package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import ai.tomorrow.tokenjar.data.WalletRepository
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the WalletRepository to the ViewModel.
 */
class ManageWalletsViewModelFactory(
    private val repository: WalletRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ManageWalletsViewModel(repository) as T
    }
}