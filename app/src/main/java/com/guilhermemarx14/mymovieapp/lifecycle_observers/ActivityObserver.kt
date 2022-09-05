package com.guilhermemarx14.mymovieapp.lifecycle_observers

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class ActivityObserver: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("teste", "Activity: ${source.javaClass.name} - Event: $event")
    }
}