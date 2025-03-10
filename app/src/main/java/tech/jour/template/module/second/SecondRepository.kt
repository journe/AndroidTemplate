package tech.jour.template.module.second

import tech.jour.template.base.mvvm.m.BaseRepository
import tech.jour.template.common.model.db.AccountBean
import tech.jour.template.common.retrofit.ApiService
import tech.jour.template.common.room.AppDatabase
import tech.jour.template.common.room.dao.AccountDao
import javax.inject.Inject

class SecondRepository @Inject constructor() : BaseRepository() {

	@Inject
	lateinit var mApi: ApiService

	@Inject
	lateinit var accountDao: AccountDao

	/**
	 * 模拟获取数据
	 */
	fun getData() = accountDao.getUserById(0)

	fun getDataFlow() = accountDao.getUserByIdFlow(0)

	fun insertData(bean: AccountBean) = accountDao.insert(bean)
}