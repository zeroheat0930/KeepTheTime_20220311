package com.zeroheat.keepthetime_20220311

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.adapters.MyFriendAdapter
import com.zeroheat.keepthetime_20220311.databinding.ActivityManageMyFriendBinding
import com.zeroheat.keepthetime_20220311.datas.BasicResponse
import com.zeroheat.keepthetime_20220311.datas.UserData
import com.zeroheat.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendActivity : BaseActivity() {

    lateinit var binding : ActivityManageMyFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friend)
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


    }

}