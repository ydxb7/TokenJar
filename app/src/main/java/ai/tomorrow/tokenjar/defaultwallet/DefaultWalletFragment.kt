package ai.tomorrow.tokenjar.defaultwallet

import ai.tomorrow.tokenjar.HomeViewPagerFragmentDirections
import ai.tomorrow.tokenjar.adapters.ManageWalletAdapter
import ai.tomorrow.tokenjar.data.WalletDatabase
import ai.tomorrow.tokenjar.databinding.FragmentDefaultWalletBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController

class DefaultWalletFragment : Fragment(){

    private val TAG = "DefaultWalletFragment"

    private lateinit var binding: FragmentDefaultWalletBinding

    private val viewModel: DefaultWalletViewModel by viewModels {
        val application = requireNotNull(this.activity).application
        DefaultWalletViewModelFactory(WalletDatabase.getInstance(application).walletDatabaseDao)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDefaultWalletBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        subscribeUi()

        binding.selectWalletBtn.setOnClickListener{
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToSelectWalletFragment()
            it.findNavController().navigate(direction)
        }

        binding.sendBtn.setOnClickListener{
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToSendEthFragment()
            it.findNavController().navigate(direction)
        }

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun subscribeUi() {
        viewModel.wallet.observe(viewLifecycleOwner) { wallet ->
            Log.d(TAG, "current wallet = $wallet")
            if(wallet != null){
                viewModel.startUpdateBalance()
                binding.hasWallet = true
            } else{
                binding.hasWallet = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdateBalance()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdateBalance()
    }

}