package com.guilhermemarx14.mymovieapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieItemBinding
import com.guilhermemarx14.mymovieapp.interfaces.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.model.MovieListItem

class MovieRecyclerViewAdapter(

    private val listener: MovieSelectedListener
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private var values: List<MovieListItem> = ArrayList()

    fun updateValues(hqList: List<MovieListItem>){
        Log.d("movieApp","updateValues")
        values = hqList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bindItem(item)
        holder.viewRoot.setOnClickListener { listener.onItemSelected(position) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val viewRoot = binding.root
        fun bindItem(item: MovieListItem) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }

}