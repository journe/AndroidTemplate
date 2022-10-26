package tech.jour.template.common.ui

import androidx.lifecycle.LifecycleOwner

interface IUiView : LifecycleOwner {

    fun showLoading()

    fun dismissLoading()
}