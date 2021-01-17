package ru.tinkoff.sample_eba.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.Event
import ru.tinkoff.sample_eba.extensions.observe

@SuppressLint("Registered")
open class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    val unBindEvent = observe(Event.ON_DESTROY)
    val bindEvent = observe(Event.ON_START)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }
}