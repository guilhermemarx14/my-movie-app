package com.guilhermemarx14.mymovieapp.lifecycle_observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.guilhermemarx14.mymovieapp.model.MovieListType

class FragmentObserver(val type: MovieListType): LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        //Log.d("movieApp", "Fragment: $type - Event: $event")
    }
}