package aop.fastcampus.part5.chapter02.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

internal abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>: AppCompatActivity() {

    abstract val viewModel: VM // viewmodel 구현을 강제하는 역할

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB // viewbinding 구현을 강제하는 역할

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }

}
