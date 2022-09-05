package com.guilhermemarx14.mymovieapp.view.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieItemBinding
import com.guilhermemarx14.mymovieapp.interfaces.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.model.MovieItemList

class MovieRecyclerViewAdapter(

    private val listener: MovieSelectedListener
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private var values: List<MovieItemList> = ArrayList()

    fun updateValues(hqList: List<MovieItemList>){
        Log.d("teste","updateValues")
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
        fun bindItem(item: MovieItemList) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }

}