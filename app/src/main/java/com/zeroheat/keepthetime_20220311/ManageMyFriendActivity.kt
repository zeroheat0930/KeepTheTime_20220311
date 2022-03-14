package com.zeroheat.keepthetime_20220311

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

    val mFriendList = ArrayList<UserData>()

    lateinit var mAdapter: MyFriendAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getMyFriendListFromServer()


        mAdapter = MyFriendAdapter( mContext, R.layout.my_friend_list_item, mFriendList )
        binding.myFriendsListView.adapter = mAdapter

    }

    fun getMyFriendListFromServer() {

        apiList.getRequestFriendList(
            ContextUtil.getLoginUserToken(mContext),
            "my"  // 수락 완료된 친구목록만 불러오기
        ).enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

//                    br.data.friends는 UserData 목록으로 이미 내려옴.
//                    목록의 내용물을 통째로 => mFriendList 변수의 내용물로 담자.

                    mFriendList.addAll( br.data.friends )

//                    어댑터 새로 고침
                    mAdapter.notifyDataSetChanged()

                }


            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}