package tech.jour.template

import tech.jour.template.base.mvvm.m.BaseRepository
import tech.jour.template.common.retrofit.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var mApi: ApiService

    /**
     * 模拟获取数据
     */
    suspend fun getData() = request<String> {
        delay(1000L)
        emit("Hello Hilt")
    }
}