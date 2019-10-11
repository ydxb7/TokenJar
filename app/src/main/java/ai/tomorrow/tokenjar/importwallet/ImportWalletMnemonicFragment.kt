package ai.tomorrow.tokenjar.importwallet

import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.data.WalletDatabase
import ai.tomorrow.tokenjar.databinding.FragmentImportWalletMnemonicBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.coroutines.*
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.KeystoreStorage
import org.consenlabs.tokencore.wallet.WalletManager
import org.consenlabs.tokencore.wallet.model.Metadata
import org.consenlabs.tokencore.wallet.model.Network
import java.io.File

class ImportWalletMnemonicFragment : Fragment(), KeystoreStorage {

    private val TAG = "ImportWalletMnemonic"

    private lateinit var binding: FragmentImportWalletMnemonicBinding
    private lateinit var identity: Identity
    private var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val database = WalletDatabase.getInstance(requireContext()).walletDatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImportWalletMnemonicBinding.inflate(inflater, container, false)

        WalletManager.storage = this
        WalletManager.scanWallets()

        binding.importWalletBtn.setOnClickListener {
            if (binding.walletPasswordEt.text.isEmpty() || binding.repeatPasswordEt.text.isEmpty()) {
                Toast.makeText(context, "Password is required.", Toast.LENGTH_SHORT).show()
            } else if (binding.walletPasswordEt.text.toString() != binding.repeatPasswordEt.text.toString()) {
                Log.d(TAG, "password: ${binding.walletPasswordEt.text}")
                Log.d(TAG, "repeat password: ${binding.repeatPasswordEt.text}")
                Toast.makeText(context, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            } else if (binding.mnemonicEv.text.isEmpty()) {
                Toast.makeText(context, "Mnemonic phrases is required.", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "create wallet.")
                val wallet = getWalletFromMnemonic(binding.mnemonicEv.text.toString())

                uiScope.launch {
                    insert(wallet)
                }
                it.findNavController().navigateUp()
            }
        }
        return binding.root
    }

    private suspend fun insert(wallet: EthWallet) {
        withContext(Dispatchers.IO) {
            database.insertWallet(wallet)
        }
        Log.d(TAG, "successfully insert the wallet!")
    }

    private fun getWalletFromMnemonic(mnemonic: String): EthWallet {
        val password = binding.walletPasswordEt.text.toString()
        val passwordHint = binding.passwordHintEt.text.toString()

        identity = Identity.recoverIdentity(
            mnemonic.trim(),
            "identity1",
            password,
            passwordHint,
            Network.ROPSTEN,
            Metadata.NONE
        )
        Log.d(TAG, "get identity")

        val tokenCoreWallet = identity.wallets[0]
        val wallet = EthWallet()
        wallet.address = "0x${tokenCoreWallet.address}"
        wallet.keystore = WalletManager.exportKeystore(tokenCoreWallet.id, password)
        wallet.keystorePath =
            requireActivity().filesDir.absolutePath + "/wallets" + "/${tokenCoreWallet.id}.json"
        wallet.privateKey = WalletManager.exportPrivateKey(tokenCoreWallet.id, password)
        wallet.mnemonic = WalletManager.exportMnemonic(tokenCoreWallet.id, password).mnemonic
        wallet.name = "new wallet"

        Log.d(TAG, "wallet.address = ${wallet.address}")
        Log.d(TAG, "wallet.keystore = ${wallet.keystore}")
        Log.d(TAG, "wallet.keystorePath = ${wallet.keystorePath}")
        Log.d(TAG, "wallet.privateKey = ${wallet.privateKey}")
        Log.d(TAG, "wallet.mnemonic = ${wallet.mnemonic}")
        Log.d(TAG, "wallet.name = ${wallet.name}")
        return wallet
    }

    override fun getKeystoreDir(): File {
//        Log.d(TAG, "getKeystoreDir = $filesDir")
        return requireActivity().filesDir

    }
}