package com.test.applyviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.test.applyviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //lazy: 호출 시 초기화됨, by 키워드: 대행 시키는 키워드
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        simpleCounter()
//        useViewModelCounter()
//        useViewModelFactoryCounter()
//        useViewModelFactoryWithByKeword()
        useViewModelFactoryWithSaveState()
    }

    private fun useViewModelFactoryWithSaveState() {
        // 시스템에 의한 종료 시에도 데이터 보존됨 savestatehandle을 통해
        val factory = MyViewModelWithSaveStateHandleFactory(100, this)
        val myViewModel by viewModels<MyViewModelWithSaveStateHandle>() {factory}

        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
        }
    }

    private fun useViewModelFactoryWithByKeword() {
        val factory = MyViewModelFactory(100)
        // sdk에 viewmodel 생성 위임
        val myViewModel by viewModels<MyViewModel>() {factory}
        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
        }
    }

    private fun useViewModelFactoryCounter() {
        val factory = MyViewModelFactory(100)
        val myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
        }
    }

    private fun useViewModelCounter() {
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.count = 100
        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
        }
    }

    private fun simpleCounter() {
        // 화면 전환 시 activity 생명주기가 다시 시작되므로 count 값이 저장되지 않음
        var count = 100
        binding.tvCount.text = count.toString()
        binding.btnIncrease.setOnClickListener {
            count += 1
            binding.tvCount.text = count.toString()
        }
    }

}