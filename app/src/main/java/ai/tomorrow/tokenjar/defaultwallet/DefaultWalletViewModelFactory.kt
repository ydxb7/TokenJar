package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.data.HistoryDatabase
import ai.tomorrow.tokenjar.data.HistoryDatabaseDao
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
    private val databaseDao: WalletDatabaseDao,
    private val historyDatabase: HistoryDatabase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DefaultWalletViewModel(databaseDao, historyDatabase) as T
    }
}