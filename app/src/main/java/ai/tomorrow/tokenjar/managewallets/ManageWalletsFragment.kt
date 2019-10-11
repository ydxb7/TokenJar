package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.HomeViewPagerFragmentDirections
import ai.tomorrow.tokenjar.adapters.ManageWalletAdapter
import ai.tomorrow.tokenjar.data.WalletDatabase
import ai.tomorrow.tokenjar.databinding.FragmentManageWalletsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController

class ManageWalletsFragment : Fragment(){

    private lateinit var binding: FragmentManageWalletsBinding

    private val viewModel: ManageWalletsViewModel by viewModels {
        val application = requireNotNull(this.activity).application
        ManageWalletsViewModelFactory(WalletDatabase.getInstance(application).walletDatabaseDao)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentManageWalletsBinding.inflate(inflater, container, false)

        val adapter = ManageWalletAdapter()
        binding.walletRecyclerView.adapter = adapter
        subscribeUi(adapter)


        binding.addWalletBtn.setOnClickListener{
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToImportWalletViewPagerFragment()
            it.findNavController().navigate(direction)
        }


        binding.lifecycleOwner = this
        return binding.root
    }

    private fun subscribeUi(adapter: ManageWalletAdapter) {
        viewModel.allWallets.observe(viewLifecycleOwner) { wallets ->
            binding.hasWallets = !wallets.isNullOrEmpty()
            adapter.submitList(wallets)
        }
    }
}