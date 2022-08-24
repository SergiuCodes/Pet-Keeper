package com.example.petkeeper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.petkeeper.R
import com.example.petkeeper.databinding.PetListFragmentLayoutBinding

class PetListFragment : Fragment() {

    private lateinit var mBinding: PetListFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.pet_list_fragment_layout, container, false)

        with(mBinding) {
            lifecycleOwner = this@PetListFragment
            executePendingBindings()
        }

        return mBinding.root
    }
}