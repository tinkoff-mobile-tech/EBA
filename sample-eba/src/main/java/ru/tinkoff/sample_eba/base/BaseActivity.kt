package ru.tinkoff.sample_eba.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.trello.navi2.Event
import com.trello.navi2.component.support.NaviAppCompatActivity
import ru.tinkoff.sample_eba.extensions.observe

@SuppressLint("Registered")
open class BaseActivity(@LayoutRes private val layoutRes: Int) : NaviAppCompatActivity() {

    val unBindEvent = observe(Event.DESTROY)
    val bindEvent = observe(Event.START)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }
}