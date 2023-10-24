package com.darosa.app.ui.datasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darosa.app.R
import com.darosa.app.databinding.ActivityDataSiswaBinding
import com.darosa.app.utils.hide
import com.darosa.app.utils.show

class DataSiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataSiswaBinding
    private lateinit var viewModel: DataSiswaViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: DataSiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DataSiswaViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        adapter = DataSiswaAdapter()

        initialState()
        searchUserData()
        setUserData()

    }

    private fun initialState() {
        binding.apply {
            rvDataSiswa.setHasFixedSize(true)
            rvDataSiswa.layoutManager = LinearLayoutManager(applicationContext)
            rvDataSiswa.adapter = adapter
//            layoutEmptyData.root.hide()
        }

        viewModel.allUsers.observe(this) { listUser ->
            listUser.let {
                if (it.isEmpty()) {
                    binding.rvDataSiswa.hide()
                    binding.layoutEmptyData.root.show()
                } else {
                    adapter.setUser(it)
                }
            }
        }
    }

    private fun searchUserData() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isEmpty()) {
                        initialState()
                    } else {
                        adapter.listUser.clear()
                        progressBarDialog.show()
                        newText.let {
//                            binding.searchView.clearFocus()
                            finalState()
                            searchViewModel.searchUser(it)
                        }
                    }

                    return true
                }
            })

        }

    }

    private fun setUserData() {
        binding.apply {
            rvDataSiswa.setHasFixedSize(true)
            rvDataSiswa.layoutManager = LinearLayoutManager(applicationContext)
            rvDataSiswa.adapter = adapter
        }
        searchViewModel.allUsers.observe(this) { listUser ->
            listUser.let {
                if (it.isEmpty()) {
                    emptyState()
                    binding.progressBarDialog.hide()
                } else {
                    adapter.setUser(it)
                    finalState()
                    binding.progressBarDialog.hide()
                }
            }
        }
    }

    private fun emptyState() {
        binding.apply {
            rvDataSiswa.hide()
            layoutEmptyData.let {
                it.tvEmpty.text = getString(R.string.not_found)
                it.root.show()
            }
        }
    }

    private fun finalState() {
        binding.apply {
            rvDataSiswa.show()
            layoutEmptyData.root.hide()
        }
    }
}