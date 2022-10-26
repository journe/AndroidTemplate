package tech.jour.template.module

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.jour.template.base.ktx.launchIO
import tech.jour.template.base.mvvm.vm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DViewModel @Inject constructor(private val mRepository: DRepository) : BaseViewModel() {

	val data = MutableLiveData<String>()

	/**
	 * 模拟获取数据
	 */
	fun getData() {
		launchIO {
			mRepository.getData()
		}
	}
}