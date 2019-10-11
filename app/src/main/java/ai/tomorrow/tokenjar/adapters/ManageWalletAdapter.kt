package ai.tomorrow.tokenjar.adapters

import ai.tomorrow.tokenjar.HomeViewPagerFragmentDirections
import ai.tomorrow.tokenjar.data.EthWallet
import ai.tomorrow.tokenjar.databinding.ListItemManageWalletBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ManageWalletAdapter :
    ListAdapter<EthWallet, ManageWalletAdapter.ViewHolder>(WalletDiffCallback()) {

    public val TAG = "ManageWalletAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemManageWalletBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallet = getItem(position)
        (holder as ViewHolder).bind(wallet)
    }

    class ViewHolder(
        private val binding: ListItemManageWalletBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            // TODO navigate to ManageFragment
            binding.setClickListener { view ->
                Log.d("ManageWalletAdapter", "click to navigate to manage")
                binding.wallet?.let { wallet ->
                    navigateToManage(wallet, view)
                }
            }
        }

        private fun navigateToManage(
            wallet: EthWallet,
            view: View
        ){
            val direction = HomeViewPagerFragmentDirections.actionHomeViewPagerFragment2ToManageFragment(wallet.walletId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: EthWallet) {
            binding.apply {
                wallet = item
                executePendingBindings()
            }
        }
    }
}

private class WalletDiffCallback : DiffUtil.ItemCallback<EthWallet>() {

    override fun areItemsTheSame(
        oldItem: EthWallet,
        newItem: EthWallet
    ): Boolean {
        return oldItem.walletId == newItem.walletId
    }

    override fun areContentsTheSame(
        oldItem: EthWallet,
        newItem: EthWallet
    ): Boolean {
        return oldItem == newItem
    }
}