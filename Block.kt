import java.math.BigInteger
import java.security.MessageDigest

class Block constructor(
    val previousHash: String,
    val data: String,
    var nonce: Long
) {

    val hash: String
        get() {

            val messageDigest = MessageDigest.getInstance("SHA-256")
            messageDigest.update("$previousHash$data$nonce".toByteArray())
            return String.format("%064x", BigInteger(1, messageDigest.digest()))
        }

    companion object {

        fun mine(previousHash: String, data: String, difficulty: Int): Block {
            val targetPrefix = "0".repeat(difficulty)
            val block = Block(previousHash, data, 0)
            while (!block.hash.startsWith(targetPrefix)) block.nonce++
            return block
        }
    }
}