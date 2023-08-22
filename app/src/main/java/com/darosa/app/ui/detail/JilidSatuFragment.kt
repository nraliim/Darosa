package com.darosa.app.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darosa.app.data.Pages
import com.darosa.app.databinding.FragmentJilidSatuBinding
import com.darosa.app.ui.course.CourseAdapter
import com.darosa.app.ui.course.CourseViewModel
import com.darosa.app.ui.page.PageSiswaActivity
import com.darosa.app.utils.ItemClickListener

class JilidSatuFragment : Fragment() {

    private var _binding: FragmentJilidSatuBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: CourseViewModel
    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJilidSatuBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CourseViewModel::class.java]
        adapter = CourseAdapter()

        getCourse()
    }

    private fun getCourse() {
        binding?.apply {
            rvPagesSatu.layoutManager = LinearLayoutManager(requireContext())
            rvPagesSatu.adapter = adapter
        }
        viewModel.getCourseData("jilid1").observe(viewLifecycleOwner) {
            adapter.setPages(it)
        }
        adapter.setOnItemClickListener(object : ItemClickListener {
            override fun onItemClick(pages: Pages) {
                val intent = Intent(context, PageSiswaActivity::class.java)
                intent.putExtra(PageSiswaActivity.EXTRA_PAGES, pages)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}