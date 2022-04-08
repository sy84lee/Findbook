package com.sy.searchbook.model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sy.searchbook.databinding.FragmentItemBinding

class BookListAdapter : PagingDataAdapter<doc, BookListAdapter.ViewHolder>(GITHUB_DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class ViewHolder(private val binding: FragmentItemBinding): RecyclerView.ViewHolder(binding.root) {
        private var view: View = binding.root
        private val icon: ImageView = binding.mtrlListItemIcon
        private val title: TextView = binding.mtrlListItemText
        private val content: TextView = binding.mtrlListItemSecondaryText

        fun bind(item: doc) {
            Log.i("TEST3", item.title)
            Glide.with(view).load(item.thumbnail).into(icon)
            title.text = item.title
            content.text = item.contents
        }
    }

    companion object {
        private val GITHUB_DIFF = object: DiffUtil.ItemCallback<doc>() {
            override fun areItemsTheSame(oldItem: doc, newItem: doc): Boolean =
                //oldItem.fullName == newItem.fullName
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: doc, newItem: doc): Boolean =
                oldItem == newItem
        }
    }
}

//class BookListAdapter(
//    private val values: List<BookInfo>
//) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {
//
//    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        private var view: View = binding.root
//        val icon: ImageView = binding.mtrlListItemIcon
//        val title: TextView = binding.mtrlListItemText
//        val content: TextView = binding.mtrlListItemSecondaryText
//
//        fun bind(listener: View.OnClickListener, item: BookInfo) {
//            Glide.with(view).load(item.thumnail).into(icon)
//            title.text = item.title
//            content.text = item.contents
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            FragmentItemBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = values[position]
//        val listener = View.OnClickListener {  }
//        holder.apply {
//            bind(listener, item)
//        }
//    }
//
//    override fun getItemCount(): Int = values.size
//}