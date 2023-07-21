package com.darosa.app.ui.datasiswa

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darosa.app.data.User
import com.darosa.app.databinding.ItemRowUserBinding
import com.darosa.app.ui.datasiswa.DetailSiswaActivity.Companion.EXTRA_USER
import com.darosa.app.utils.UserDiffCallback
import com.darosa.app.utils.loadImageUrl

class DataSiswaAdapter : RecyclerView.Adapter<DataSiswaAdapter.ViewHolder>() {

    var listUser = ArrayList<User>()

    fun setUser(itemUser: List<User>) {
        val diffCallback = UserDiffCallback(listUser, itemUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listUser.clear()
        listUser.addAll(itemUser)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvName.text = user.nama
                tvAddress.text = user.alamat
                ivPhotoUser.loadImageUrl(user.fotoProfil.toString())
                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailSiswaActivity::class.java)
                    intent.putExtra(EXTRA_USER, user)
                    it.context.startActivity(intent)
                }
            }
        }
    }

}