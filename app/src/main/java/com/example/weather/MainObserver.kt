package com.example.weather

import android.util.Log
import androidx.lifecycle.*

/**
 * Author by CYN, Date on 2021/12/31
 */
class MainObserver() : DefaultLifecycleObserver{

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("MainObserver","onCreate()")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e("MainObserver","onStart()")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("MainObserver","onResume()")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("MainObserver","onPause()")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e("MainObserver","onStop()")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e("MainObserver","onDestroy()")
    }

}