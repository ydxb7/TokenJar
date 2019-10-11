package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel for ManageWalletsFragment.
 */
class ManageWalletsViewModel internal constructor(
    walletRepository: WalletRepository
) : ViewModel() {
    val allWallets: LiveData<List<EthWallet>> =
        walletRepository.getWallets()

    init {
        viewModelScope.launch {
            walletRepository.removeAllWallets()

            val fakeWallets = arrayListOf(
                EthWallet(
                    1, "0xadgshwrt", "wallet name 1", "password1",
                    "keystore data 1", "keystore path", "yes no", "akdjgjjad"
                ),
                EthWallet(
                    2, "0xadgshwrt", "wallet name 2", "password1",
                    "keystore data 1", "keystore path", "yes no", "akdjgjjad"
                ),
                EthWallet(
                    3, "0xadgshwrt", "wallet name 3", "password3",
                    "keystore data 1", "keystore path", "yes no", "akdjgjjad"
                )
            )

            for(wallet in fakeWallets)
                walletRepository.insertWallet(wallet)
        }
    }
}