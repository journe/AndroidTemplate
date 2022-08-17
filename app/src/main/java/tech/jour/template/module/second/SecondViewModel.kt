package tech.jour.template.module.second

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.jour.template.base.ktx.launchIO
import tech.jour.template.base.mvvm.vm.BaseViewModel
import tech.jour.template.common.model.db.AccountBean
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(private val mRepository: SecondRepository) :
    BaseViewModel() {

    val data = MutableLiveData<AccountBean>()

    /**
     * 模拟获取数据
     */
    fun getData() {
        launchIO {
            data.postValue(mRepository.getData())
        }
    }
}