package tech.jour.template.common.retrofit

import java.io.Serializable

open class ApiResponse<T>(
    open val data: T? = null,
    open val errCode: Int? = null,
    open val message: String? = null,
    open val throwable: Throwable? = null,//这个error是协程抛出的异常
) : Serializable {
    val isSuccess: Boolean
        get() = errCode == 0 || errCode == null

    override fun toString(): String {
        return "ApiResponse(data=$data, errorCode=$errCode, message=$message, error=$throwable)"
    }
}

data class ApiSuccessResponse<T>(override val data: T, override val message: String?) :
    ApiResponse<T>()

data class ApiEmptyBodyResponse<T>(
    override val errCode: Int? = null,
    override val message: String? = null
) : ApiResponse<T>()

data class ApiFailedResponse<T>(
    override val errCode: Int?,
    override val message: String?,
    override val data: T? = null
) : ApiResponse<T>()

data class ApiErrorResponse<T>(override val throwable: Throwable?) : ApiResponse<T>()