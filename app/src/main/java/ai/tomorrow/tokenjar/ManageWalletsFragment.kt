package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentManageWalletsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class ManageWalletsFragment : Fragment(){

    private lateinit var binding: FragmentManageWalletsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentManageWalletsBinding.inflate(inflater, container, false)


        binding.addWalletBtn.setOnClickListener{
            val direction = HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToImportWalletViewPagerFragment()
            it.findNavController().navigate(direction)
        }


        return binding.root
    }

}