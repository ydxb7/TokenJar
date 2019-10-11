package ai.tomorrow.tokenjar.network

import ai.tomorrow.tokenjar.data.DatabaseHistory
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkHistoryContainer(val videos: List<History>)

//fun NetworkHistoryContainer.asDatabaseModel(): Array<DatabaseHistory> {
//    return videos.map {
//        DatabaseHistory(
//            blockNumber = it.blockNumber,
//            timeStamp = it.timeStamp,
//            hash = it.hash,
//            nonce = it.nonce,
//            blockHash = it.blockHash,
//            transactionIndex = it.transactionIndex,
//            from = it.from,
//            to = it.to,
//            value = it.value,
//            gas = it.gas,
//            gasPrice = it.gasPrice,
//            isError = it.isError,
//            cumulativeGasUsed = it.cumulativeGasUsed,
//            gasUsed = it.gasUsed,
//            confirmations = it.confirmations)
//    }.toTypedArray()
//}