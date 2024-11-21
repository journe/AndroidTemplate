package tech.jour.template.base.mvvm.v

import android.content.res.Resources
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import tech.jour.template.base.mvvm.vm.BaseViewModel
import tech.jour.template.base.utils.*
import tech.jour.template.base.utils.network.AutoRegisterNetListener
import tech.jour.template.base.utils.network.NetworkStateChangeListener
import tech.jour.template.base.utils.network.NetworkTypeEnum

/**
 * Activity基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity(),
    FrameView, NetworkStateChangeListener {

    protected lateinit var mBinding: VB

    protected abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ARouter 依赖注入
//        ARouter.getInstance().inject(this)

        setStatusBar()
        createBinding()
        initView()
        initNetworkListener()
        initObserve()
        initRequestData()
    }

    private fun createBinding() {
        val clazzBD: Class<VB> = TUtil.getClazz<Class<VB>>(this, 0)
        //ViewBinding
        mBinding = clazzBD.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as VB
        setContentView(mBinding.root)
    }

    /**
     * 初始化网络状态监听
     * @return Unit
     */
    private fun initNetworkListener() {
        lifecycle.addObserver(AutoRegisterNetListener(this))
    }

    /**
     * 设置状态栏
     * 子类需要自定义时重写该方法即可
     * @return Unit
     */
    open fun setStatusBar() {}

    /**
     * 网络类型更改回调
     * @param type Int 网络类型
     * @return Unit
     */
    override fun networkTypeChange(type: NetworkTypeEnum) {}

    /**
     * 网络连接状态更改回调
     * @param isConnected Boolean 是否已连接
     * @return Unit
     */
    override fun networkConnectChange(isConnected: Boolean) {
        toast(if (isConnected) "网络已连接" else "网络已断开")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getResources(): Resources {
        // 主要是为了解决 AndroidAutoSize 在横屏切换时导致适配失效的问题
        // 但是 AutoSizeCompat.autoConvertDensity() 对线程做了判断 导致Coil等图片加载框架在子线程访问的时候会异常
        // 所以在这里加了线程的判断 如果是非主线程 就取消单独的适配
//        if (Looper.myLooper() == Looper.getMainLooper()) {
//            AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()))
//        }
        return super.getResources()
    }
}