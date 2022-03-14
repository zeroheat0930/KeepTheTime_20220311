package com.zeroheat.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.databinding.ActivityManageMyFriendBinding

class ManageMyFriendActivity : BaseActivity() {

    lateinit var binding : ActivityManageMyFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}