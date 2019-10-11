package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DefaultWalletViewModel internal constructor(
    val database: WalletDatabaseDao
) : ViewModel() {
    val wallet: LiveData<EthWallet?> = database.getFirstWallet()

}