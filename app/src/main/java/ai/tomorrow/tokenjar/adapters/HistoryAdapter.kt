package ai.tomorrow.tokenjar.adapters

import ai.tomorrow.tokenjar.data.DatabaseHistory
import ai.tomorrow.tokenjar.databinding.ListItemHistoryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(val myAddress: String) :
    ListAdapter<DatabaseHistory, HistoryAdapter.ViewHolder>(HistoryDiffCallback()) {

    public val TAG = "HistoryAdapterAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = getItem(position)
        (holder as ViewHolder).bind(history, myAddress)
    }

    class ViewHolder(
        private val binding: ListItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: DatabaseHistory, myAddress: String) {
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