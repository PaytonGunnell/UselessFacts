package edu.dixietech.pgunnell.uselessfacts.fragments.favoritefacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.dixietech.pgunnell.uselessfacts.databinding.ListItemFactBinding
import edu.dixietech.pgunnell.uselessfacts.model.Fact

class FavoriteFactsAdapter(
    private val clickListener: ClickListener
): ListAdapter<Fact, FavoriteFactsAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ListItemFactBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Fact, clickListener: ClickListener) {
            binding.fact = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): FavoriteFactsAdapter.ViewHolder {
                return LayoutInflater
                    .from(parent.context)
                    .run { FavoriteFactsAdapter.ViewHolder(ListItemFactBinding.inflate(this, parent, false)) }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Fact>() {

        override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemFactBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ClickListener(val clickListener: (id: String) -> Unit) {
        fun onClick(id: String) = clickListener(id)
    }
}