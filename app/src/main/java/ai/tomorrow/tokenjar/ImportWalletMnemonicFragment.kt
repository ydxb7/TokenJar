package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentImportWalletKeystoreBinding
import ai.tomorrow.tokenjar.databinding.FragmentImportWalletMnemonicBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ImportWalletMnemonicFragment : Fragment(){

    private lateinit var binding: FragmentImportWalletMnemonicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImportWalletMnemonicBinding.inflate(inflater, container, false)

        return binding.root
    }

}