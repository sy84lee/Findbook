package com.sy.searchbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.sy.searchbook.PagingViewModel
import com.sy.searchbook.databinding.MainFragmentBinding
import com.sy.searchbook.model.BookListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var mBinding: MainFragmentBinding? = null
    private val binding get() = mBinding!!
    private var searchView: SearchView? = null
    private var recycleView: RecyclerView? = null
    //private var model: Model? = null
    private var columnCount = 1
    private var word: String? = null
    //private val viewModel by viewModels<PagingViewModel>()
    private val viewModel: PagingViewModel by sharedViewModel()
    private val adapter = BookListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //model = Model();
        //model!!.addObserver(this)

        mBinding = MainFragmentBinding.inflate(inflater, container, false)
        searchView = binding.searchView
        recycleView = binding.bookList

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText!!)
                return false
            }
        })

    return binding.root
    }

    fun search(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchRepository(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView!!.adapter = adapter
    }
}