package ai.tomorrow.tokenjar.importwallet

import ai.tomorrow.tokenjar.R
import ai.tomorrow.tokenjar.adapters.IMPORT_KEYSTORE_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.IMPORT_MNEMONIC_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.IMPORT_PRIVATE_KEY_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.ImportWalletPagerAdapter
import ai.tomorrow.tokenjar.databinding.FragmentViewPagerImportWalletBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator

class ImportWalletViewPagerFragment : Fragment() {

    private val TAG = "ImportWalletViewPagerFragment"

    private lateinit var binding: FragmentViewPagerImportWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerImportWalletBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ImportWalletPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        binding.backBtn.setOnClickListener {
            it.findNavController().navigateUp()
        }
//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }


    private fun getTabTitle(position: Int): String? {
        return when (position) {
            IMPORT_KEYSTORE_PAGER_INDEX -> getString(R.string.keystore)
            IMPORT_MNEMONIC_PAGER_INDEX -> getString(R.string.mnemonic)
            IMPORT_PRIVATE_KEY_PAGER_INDEX -> getString(R.string.private_key)
            else -> null
        }
    }


}