package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.data.*
import ai.tomorrow.tokenjar.network.History
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import java.math.BigDecimal

const val UPDATE_FREQUENCY = 30000L
const val API_KEY_TOKEN = "ZBE4XGYMYQ1R164QY3VY4S5TFFGHRYNEEI"

class DefaultWalletViewModel internal constructor(
    val database: WalletDatabaseDao,
    val historyDatabase: HistoryDatabase
) : ViewModel() {
    private val TAG = "DefaultWalletViewModel"

    private var uiHandler = Handler()
    private lateinit var backgroundHandler: Handler
    private lateinit var backgroundThread: HandlerThread

    val repository = HistoryRepository(historyDatabase)
    val historyDao = historyDatabase.historyDatabaseDao

    val allHistories: LiveData<List<DatabaseHistory>> = historyDao.getAllHistory()

    val wallet: LiveData<EthWallet?> = database.getFirstWallet()

    private val web3j = Web3j.build(HttpService("https://ropsten.infura.io/llyrtzQ3YhkdESt2Fzrk"))

    private val _balanceString = MutableLiveData<String>()
    val balanceString: LiveData<String>
        get() = _balanceString


    private val _historyResponse = MutableLiveData<List<DatabaseHistory>>()
    val historyResponse: LiveData<List<DatabaseHistory>>
        get() = _historyResponse


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val backgroundThreadRunner = object : Runnable {
        override fun run() {
            Log.d(
                TAG,
                "backgroundThreadRunner, thread name: ${Thread.currentThread().name}, wallet.value = ${wallet.value}"
            )

            uiHandler.post {
                if (wallet.value != null) {
                    _balanceString.value = "${getBalance(wallet.value)} ETH"
                }
            }

            backgroundHandler.postDelayed(this, UPDATE_FREQUENCY)
        }
    }

    init {
        backgroundThread = HandlerThread("backgroundHandler")
        backgroundThread.start()
        backgroundHandler = Handler(backgroundThread.looper)
    }

    fun startUpdateBalance() {
        backgroundHandler.post(backgroundThreadRunner)
    }

    fun stopUpdateBalance() {
        backgroundHandler.removeCallbacks(backgroundThreadRunner)
    }


    private fun getBalance(wallet: EthWallet?): BigDecimal {
        Log.d(TAG, "get balance")
        if (wallet == null) {
            return BigDecimal(0)
        }
        // send asynchronous requests to get balance
        val ethGetBalance = web3j
            .ethGetBalance(wallet.address, DefaultBlockParameterName.LATEST)
            .sendAsync()
            .get()

        val wei = ethGetBalance.balance
        return Convert.fromWei(BigDecimal(wei), Convert.Unit.ETHER)
    }

    fun refreshHistoryDatabaseFromNetwork(address: String) = uiScope.launch {
        repository.refreshHistories(address)
    }

    override fun onCleared() {
        super.onCleared()
        stopUpdateBalance()
        viewModelJob.cancel()
    }
}