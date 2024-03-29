package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for ManageWalletsFragment.
 */
class ManageWalletsViewModel internal constructor(
    val database: WalletDatabaseDao
) : ViewModel() {
    val allWallets: LiveData<List<EthWallet>> = database.getWallets()
        init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val allWallets1 = database.getWallets()
                Log.d("ManageWalletsViewModel", "allWallets1 = ${allWallets1.value?.size}")
            }

        }
    }
}