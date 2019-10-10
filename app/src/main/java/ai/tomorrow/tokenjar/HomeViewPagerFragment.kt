package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.adapters.DEFAULT_WALLET_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.MANAGE_WALLETS_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.HomePagerAdapter
import ai.tomorrow.tokenjar.databinding.FragmentViewPagerHomeBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment(){

    private val TAG = "HomeViewPagerFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentViewPagerHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomePagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()



        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            DEFAULT_WALLET_PAGER_INDEX -> R.drawable.dollar_icon
            MANAGE_WALLETS_PAGER_INDEX -> R.drawable.manage_icon
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            DEFAULT_WALLET_PAGER_INDEX -> getString(R.string.default_wallet)
            MANAGE_WALLETS_PAGER_INDEX -> getString(R.string.manage_wallets)
            else -> null
        }
    }


}