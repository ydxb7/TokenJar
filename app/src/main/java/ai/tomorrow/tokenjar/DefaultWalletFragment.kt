package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentDefaultWalletBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class DefaultWalletFragment : Fragment(){

    private lateinit var binding: FragmentDefaultWalletBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDefaultWalletBinding.inflate(inflater, container, false)

        binding.selectWalletBtn.setOnClickListener{
            val direction = HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToSelectWalletFragment()
            it.findNavController().navigate(direction)
        }



        return binding.root
    }
}