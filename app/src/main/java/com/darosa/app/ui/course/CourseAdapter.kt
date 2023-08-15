package com.darosa.app.ui.course

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darosa.app.R
import com.darosa.app.data.Pages
import com.darosa.app.databinding.ItemRowPageBinding
import com.darosa.app.ui.page.PageActivity
import com.darosa.app.ui.page.PageActivity.Companion.EXTRA_PAGES

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    private var listPages = ArrayList<Pages>()

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
                if (pages.isChecked == true) {
                    ivChecklist.setImageResource(R.drawable.ic_check)
                } else {
                    ivChecklist.setImageResource(R.drawable.ic_circle)
                }

                itemView.setOnClickListener {
                    val intent = Intent(it.context, PageActivity::class.java)
                    intent.putExtra(EXTRA_PAGES, pages)
                    it.context.startActivity(intent)
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