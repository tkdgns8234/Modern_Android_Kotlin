package com.test.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.test.jetpack.R
import com.test.jetpack.viewmodel.savehandlestate.MyViewModel
import com.test.jetpack.viewmodel.savehandlestate.MyViewModelFactory

class DataBindingTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding_test)

        // DataBindingUtil을 통해 setContentView
        val binding = DataBindingUtil.setContentView<ActivityDataBindingTestBinding>(this, R.layout.activity_data_binding_test)

        val factory = MyViewModelFactory(10, this)
        val myViewModel by viewModels<MyViewModel>() {factory}

        // liveData 관측을 위해 lifecycleOwner를 정해준다.
        binding.lifecycleOwner = this
        // xml과 연동할 viewmodel 지정
        binding.viewModel = myViewModel

        binding.btnIncrease.setOnClickListener {
            myViewModel.liveCount.value = myViewModel.liveCount.value?.plus(1)
        }

        // 아래 코드 주석을 통해 live data를 observing하지 않음에도 불구하고 데이터 바인딩을 통해
        // 변경된 내용이 잘 표시됨
//        myViewModel.modifiedCount.observe(this) { count ->
//            binding.tvCount.text = count.toString()
//        }
    }
}