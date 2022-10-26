package tech.jour.template.common.coroutine

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.jour.template.common.retrofit.ApiResponse
import tech.jour.template.common.retrofit.ResultBuilder
import tech.jour.template.common.retrofit.parseData
import tech.jour.template.common.ui.IUiView

fun <T> launchFlow(
    requestBlock: suspend () -> ApiResponse<T>,
    startCallback: (() -> Unit)? = null,
    completeCallback: (() -> Unit)? = null,
): Flow<ApiResponse<T>> {
    return flow {
        emit(requestBlock())
    }.onStart {
        startCallback?.invoke()
    }.onCompletion {
        completeCallback?.invoke()
    }
}

/**
 * 这个方法只是简单的一个封装Loading的普通方法，不返回任何实体类
 */
fun IUiView.launchWithLoading(requestBlock: suspend () -> Unit) {
    lifecycleScope.launch(BaseCoroutinesException()) {

        flow {
            emit(requestBlock())
        }.onStart {
            showLoading()
        }.onCompletion {
            dismissLoading()
        }
            .collect()
    }
}

/**
 * 请求不带Loading&&不需要声明LiveData
 */
fun <T> IUiView.launchAndCollect(
    requestBlock: suspend () -> ApiResponse<T>,
    listenerBuilder: ResultBuilder<T>.() -> Unit
) {
    lifecycleScope.launch {
        launchFlow(requestBlock).collect { response ->
            response.parseData(listenerBuilder)
        }
    }
}

/**
 * 请求带Loading&&不需要声明LiveData
 */
fun <T> IUiView.launchWithLoadingAndCollect(
    requestBlock: suspend () -> ApiResponse<T>,
    listenerBuilder: ResultBuilder<T>.() -> Unit
) {
    lifecycleScope.launch {
        launchFlow(requestBlock, { showLoading() }, { dismissLoading() }).collect { response ->
            response.parseData(listenerBuilder)
        }
    }
}

fun <T> Flow<ApiResponse<T>>.launchAndCollectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    listenerBuilder: ResultBuilder<T>.() -> Unit,
) {
    if (owner is Fragment) {
        owner.viewLifecycleOwner.lifecycleScope.launch {
            owner.viewLifecycleOwner.lifecycle.whenStateAtLeast(minActiveState) {
                collectLatest { apiResponse: ApiResponse<T> ->
                    apiResponse.parseData(listenerBuilder)
                }
            }
        }
    } else {
        owner.lifecycleScope.launch {
            owner.lifecycle.whenStateAtLeast(minActiveState) {
                collectLatest { apiResponse: ApiResponse<T> ->
                    apiResponse.parseData(listenerBuilder)
                }
            }
        }
    }
}



