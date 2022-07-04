package com.test.jetpack.viewmodel.savehandlestate

import androidx.lifecycle.*

class MyViewModel(
    _count: Int,
    private val saveStateHandle: SavedStateHandle
) :ViewModel(){
    // live data 사용
    var liveCount : MutableLiveData<Int> = MutableLiveData(_count)
    
    //Transformations로 LiveData 값 변경
    val modifiedCount : LiveData<String> = Transformations.map(liveCount) {count ->
        "$count 입니다."
    }

    // 2way data binding
    // xml UI 에서 값이 변경되면 이 variable 값이 자동으로 변경됨
    // xml 에선 @={ } 를 사용
    var hasChecked : MutableLiveData<Boolean> = MutableLiveData(false)

    var count = saveStateHandle.get<Int>(SAVE_STATE_KEY) ?: _count

    fun saveState() {
        saveStateHandle.set(SAVE_STATE_KEY, count)
    }

    companion object {
        private const val SAVE_STATE_KEY = "counter"
    }
}