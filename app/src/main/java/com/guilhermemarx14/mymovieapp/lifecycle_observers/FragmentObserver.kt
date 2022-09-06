package com.guilhermemarx14.mymovieapp.lifecycle_observers

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class FragmentObserver: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("movieApp", "Fragment: ${source.javaClass.name} - Event: $event")
    }
}