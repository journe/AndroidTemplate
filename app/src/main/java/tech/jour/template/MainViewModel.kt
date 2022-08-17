package tech.jour.template

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import tech.jour.template.base.ktx.launchIO
import tech.jour.template.base.mvvm.vm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mRepository: MainRepository) : BaseViewModel() {

	val data = MutableLiveData<String>()

	/**
	 * 模拟获取数据
	 */
	fun getData() {
		launchIO {
			mRepository.getData()
				.catch { Log.d("qqq", "getData: $it") }
				.collect { data.postValue(it) }
		}
	}
}