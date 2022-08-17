package tech.jour.template.common.di

import tech.jour.template.common.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 全局作用域的Home组件网络接口代理依赖注入模块
 *
 * @author Qu Yunshuo
 * @since 6/4/21 5:51 PM
 */
@Module
@InstallIn(SingletonComponent::class)
class DINetServiceModule {

    /**
     * Home模块的[ApiService]依赖提供方法
     *
     * @param retrofit Retrofit
     * @return HomeApiService
     */
    @Singleton
    @Provides
    fun provideHomeApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}