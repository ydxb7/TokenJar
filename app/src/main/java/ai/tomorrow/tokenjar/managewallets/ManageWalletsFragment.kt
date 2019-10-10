package ai.tomorrow.tokenjar.managewallets

import ai.tomorrow.tokenjar.HomeViewPagerFragmentDirections
import ai.tomorrow.tokenjar.data.WalletDatabase
import ai.tomorrow.tokenjar.data.WalletRepository
import ai.tomorrow.tokenjar.databinding.FragmentManageWalletsBinding
import ai.tomorrow.tokenjar.utilities.InjectorUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

class ManageWalletsFragment : Fragment(){

    private lateinit var binding: FragmentManageWalletsBinding

    private val viewModel: ManageWalletsViewModel by viewModels {
        val application = requireNotNull(this.activity).application
        ManageWalletsViewModelFactory(WalletRepository.getInstance(WalletDatabase.getInstance(application).walletDatabaseDao))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentManageWalletsBinding.inflate(inflater, container, false)


        binding.addWalletBtn.setOnClickListener{
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToImportWalletViewPagerFragment()
            it.findNavController().navigate(direction)
        }


        binding.lifecycleOwner = this
        return binding.root
    }

}