package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentSelectWalletBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class SelectWalletFragment : Fragment(){

    private lateinit var binding: FragmentSelectWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectWalletBinding.inflate(inflater, container, false)

        binding.cancelBtn.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return binding.root
    }


}