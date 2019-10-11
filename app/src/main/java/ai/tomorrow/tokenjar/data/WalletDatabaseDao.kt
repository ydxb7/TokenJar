package ai.tomorrow.tokenjar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WalletDatabaseDao {

    @Insert
    fun insertWallet(wallet: EthWallet)

    @Update
    fun update(wallet: EthWallet)

    @Query("SELECT * from eth_wallet_table WHERE walletId = :key")
    fun getWallet(key: Long): EthWallet?

//    @Query("DELETE FROM eth_wallet_table")
//    fun clear()

    @Query("SELECT * FROM eth_wallet_table ORDER BY walletId ASC")
    fun getWallets(): LiveData<List<EthWallet>>

    @Query("SELECT * FROM eth_wallet_table ORDER BY walletId ASC LIMIT 1")
    fun getFirstWallet(): LiveData<EthWallet?>
}