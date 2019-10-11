package ai.tomorrow.tokenjar.importwallet

import ai.tomorrow.tokenjar.databinding.FragmentImportWalletKeystoreBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ImportWalletKeystoreFragment : Fragment(){

    private lateinit var binding: FragmentImportWalletKeystoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImportWalletKeystoreBinding.inflate(inflater, container, false)

        return binding.root
    }

}