package com.zeroheat.keepthetime_20220311

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.adapters.FriendViewPagerAdapter
import com.zeroheat.keepthetime_20220311.databinding.ActivityManageMyFirendBinding

class ManageMyFriendsActivity : BaseActivity() {

    lateinit var binding: ActivityManageMyFirendBinding

    lateinit var mAdapter: FriendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_firend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddFriend.setOnClickListener {

            val myIntent = Intent(mContext, SearchUserActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

        mAdapter = FriendViewPagerAdapter(supportFragmentManager)
        binding.friendViewPager.adapter = mAdapter

        binding.friendsTabLayout.setupWithViewPager( binding.friendViewPager )

    }

}