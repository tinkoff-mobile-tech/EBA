package ru.tinkoff.sample_eba.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.trello.navi2.Event
import com.trello.navi2.component.support.NaviFragment
import ru.tinkoff.sample_eba.extensions.observe

open class BaseFragment(@LayoutRes private val layoutRes: Int) : NaviFragment() {

    val unBindEvent = observe(Event.DESTROY)
    val bindEvent = observe(Event.START)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutRes, container, false)
    }
}