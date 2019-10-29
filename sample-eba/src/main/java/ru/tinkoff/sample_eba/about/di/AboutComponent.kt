package ru.tinkoff.sample_eba.about.di

import android.content.res.Resources
import ru.tinkoff.eba.actioncreators.ActionMapper
import ru.tinkoff.eba.actioncreators.toSimpleActionCreator
import ru.tinkoff.sample_eba.R
import ru.tinkoff.sample_eba.about.AboutEventsBinder
import ru.tinkoff.sample_eba.about.OpenPdfActionCreator

class AboutComponent (
    private val setVersionName: ActionMapper<String>,
    private val openPdfAction: ActionMapper<Pair<String, String>>,
    private val resources: Resources
) {

    fun binder(): AboutEventsBinder {
        val policyPrivacyUrl = resources.getString(R.string.privacyUrlPath)
        val openPolicyPrivacy = OpenPdfActionCreator(openPdfAction, policyPrivacyUrl)
        val personalDataUrl = resources.getString(R.string.eulaUrlPath)
        val openProcessingPersonalData = OpenPdfActionCreator(openPdfAction, personalDataUrl)
        val setVersionName = setVersionName.toSimpleActionCreator { "1.0" }
        return AboutEventsBinder(setVersionName, openPolicyPrivacy, openProcessingPersonalData)
    }
}