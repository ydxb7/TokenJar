package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletDatabaseDao
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import java.math.BigDecimal

const val UPDATE_FREQUENCY = 30000L

class DefaultWalletViewModel internal constructor(
    val database: WalletDatabaseDao
) : ViewModel() {
    private val TAG = "DefaultWalletViewModel"

    private var uiHandler = Handler()
    private lateinit var backgroundHandler: Handler
    private lateinit var backgroundThread: HandlerThread

    val wallet: LiveData<EthWallet?> = database.getFirstWallet()

    private val web3j = Web3j.build(HttpService("https://ropsten.infura.io/llyrtzQ3YhkdESt2Fzrk"))

    private val _balanceString = MutableLiveData<String>()
    val balanceString: LiveData<String>
        get() = _balanceString



    private val backgroundThreadRunner = object : Runnable {
        override fun run() {
            Log.d(TAG, "backgroundThreadRunner, thread name: ${Thread.currentThread().name}, wallet.value = ${wallet.value}" )

            uiHandler.post {
                if (wallet.value != null) {
                    _balanceString.value ="${getBalance(wallet.value)} ETH"
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

    fun stopUpdateBalance(){
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


    override fun onCleared() {
        super.onCleared()
        stopUpdateBalance()
    }
}