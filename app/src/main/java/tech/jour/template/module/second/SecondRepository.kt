package tech.jour.template.module.second

import tech.jour.template.base.mvvm.m.BaseRepository
import tech.jour.template.common.retrofit.ApiService
import tech.jour.template.common.room.AppDatabase
import javax.inject.Inject

class SecondRepository @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var mApi: ApiService

    @Inject
    lateinit var database: AppDatabase

    /**
     * 模拟获取数据
     */
    fun getData() = database.accountDao().getUserById(0)
}