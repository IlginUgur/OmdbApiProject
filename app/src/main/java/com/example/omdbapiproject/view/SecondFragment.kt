package com.example.omdbapiproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.omdbapiproject.R
import com.example.omdbapiproject.databinding.FragmentListBinding
import com.example.omdbapiproject.databinding.FragmentSecondBinding
import com.example.omdbapiproject.viewmodel.MovieDetailViewModel
import com.example.omdbapiproject.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel by  viewModel<MovieDetailViewModel>() //viewModel<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val imdbID = SecondFragmentArgs.fromBundle(it).imdbID
            viewModel.getDetailFromAPI(imdbID)
            observeLiveData()
        }

    }

    private fun observeLiveData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer { movie ->
           movie.data?.let {
               binding.movieDetailTitle.text = it.Title
               binding.movieDetailActors.text = it.Actors
               binding.movieDetailCountry.text = it.Country
               binding.movieDetailDirector.text = it.Director
               binding.movieDetailYear.text = it.Year
               binding.movieDetailImdbRating.text = "Imdb Rating:" + it.Ratings[0].Value
               Picasso.get().load(it.Poster).into(binding.movieDetailImage)
           }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}