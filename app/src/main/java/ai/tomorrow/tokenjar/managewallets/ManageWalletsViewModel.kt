package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import ai.tomorrow.tokenjar.data.WalletRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for ManageWalletsFragment.
 */
class ManageWalletsViewModel internal constructor(
    walletRepository: WalletRepository
) : ViewModel() {
    val allWallets: LiveData<List<EthWallet>> =
        walletRepository.getWallets()
}