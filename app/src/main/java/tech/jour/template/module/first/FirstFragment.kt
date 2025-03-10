package tech.jour.template.module.first

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.jour.template.R
import tech.jour.template.base.mvvm.vm.EmptyViewModel
import tech.jour.template.common.ui.BaseFragment
import tech.jour.template.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding, EmptyViewModel>() {

	override val mViewModel: EmptyViewModel by viewModels()

	override fun initView() {
		mBinding.buttonFirst.setOnClickListener {
			findNavController().navigate(R.id.SecondFragment)
		}
	}

	override fun initObserve() {
	}

	override fun initRequestData() {
	}
}