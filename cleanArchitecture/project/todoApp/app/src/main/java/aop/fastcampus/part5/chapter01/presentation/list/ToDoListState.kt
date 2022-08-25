package aop.fastcampus.part5.chapter01.presentation.list

import aop.fastcampus.part5.chapter01.data.entity.ToDoEntity

sealed class ToDoListState {

    //sealed 키워드: 해당 파일을 제외한 나머지 파일에선 이 클래스를 상속하는 클래스가 없도록 제한
    // object 키워드로 선언 시 추후에 싱글톤 인스턴스로 생성됨
    object UnInitialized: ToDoListState()

    object Loading: ToDoListState()

    data class Suceess(
        val toDoList: List<ToDoEntity>
    ): ToDoListState()

    object Error: ToDoListState()

}

// enum은 싱글톤처럼 하위 객체를 하나만 생성 가능

// class, data class, object 타입만 올 수 있음

// sealed class 를 이용하는 경우 enum 과 다르게 여러방식으로 각 요소들을 초기화할 수 있다는 장점이 있다.!!

// 하위클래스를 제한하기에 when 절 사용 시 else 구문을 이용하지 않아도 되는 장점도 있음