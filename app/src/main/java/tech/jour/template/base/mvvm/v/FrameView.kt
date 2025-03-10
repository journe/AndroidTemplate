package tech.jour.template.base.mvvm.v

/**
 * View层基类抽象
 *
 *
 * @since 10/13/20
 */
interface FrameView {

    /**
     * 初始化 View
     */
    fun initView()

    /**
     * 订阅LiveData
     */
    fun initObserve()

    /**
     * 用于在页面创建时进行请求接口
     */
    fun initRequestData()
}