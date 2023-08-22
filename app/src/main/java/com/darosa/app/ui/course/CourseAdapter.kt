package com.darosa.app.ui.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darosa.app.R
import com.darosa.app.data.Pages
import com.darosa.app.databinding.ItemRowPageBinding
import com.darosa.app.utils.ItemClickListener

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: ItemClickListener
    private var listPages = ArrayList<Pages>()

    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    fun setPages(itemPages: List<Pages>) {
        val diffCallback = PageDiffCallback(listPages, itemPages)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listPages.clear()
        listPages.addAll(itemPages)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemRowPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPages[position])
    }

    override fun getItemCount(): Int = listPages.size

    inner class ViewHolder(private val binding: ItemRowPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pages: Pages) {
            binding.apply {
                tvPage.text = pages.halaman.toString()
                when (pages.isChecked) {
                    10L -> ivChecklist.setImageResource(R.drawable.ic_circle)
                    20L -> ivChecklist.setImageResource(R.drawable.ic_radio_check)
                    30L -> ivChecklist.setImageResource(R.drawable.ic_check)
                }

                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(pages)
                }
            }
        }
    }

    inner class PageDiffCallback(private val oldCourseList: List<Pages>, private val newCourseList: List<Pages>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldCourseList.size
        override fun getNewListSize(): Int = newCourseList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCourseList[oldItemPosition].halaman == newCourseList[newItemPosition].halaman
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldUser = oldCourseList[oldItemPosition]
            val newUser = newCourseList[newItemPosition]
            return oldUser.halaman == newUser.halaman && oldUser.isChecked == newUser.isChecked
        }
    }
}