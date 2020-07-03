/**
 * Class : CoinData
 */
class CoinData {
    String userID
    Integer coins
    String userName
    static constraints = {
        userID size: 10..10, nullable: false, unique: true
        coins min: 0, nullable: false
        userName nullable: false
    }
}
