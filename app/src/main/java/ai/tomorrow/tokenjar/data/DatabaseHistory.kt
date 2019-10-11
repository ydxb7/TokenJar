package ai.tomorrow.tokenjar.data

import ai.tomorrow.tokenjar.network.History
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class DatabaseHistory constructor(
    val blockNumber: Long,
    val timeStamp: Long,
    @PrimaryKey
    val hash: String,
    val nonce: Long,
    val blockHash: String,
    val transactionIndex: Int,
    val from: String,
    val to: String,
    val value: String,
    val gas: String,
    val gasPrice: String,
    val isError: Int,
    val cumulativeGasUsed: Long,
    val gasUsed: Long,
    val confirmations: Long
)

fun List<DatabaseHistory>.asDomainModel(): List<History> {
    return map {
        History(
            blockNumber = it.blockNumber,
            timeStamp = it.timeStamp,
            hash = it.hash,
            nonce = it.nonce,
            blockHash = it.blockHash,
            transactionIndex = it.transactionIndex,
            from = it.from,
            to = it.to,
            value = it.value,
            gas = it.gas,
            gasPrice = it.gasPrice,
            isError = it.isError,
            cumulativeGasUsed = it.cumulativeGasUsed,
            gasUsed = it.gasUsed,
            confirmations = it.confirmations
        )
    }
}