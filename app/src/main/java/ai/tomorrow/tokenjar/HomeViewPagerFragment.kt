package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.adapters.ADDRESS_BOOK_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.DEFAULT_WALLET_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.MANAGE_WALLETS_PAGER_INDEX
import ai.tomorrow.tokenjar.adapters.TokenPagerAdapter
import ai.tomorrow.tokenjar.databinding.FragmentViewPagerBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment(){

    private val TAG = "HomeViewPagerFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = TokenPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
//        Log.d(TAG, "position = $position")
        return when (position) {
            DEFAULT_WALLET_PAGER_INDEX -> R.drawable.dollar_icon
            MANAGE_WALLETS_PAGER_INDEX -> R.drawable.manage_icon
            ADDRESS_BOOK_PAGER_INDEX -> R.drawable.book_icon
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            DEFAULT_WALLET_PAGER_INDEX -> getString(R.string.default_wallet)
            MANAGE_WALLETS_PAGER_INDEX -> getString(R.string.manage_wallets)
            ADDRESS_BOOK_PAGER_INDEX -> getString(R.string.address_book)
            else -> null
        }
    }


}