package ai.tomorrow.tokenjar.importwallet

import ai.tomorrow.tokenjar.databinding.FragmentImportWalletKeystoreBinding
import ai.tomorrow.tokenjar.databinding.FragmentImportWalletMnemonicBinding
import ai.tomorrow.tokenjar.databinding.FragmentImportWalletPrivateKeyBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ImportWalletPrivateKeyFragment : Fragment(){

    private lateinit var binding: FragmentImportWalletPrivateKeyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImportWalletPrivateKeyBinding.inflate(inflater, container, false)

        return binding.root
    }

}