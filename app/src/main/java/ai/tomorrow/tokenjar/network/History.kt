package ai.tomorrow.tokenjar.network

import java.math.BigDecimal
import java.math.BigInteger

data class ResultResponse(
//    val status: Int,
//    val message: String,
    val result: List<History>
)

data class History(
//    val blockNumber: Long?,
//    val timeStamp: Long?,
    val hash: String?
//    val nonce: Long?,
//    val blockHash: String?
//    val transactionIndex: Int,
//    val from: String,
//    val to: String,
//    val value: BigInteger,
//    val gas: BigInteger,
//    val gasPrice: BigInteger,
//    val isError: Int,
//    val cumulativeGasUsed: Long,
//    val gasUsed: Long,
//    val confirmations: Long
)