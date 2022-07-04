package com.test.applyviewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyViewModelWithSaveStateHandle(
    _count: Int,
    private val saveStateHandle: SavedStateHandle
) :ViewModel(){
    var count = saveStateHandle.get<Int>(SAVE_STATE_KEY) ?: _count

    fun saveState() {
        saveStateHandle.set(SAVE_STATE_KEY, count)
    }

    companion object {
        private const val SAVE_STATE_KEY = "counter"
    }
}