package tech.jour.template.module.second

import dagger.hilt.android.lifecycle.HiltViewModel
import tech.jour.template.base.ktx.launchIO
import tech.jour.template.base.mvvm.vm.BaseViewModel
import tech.jour.template.common.model.db.AccountBean
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(private val mRepository: SecondRepository) :
	BaseViewModel() {


	val allUserFlow = mRepository.getAllUsers()

	fun insertData(bean: AccountBean) {
		launchIO { mRepository.insertData(bean) }
	}

	fun insertDataIO(bean: AccountBean) = mRepository.insertData(bean)

	fun getUserById(i: Int) = mRepository.getDataFlow(i)

}