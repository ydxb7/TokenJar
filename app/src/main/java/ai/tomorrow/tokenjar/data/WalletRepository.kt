package ai.tomorrow.tokenjar.data

///**
// * Repository module for handling data operations.
// */
//class WalletRepository private constructor(private val walletDao: WalletDatabaseDao) {
//
//    fun getWallets() = walletDao.getWallets()
//
//    fun getWallet(walletId: Long) = walletDao.getWallet(walletId)
//
//    fun getFirstWallet() = walletDao.getFirstWallet()
//
//    suspend fun insertWallet(wallet: EthWallet) =
//        walletDao.insert(wallet)
//
//    suspend fun updateWallet(wallet: EthWallet) =
//        walletDao.update(wallet)
//
//    suspend fun removeAllWallets() = walletDao.clear()
//
//    companion object {
//
//        // For Singleton instantiation
//        @Volatile private var instance: WalletRepository? = null
//
//        fun getInstance(walletDao: WalletDatabaseDao) =
//            instance ?: synchronized(this) {
//                instance ?: WalletRepository(walletDao).also { instance = it }
//            }
//    }
//}