
<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>Upload File</title>
    </head>
    <body>
    <div class="jumbotron">
    <h1 class="display-4">Upload the file to process</h1>
    <hr class="my-4">

    <g:form name="convertForm" action="process" enctype='multipart/form-data'>
    <div class="form-row">
    <div class="form-group">
    <label for="payload">Upload File</label>
    <input type="file" class="form-control-file" id="payload" name="payload" accept="text/plain" >
    </div>

    </div>
    <div class="form-row">
    <div class="col">
    <button type="submit"class="btn btn-primary">Upload</button>
    </div>

    </div>
    </g:form>

    </div>

    </body>
    </html>

