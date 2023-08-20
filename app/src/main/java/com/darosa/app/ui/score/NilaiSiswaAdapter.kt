package com.darosa.app.ui.score

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darosa.app.data.Score
import com.darosa.app.databinding.ItemRowScoresBinding

class NilaiSiswaAdapter : RecyclerView.Adapter<NilaiSiswaAdapter.ViewHolder>() {

    private var listScores = ArrayList<Score>()

    fun setScores(itemScores: List<Score>) {
        val diffCallback = ScoreDiffCallback(listScores, itemScores)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listScores.clear()
        listScores.addAll(itemScores)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NilaiSiswaAdapter.ViewHolder {
        val binding = ItemRowScoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NilaiSiswaAdapter.ViewHolder, position: Int) {
        holder.bind(listScores[position])
    }

    override fun getItemCount(): Int = listScores.size

    inner class ViewHolder(private val binding: ItemRowScoresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(score: Score) {
            binding.apply {
                tvName.text = score.nama
                tvJilid0.text = score.jilid0.toString()
                tvJilid1.text = score.jilid1.toString()
                tvJilid2.text = score.jilid2.toString()
                tvJilid3.text = score.jilid3.toString()

                itemView.setOnClickListener {
                    val intent = Intent(it.context, EditNilaiActivity::class.java)
                    intent.putExtra(EditNilaiActivity.EXTRA_SCORE, score)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    inner class ScoreDiffCallback(private val oldScoreList: List<Score>, private val newScoreList: List<Score>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldScoreList.size
        override fun getNewListSize(): Int = newScoreList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldScoreList[oldItemPosition].nama == newScoreList[newItemPosition].nama
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldScore = oldScoreList[oldItemPosition]
            val newScore = newScoreList[newItemPosition]
            return oldScore.jilid0 == newScore.jilid0 && oldScore.jilid1 == newScore.jilid1 && oldScore.jilid2 == newScore.jilid2 && oldScore.jilid3 == newScore.jilid3
        }
    }
}