package ai.tomorrow.tokenjar.adapters

import ai.tomorrow.tokenjar.AddressBookFragment
import ai.tomorrow.tokenjar.DefaultWalletFragment
import ai.tomorrow.tokenjar.ManageWalletsFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IndexOutOfBoundsException

const val DEFAULT_WALLET_PAGER_INDEX = 0
const val MANAGE_WALLETS_PAGER_INDEX = 1
const val ADDRESS_BOOK_PAGER_INDEX = 2


class TokenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        DEFAULT_WALLET_PAGER_INDEX to { DefaultWalletFragment() },
        MANAGE_WALLETS_PAGER_INDEX to { ManageWalletsFragment() },
        ADDRESS_BOOK_PAGER_INDEX to { AddressBookFragment() }
    )

    override fun getItemCount() = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}