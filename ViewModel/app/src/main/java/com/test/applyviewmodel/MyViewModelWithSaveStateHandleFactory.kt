package com.test.applyviewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class MyViewModelWithSaveStateHandleFactory(
   private val counter : Int,
   owner: SavedStateRegistryOwner,
   defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MyViewModelWithSaveStateHandle::class.java)) {
            return MyViewModelWithSaveStateHandle(counter, handle) as T
        }
        throw IllegalArgumentException("Viewmodel class not found")
    }

}