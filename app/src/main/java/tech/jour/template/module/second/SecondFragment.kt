package tech.jour.template.module.second

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.template.R
import tech.jour.template.common.ui.BaseFragment
import tech.jour.template.databinding.FragmentSecondBinding

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding, SecondViewModel>() {

    override val mViewModel: SecondViewModel by viewModels()

    override fun FragmentSecondBinding.initView() {
        mBinding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun initObserve() {
        mViewModel.data.observe(this) {
            mBinding.textviewSecond.text = it?.nickname
        }
    }

    override fun initRequestData() {
        mViewModel.getData()
    }


}