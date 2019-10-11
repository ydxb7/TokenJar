package ai.tomorrow.tokenjar.adapters

import ai.tomorrow.tokenjar.data.DatabaseHistory
import ai.tomorrow.tokenjar.databinding.ListItemHistoryBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter :
    ListAdapter<DatabaseHistory, HistoryAdapter.ViewHolder>(HistoryDiffCallback()) {

    public val TAG = "HistoryAdapterAdapter"
    private var myAddress: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        return ViewHolder(
            ListItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        val history = getItem(position)
        (holder as ViewHolder).bind(history, myAddress)
    }

    fun setAddress(address: String){
        Log.d(TAG, "setAddress")
        myAddress = address
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: DatabaseHistory, myAddress: String?) {
            Log.d("HistoryAdapter", "bind")
            if (myAddress != null){
                binding.apply {
                    isFrom = (myAddress == history.to)
                    if (isFrom) {
                        addressTv.text = history.from
                        valueTv.text = "+ ${history.value}"
                    } else {
                        addressTv.text = history.to
                        valueTv.text = "- ${history.value}"
                    }

                    executePendingBindings()
                }
            }
        }
    }
}

private class HistoryDiffCallback : DiffUtil.ItemCallback<DatabaseHistory>() {

    override fun areItemsTheSame(
        oldItem: DatabaseHistory,
        newItem: DatabaseHistory
    ): Boolean {
        return oldItem.hash == newItem.hash
    }

    override fun areContentsTheSame(
        oldItem: DatabaseHistory,
        newItem: DatabaseHistory
    ): Boolean {
        return oldItem == newItem
    }
}