package tech.jour.template.module

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.template.common.ui.BaseFragment
import tech.jour.template.databinding.FragmentSecondBinding

@AndroidEntryPoint
class DFragment : BaseFragment<FragmentSecondBinding, DViewModel>() {

    override val mViewModel: DViewModel by viewModels()

    override fun FragmentSecondBinding.initView() {
        mBinding.buttonSecond.setOnClickListener {
        }

    }

    override fun initObserve() {
        mViewModel.data.observe(this) {
            mBinding.textviewSecond.text = it
        }
    }

    override fun initRequestData() {
        mViewModel.getData()
    }


}