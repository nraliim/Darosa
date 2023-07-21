package com.darosa.app.utils

import androidx.recyclerview.widget.DiffUtil
import com.darosa.app.data.User

class UserDiffCallback(private val oldUserList: List<User>, private val newUserList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldUserList.size
    override fun getNewListSize(): Int = newUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].nama == newUserList[newItemPosition].nama
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldUserList[oldItemPosition]
        val newUser = newUserList[newItemPosition]
        return oldUser.nama == newUser.nama && oldUser.alamat == newUser.alamat
    }
}