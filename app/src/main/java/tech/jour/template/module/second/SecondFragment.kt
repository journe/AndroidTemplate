package tech.jour.template.module.second

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.TimeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.jour.template.base.ktx.clickDelay
import tech.jour.template.common.coroutine.launchWithLoading
import tech.jour.template.common.model.db.AccountBean
import tech.jour.template.common.ui.BaseFragment
import tech.jour.template.databinding.FragmentSecondBinding

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding, SecondViewModel>() {

	override val mViewModel: SecondViewModel by viewModels()

	override fun initView() {
		mBinding.apply {
			buttonSecond.setOnClickListener {
				findNavController().navigateUp()
			}
			updateBtn.clickDelay {
				launchWithLoading {
					mViewModel.insertDataIO(
						AccountBean(
							0, "testData",
							TimeUtils.getNowMills().toString()
						)
					)
				}
//				mViewModel.insertData(
//					AccountBean(
//						0,
//						"testData",
//						TimeUtils.getNowMills().toString()
//					)
//				)
			}
		}

	}

	override fun initObserve() {
		lifecycleScope.launch {
			mViewModel.dataFlow.collectLatest {
				mBinding.textviewSecond.text = it?.nickname
				mBinding.phoneTv.text = it?.phone
			}
		}
	}

	override fun initRequestData() {
	}

}