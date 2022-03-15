package com.zeroheat.keepthetime_20220311.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroheat.keepthetime_20220311.R
import com.zeroheat.keepthetime_20220311.adapters.RequestedUsersRecyclerAdapter
import com.zeroheat.keepthetime_20220311.databinding.FragmentRequestedUsersBinding
import com.zeroheat.keepthetime_20220311.datas.BasicResponse
import com.zeroheat.keepthetime_20220311.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestedUsersFragment : BaseFragment() {

    lateinit var binding: FragmentRequestedUsersBinding

    val mRquestedList = ArrayList<UserData>()

    lateinit var mRequestAdapter : RequestedUsersRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_requested_users,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
//        나에게 친구 요청한 사람 목록을 > 리싸이클러뷰로 보여주기.
//        API : getRequestFriendList 함수 -> "requested" 로 대입.

        getRequestedUsersFromServer()


        mRequestAdapter = RequestedUsersRecyclerAdapter(mContext, mRquestedList)
        binding.requestUsersRecyclerView.adapter = mRequestAdapter

        binding.requestUsersRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
    fun getRequestedUsersFromServer(){

        apiList.getRequestFriendList("requested").enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

                    mRquestedList.clear()

                    mRquestedList.addAll(response.body()!!.data.friends)

                    mRequestAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }
}