package aop.fastcampus.part5.chapter02

import android.app.Application
import aop.fastcampus.part5.chapter02.di.appModule
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AopPart5Chapter02Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AopPart5Chapter02Application)
            modules(appModule)
        }
    }

}

/**
클린아키텍처: presentation layer, domain layer, data layer
-> 관심사 분리, 테스터블한 구조, 변경이 용이한 코드, 개방폐쇠원칙 등 객체지향 원칙 준수

presentation, data layer에서 상대적으로 추상적인 계층인 domain layer를 의존한다.
presentation layer는 viewmodel <-> usecase를 통해
data layer는 repository <-> usecase 를 통해


의문
1. viewmodel과 usecase 간의 로직을 어떻게 분리할 것인가?

해결책
usecase의 특징
    1. android 의존성 없이 수행될 수 있는 로직인가?
    2. 개발지식이 없는 타 부서가 usecase 를 보아도 해당 도메인이 무슨 일을 하는지 알 수 있어야한다.
    -> 최대한 추상화해서 표현되어야한다 (세부 구현X)

의문
2. 결국 Usecase는 Repository의 wrapper class 정도밖에 안되는데 이게 큰 의미가 있는것인가?
-> 의존 역전 원칙, 변경이 전파되는것을 막을 수 있다.
-> 커뮤니케이션의 용이성
usecase만 잘 정리해 놓아도 domain과 관련된 communication을 할 때 좋음

usecase Rule
UseCase 는 Android 와 연관된 의존성이 들어갈 수 없다.
UseCase 에 Repository 를 주입할 때는 인터페이스 타입으로 받는다.

usecase는 다른 usecase를 주입받을 수 있다.
Low level UseCase - 단일한 비즈니스 로직을 수행한다. ex/ 이미지 URL 을 받아온다.
High level UseCase - Low level UseCase 를 조합하여 비즈니스 로직을 수행한다. ex/ 댓글을 작성한다.
여기서 포인트는 High level UseCase 는 High level UseCase 를 참조해서는 안될 것이다. 순환 참조 이슈가 있고 관리가 어려워진다

참고용

tips
각 db, service 등 코인에서 의존성주입에
사용되는 클래스는 provider** 형식으로 정의해서 디버깅하기 좋네

설계순서
1. 구조 잡기
2. application, 의존성 주입위한 appmodule 작성
3. data 계층 작성, domain 작성 (repository, repo impl 뼈대)
4. base activity, fragment, viewmodel 등 프레젠테이션 layer의 base 작성
5. activity, fragment, ui 구현
6. 뷰모델, 액티비티 또는 프래그먼트에서 뷰모델을 observe한 후 상태에대한 콜백 구현


코드를 보고 느낀점
코틀린 extention, base class 를 활용하는모습이 감명깊다
코드가 간결하고 알아보기 쉽다.

room typeconverter? 뭐지
 */