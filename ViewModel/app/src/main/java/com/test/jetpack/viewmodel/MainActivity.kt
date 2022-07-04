package com.test.jetpack.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.test.jetpack.databinding.ActivityMainBinding
import com.test.jetpack.viewmodel.savehandlestate.MyViewModel
import com.test.jetpack.viewmodel.savehandlestate.MyViewModelFactory

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
//        useViewModelFactoryWithSaveState()
        useLiveData()
    }

    private fun useLiveData() {
        val factory = MyViewModelFactory(100, this)
        val myViewModel by viewModels<MyViewModel>() {factory}

        binding.btnIncrease.setOnClickListener {
            // ?.plus(1) -> null이 아니면 더한다
            myViewModel.liveCount.value = myViewModel.liveCount.value?.plus(1)
        }
        //livedata가 plus 되면 observe 하고있기떄문에 변화가 감지되고 아래 구문이 실행된다.
        // {}중괄호 안의 구문은 람다리터럴 인데
        // observe의 두번째 파라미터인 인터페이스의 함수를 구현한 내용을 람다로 전달하는것으로 보인다.
        // 코틀린 어렵네 ^^;
        myViewModel.liveCount.observe(this) { count ->
            binding.tvCount.text = count.toString()
        }
        //여튼 이렇게 되면 리스너 안에 둘 필요가 없어진다!

        // 아래는 Transformations로 LiveData 값 변경 코드
//        myViewModel.modifiedCount.observe(this) { count ->
//            binding.tvCount.text = count.toString()
//        }
    }

    private fun useViewModelFactoryWithSaveState() {
        // 시스템에 의한 종료 시에도 데이터 보존됨 savestatehandle을 통해
        val factory = MyViewModelFactory(100, this)
        val myViewModel by viewModels<MyViewModel>() {factory}

        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
            myViewModel.saveState()
        }
    }

    private fun useViewModelFactoryWithByKeword() {
        val factory = MyViewModelFactoryBasic(100)
        // sdk에 viewmodel 생성 위임
        val myViewModel by viewModels<MyViewModel>() {factory}
        binding.tvCount.text = myViewModel.count.toString()

        binding.btnIncrease.setOnClickListener {
            myViewModel.count += 1
            binding.tvCount.text = myViewModel.count.toString()
        }
    }

    private fun useViewModelFactoryCounter() {
        val factory = MyViewModelFactoryBasic(100)
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