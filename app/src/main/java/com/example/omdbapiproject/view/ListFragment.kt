package com.example.omdbapiproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omdbapiproject.databinding.FragmentListBinding
import com.example.omdbapiproject.model.MovieModel
import com.example.omdbapiproject.util.Constants.MOVIE
import com.example.omdbapiproject.viewmodel.MovieViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(), RecyclerViewAdapter.Listener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var recyclerViewAdapter = RecyclerViewAdapter(arrayListOf(),this)
    private val viewModel by viewModel<MovieViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.movieRecyclerView.layoutManager = layoutManager
        //viewModel = ViewModelProvider(this).get(MovieViewModel::class.java) //lifecycle aware
        //binding.movieSearchView.queryHint = "pulp"
        viewModel.getDataFromAPI(MOVIE)
        binding.movieSearchText.setText(MOVIE)
        observeLiveData()

        /*binding.movieSearchView.setOnQueryTextFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
           viewModel.getDataFromAPI()
        })
        binding.movieSearchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                viewModel.getDataFromAPI(binding.movieSearchView.queryHint.toString())
            }
        }*/
        binding.button.setOnClickListener{
            viewModel.getDataFromAPI(binding.movieSearchText.text.toString())
        }


    }

    private fun observeLiveData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer{movies ->
            binding.movieRecyclerView.visibility = View.VISIBLE
            binding.movieErrorText.visibility =View.GONE
            binding.movieProgressBar.visibility = View.GONE
            recyclerViewAdapter = RecyclerViewAdapter(ArrayList(movies.data?.Search ?: arrayListOf()),this@ListFragment)
            binding.movieRecyclerView.adapter = recyclerViewAdapter

        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer{error ->
            if(error.data == true){
                binding.movieErrorText.visibility = View.VISIBLE
                binding.movieRecyclerView.visibility = View.GONE
            } else{
              binding.movieErrorText.visibility = View.GONE
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer{loading ->
            if(loading.data == true){
                binding.movieProgressBar.visibility = View.VISIBLE
                binding.movieRecyclerView.visibility = View.GONE
                binding.movieErrorText.visibility = View.GONE
            }else{
                binding.movieProgressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movieModel: MovieModel) {
        Toast.makeText(requireContext(), "Clicked : ${movieModel.Title}", Toast.LENGTH_LONG).show()
    }

}