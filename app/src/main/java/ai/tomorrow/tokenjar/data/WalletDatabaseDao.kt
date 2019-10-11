package ai.tomorrow.tokenjar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WalletDatabaseDao {

    @Insert
    suspend fun insert(wallet: EthWallet)

    @Update
    suspend fun update(wallet: EthWallet)

    @Query("SELECT * from eth_wallet_table WHERE walletId = :key")
    suspend fun getWallet(key: Long): EthWallet?

    @Query("DELETE FROM eth_wallet_table")
    suspend fun clear()

    @Query("SELECT * FROM eth_wallet_table ORDER BY walletId ASC")
    fun getWallets(): LiveData<List<EthWallet>>
}