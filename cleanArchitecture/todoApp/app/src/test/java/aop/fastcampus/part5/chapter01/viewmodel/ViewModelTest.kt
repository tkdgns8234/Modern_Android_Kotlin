package aop.fastcampus.part5.chapter01.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import aop.fastcampus.part5.chapter01.di.appTestModule
import aop.fastcampus.part5.chapter01.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal abstract class ViewModelTest: KoinTest {

    @get:Rule // Mockito를 사용한다는 규칙 설정
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    /*
    InstantTaskExecutorRule을 이용하면 안드로이드 구성요소 관련 작업들을 모두 한 스레드에서 실행되게 한다.
    그러므로 모든 작업이 synchronous하게 동작하여 테스팅을 원활하게 할 수 있다.
    특히 LiveData를 이용한다면 필수적으로 InstantTaskExecutorRule를 사용해야할 것이다.
     */

    @Mock
    private lateinit var context: Application // 실제 구현체가 없는 껍데기 mock 객체

    private val dispatcher = TestCoroutineDispatcher() // 테스트용 코루틴 디스패처

    @Before // 테스트 실행 전
    fun setup() { 
        startKoin { // koin으로 의존성 주입
            androidContext(context)
            modules(appTestModule)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        stopKoin() // koin 중단
        Dispatchers.resetMain() // MainDispatcher를 초기화 해주어야 메모리 누수가 발생하지 않음
    }
    
    // LiveDataTestObserver 클래스 이용 테스트
    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver) //life cycle 과 관계없이 항상 옵저빙하도록 함

        return testObserver
    }

}
