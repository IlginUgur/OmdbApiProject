package com.example.omdbapiproject.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.omdbapiproject.databinding.RecyclerRowBinding
import com.example.omdbapiproject.model.MovieModel
import java.net.URL
import com.squareup.picasso.Picasso


class RecyclerViewAdapter (val movieList : ArrayList<MovieModel>, val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(movieModel : MovieModel)
    }

    class RowHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClick(movieList[position])
            val action = ListFragmentDirections.actionListFragmentToSecondFragment(movieList[position].imdbID.toString())
            Navigation.findNavController(it).navigate(action)
        }
     //   holder.binding.movieImageView.setImageResource(movieList[position].image.toInt())
        holder.binding.movieTextView.text = movieList[position].Title
        holder.binding.movieYearText.text = movieList[position].Year
        Picasso.get().load(movieList[position].Poster).into(holder.binding.movieImageView)

        //val newurl = URL(movieList[position].Poster)
        //holder.binding.movieImageView.setImageBitmap(mIcon_val)
        //holder.binding.movieImageView.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));
    }
}