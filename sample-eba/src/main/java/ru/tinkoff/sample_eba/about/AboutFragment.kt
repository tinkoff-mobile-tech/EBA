package ru.tinkoff.sample_eba.about

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding3.view.clicks
import ru.tinkoff.eba.R
import ru.tinkoff.sample_eba.about.di.AboutComponent
import ru.tinkoff.sample_eba.base.BaseFragment

class AboutFragment : BaseFragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val component = AboutComponent(
            setVersionName = { { view.findViewById<TextView>(R.id.appVersion).text = it } },
            openPdfAction = { (url, _) -> { openPdf(url) } },
            resources = resources
        )
        val events = AboutEventsImpl(
            unbindEvent = unBindEvent,
            bindEvent = bindEvent,
            openPolicyPrivacyEvent = view.findViewById<LinearLayout>(R.id.confidentialityPolicy).clicks(),
            openProcessingPersonalDataEvent = view.findViewById<LinearLayout>(R.id.personalDataProtection).clicks()
        )

        component.binder().bind(events)
    }

    private fun openPdf(fileName: String) {
        Toast.makeText(context, fileName, Toast.LENGTH_LONG).show()
    }
}