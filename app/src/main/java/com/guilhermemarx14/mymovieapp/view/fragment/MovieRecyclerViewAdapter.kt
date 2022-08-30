package com.guilhermemarx14.mymovieapp.view.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieItemBinding
import com.guilhermemarx14.mymovieapp.interfaces.MovieSelectedListener

import com.guilhermemarx14.mymovieapp.view.fragment.placeholder.PlaceholderContent.PlaceholderItem

class MovieRecyclerViewAdapter(

    private val listener: MovieSelectedListener
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val values: MutableList<PlaceholderItem> = ArrayList()

    fun updateValues(movieList: List<PlaceholderItem>){
        values.clear()
        values.addAll(movieList)
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
        fun bindItem(item: PlaceholderItem) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }

}