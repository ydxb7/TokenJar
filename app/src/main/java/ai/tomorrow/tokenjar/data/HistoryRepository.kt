package ai.tomorrow.tokenjar.data

import ai.tomorrow.tokenjar.defaultwallet.API_KEY_TOKEN
import ai.tomorrow.tokenjar.network.EtherscanApi
import ai.tomorrow.tokenjar.network.History
import ai.tomorrow.tokenjar.network.asDatabaseModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HistoryRepository(private val database: HistoryDatabase) {

    private val TAG = "HistoryRepository"

    val histories: LiveData<List<History>> =
        Transformations.map(database.historyDatabaseDao.getAllHistory()) {
            it.asDomainModel()
        }

    suspend fun refreshHistories(address: String){
        withContext(Dispatchers.IO){
            var getHistoryDeferred = EtherscanApi.retrofitService.getHistory(
                "account",
                "txlist",
                address,
                0,
                99999999,
                1,
                10,
                "asc",
                API_KEY_TOKEN
            )

            try {
                var histories = getHistoryDeferred.await().result
                database.historyDatabaseDao.insertAll(*histories.asDatabaseModel())

                Log.d(TAG, "_historyResponse.value size = ${histories.size}")
                Log.d(TAG, "_historyResponse.value = ${histories}")
            } catch (e: Exception){
                Log.d(TAG, "Fail: ${e.message}")
            }
        }
    }
}