package tech.jour.template.common.retrofit

import com.orhanobut.logger.Logger

fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultBuilder<T>.() -> Unit) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.data)
        is ApiEmptyBodyResponse -> listener.onDataEmpty(this.message)
        is ApiFailedResponse -> listener.onFailed(this.errCode, this.message, this.data)
        is ApiErrorResponse -> listener.onError(this.throwable, this.message)
    }
    listener.onComplete()
}

class ResultBuilder<T> {
    var onSuccess: (data: T) -> Unit = {}
    var onDataEmpty: (message: String?) -> Unit = {
        Logger.d(it)
    }
    var onFailed: (errorCode: Int?, errorMsg: String?, data: T?) -> Unit =
        { errorCode, errorMsg, data ->
            Logger.d("onFailed")
            errorMsg?.let {
//                LiveEventBus.get(ToastEvent::class.java)
//                    .postAcrossProcess(ToastEvent(it, ToastCCK.style.INFO))
//            ToastCCK.toast(it, ToastCCK.style.ERROR)
//            ToastCCK.error(it).show()
            }
        }
    var onError: (e: Throwable?, errorMsg: String?) -> Unit = { e, errorMsg ->
        Logger.d("onError:${errorMsg}\n ${e?.message}")
//        if (BuildConfig.DEBUG) {
//            LiveEventBus.get(ToastEvent::class.java)
//                .postAcrossProcess(
//                    ToastEvent(e?.message.toString(), ToastCCK.style.ERROR)
//                )
//        } else {
//            LiveEventBus.get(ToastEvent::class.java)
//                .postAcrossProcess(ToastEvent("网络错误", ToastCCK.style.ERROR))
//        }
    }
    var onComplete: () -> Unit = {}
}