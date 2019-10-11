package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the WalletRepository to the ViewModel.
 */
class DefaultWalletViewModelFactory(
    private val databaseDao: WalletDatabaseDao
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DefaultWalletViewModel(databaseDao) as T
    }
}