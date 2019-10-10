package ai.tomorrow.tokenjar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WalletDatabaseDao {

    @Insert
    fun insert(wallet: EthWallet)

    @Update
    fun update(wallet: EthWallet)

    @Query("SELECT * from eth_wallet_table WHERE walletId = :key")
    fun get(key: Long): EthWallet?

    @Query("DELETE FROM eth_wallet_table")
    fun clear()

    @Query("SELECT * FROM eth_wallet_table ORDER BY walletId ASC")
    fun getAllWallets(): LiveData<List<EthWallet>>
}