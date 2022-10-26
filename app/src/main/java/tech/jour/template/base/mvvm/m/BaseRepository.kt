package tech.jour.template.base.mvvm.m

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tech.jour.template.BuildConfig
import tech.jour.template.common.retrofit.*

/**
 * 仓库层 Repository 基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
open class BaseRepository {

    /**
     * 发起请求封装
     * 该方法将flow的执行切换至IO线程
     *
     * @param requestBlock 请求的整体逻辑
     * @return Flow<T>
     */
    protected fun <T> request(requestBlock: suspend FlowCollector<T>.() -> Unit): Flow<T> {
        return flow(block = requestBlock).flowOn(Dispatchers.IO)
    }


    suspend fun <T> executeHttp(block: suspend () -> ApiResponse<T>): ApiResponse<T> {
        runCatching {
            block.invoke()
        }.onSuccess { data: ApiResponse<T> ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return ApiEmptyBodyResponse()
//        return  block.invoke()
    }

    /**
     * 非后台返回错误，捕获到的异常
     */
    private fun <T> handleHttpError(e: Throwable): ApiErrorResponse<T> {
        if (BuildConfig.DEBUG) e.printStackTrace()
//        handlingExceptions(e)
        return ApiErrorResponse(e)
    }

    /**
     * 返回200，但是还要判断isSuccess
     */
    private fun <T> handleHttpOk(data: ApiResponse<T>): ApiResponse<T> {
        return if (data.isSuccess) {
            getHttpSuccessResponse(data)
        } else {
//            handlingApiExceptions(data.errorCode, data.errorMsg)
            ApiFailedResponse(data.errCode, data.message, data.data)
        }
    }

    /**
     * 成功,数据为空的处理
     */
    private fun <T> getHttpSuccessResponse(response: ApiResponse<T>): ApiResponse<T> {
        val data = response.data
        return if (data == null || data is List<*> && (data as List<*>).isEmpty()) {
            ApiEmptyBodyResponse(
                message = response.message,
                errCode = response.errCode
            )
        } else {
            ApiSuccessResponse(data, response.message)
        }
    }
}