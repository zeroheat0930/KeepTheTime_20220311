package com.zeroheat.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.adapters.MainViewPagerAdapter
import com.zeroheat.keepthetime_20220311.databinding.ActivityMainBinding
import com.zeroheat.keepthetime_20220311.datas.BasicResponse
import com.zeroheat.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var mAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mAdapter = MainViewPagerAdapter( supportFragmentManager )
        binding.mainViewPager.adapter = mAdapter

    }
}