package com.zeroheat.keepthetime_20220311

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeroheat.keepthetime_20220311.api.APIList
import com.zeroheat.keepthetime_20220311.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

//    모든 화면에서, apiList 변수가 있다면 => apiList.서버기능( ) 형태로 간단히 코딩 가능.
    lateinit var apiList: APIList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create( APIList::class.java )
    }

    abstract fun setupEvents(

 //        GET - /user 접근해서, 내 정보 조회.
//        토큰값이 필요함. => 로그인 성공시 토큰 저장, ContextUtil에서 추출해서 사용.


    )

    abstract fun setValues()

}