package ai.tomorrow.tokenjar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eth_wallet_table")
data class EthWallet(
    @PrimaryKey(autoGenerate = true)
    var walletId: Long = 0L,

    @ColumnInfo(name = "address")
    val address: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "keystore")
    var keystore: String = "",

    @ColumnInfo(name = "keystore_path")
    var keystorePath: String = "",

    @ColumnInfo(name = "mnemonic")
    var mnemonic: String = "",

    @ColumnInfo(name = "private_key")
    var privateKey: String = ""
)