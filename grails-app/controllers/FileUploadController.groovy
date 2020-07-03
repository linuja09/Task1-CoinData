import org.apache.tomcat.util.http.fileupload.FileUtils
import org.hibernate.Session
import org.hibernate.Transaction

/**
 * class : FileUploadController
 * Implements the logic to read the contents from the uploaded text file, validate and store the data in the database
 */
class FileUploadController {

    static final String PATH = "/grails-app/assets/"
    static final String BASE_PATH = new File("").getAbsolutePath();
    static final String TEMP_FILE = "temp.txt"

    def sessionFactory

    /**
     * Redirects the list page
     */
    def index = {
        redirect action: "list"
    }

    /**
     * Displays the Data and the errors if available
     */
    def list = {
        def error =  params.error
        def data = CoinData.list()
        [coinDatas: data, error: error]
    }

    def file = {}

    /**
     * Logic to read the contents from the uploaded text file, validate and store the data in the database
     */
    def process = {

        String directoryName = BASE_PATH.concat(PATH).concat("temp");
        File directory = new File(directoryName);
        File tempFile = new File(directoryName+TEMP_FILE)

        // Create a directory in the name 'temp' if it doesn't already exist
        if (!directory.exists()){
            FileUtils.forceMkdir(directory)
        }

        // Get the uploaded text file
        def file_ = request.getFile("payload");

        // If file is uploaded and not empty, transfer the contents in the text file to a new file 'temp.txt' which is
        // created in the 'temp' directory
        if (file_ && !file_.empty) {
            if (tempFile?.exists()){
                FileUtils.forceDelete(tempFile);
            }
            file_.transferTo(tempFile)
        } else {
            throw new Exception("No file uploaded")
        }

        def lineNo = 1
        def lines = tempFile.readLines()
        def temp
        def data
        def error = [];

        Session session1 = sessionFactory.openSession();

        Transaction tx = session1.beginTransaction();
        // Read contents in the text file and assign it to the 'CoinData' object
        lines.each {
            temp = it.tokenize(', ')
            data = new CoinData()
            data.userID = temp[0]
            data.userName = temp[2]

            try {
                data.coins = Integer.parseInt(temp[1])
                session1.save(data)
            } catch (Exception e) {
                println("FAILED")
                error.push(lineNo)
            }
            lineNo++
        }

        // If no errors, commit transaction, else, rollback transaction
        try {
            println error.isEmpty()
            if(error.isEmpty()){
                tx.commit()
            }
            else {
                tx.rollback()
            }
        } catch (Exception e) {
            println("FAILED 2 ${e}")
        }

        FileUtils.forceDelete(new java.io.File(directoryName+"temp.txt"));

        redirect(action:"list", params: [
                error: error,
        ])
    }
}
