package com.test.jetpack.databinding

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter


// app:progressScaled 속성이 값을 전달받을 때마다
// bindingAdapter 에서 아래 함수가 호출되게 됨
// 파라미터는 값을 전달하는 view, 그리고 xml 속성값을 입력
@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)  // app:progressScaled", "android:max 두 속성을 모두 관찰
fun setProgress(progressBar: ProgressBar, count: Int, max: Int) {
    var value = (count * 2).coerceAtMost(max)
    println(value)
    progressBar.progress = (count * 2).coerceAtMost(max)
}