package aop.fastcampus.part5.chapter01

import android.app.Application
import aop.fastcampus.part5.chapter01.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AopPart5Chapter01Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR) // koin 의 로그 레벨을 지정
            androidContext(this@AopPart5Chapter01Application) // context 등록
            modules(appModule) // 의존성을 주입할 클래스(모듈) 을 지정
        }

    }

}

// TDD를 기반으로한 클린 아키텍처 앱 만들기 (테스트가 주가되는 앱 제작)
// koin 이용해서 의존성 주입
// TDD 를 기반으로 비즈니스 로직(뷰모델 + 유스케이스) 완성시키고 UI 작업


// 각 패키지
// usecase 구현을 위한 domain 패키지 생성
// data 패키지 생성
// presentation 패키지: 액티비티나 뷰, 비즈니스로직이 들어가는 뷰모델이 존재
// di: 의존성 주입을 위한 class (koin을 활용)

// 테스트 코드는 비즈니스로직을 테스트하기 위해 뷰모델과 동일한 패키지를 만들어 각 모듈별로 테스트를 수행

//domain레이어 에서 data레이어로 단방향 이동

// viewModelScope viewModel이 destroyed 되면 같이 파괴됨