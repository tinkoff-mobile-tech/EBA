package ru.tinkoff.sample_eba.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event
import ru.tinkoff.sample_eba.extensions.observe

open class BaseFragment(@LayoutRes private val layoutRes: Int) : Fragment() {

    val unBindEvent = observe(Event.ON_DESTROY)
    val bindEvent = observe(Event.ON_START)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutRes, container, false)
    }
}