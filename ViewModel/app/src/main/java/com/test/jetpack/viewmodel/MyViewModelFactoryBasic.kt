package com.test.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactoryBasic(private val counter: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //myViewModel 클래스를 viewmodel에 등록 가능한지 확인 (ViewModel 상속 확인작업)
        if (modelClass.isAssignableFrom(MyViewModelBasic::class.java)) {
            return MyViewModelBasic(counter) as T
        }
        throw IllegalArgumentException("ViewModel class Not found")
    }

}