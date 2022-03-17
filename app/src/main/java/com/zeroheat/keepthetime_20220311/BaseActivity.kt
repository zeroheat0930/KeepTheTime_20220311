package com.zeroheat.keepthetime_20220311

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.zeroheat.keepthetime_20220311.api.APIList
import com.zeroheat.keepthetime_20220311.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

//    모든 화면에서, apiList 변수가 있다면 => apiList.서버기능( ) 형태로 간단히 코딩 가능.
    lateinit var apiList: APIList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create( APIList::class.java )

        supportActionBar?.let {
            setCustomActionBar()
        }
    }

    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar(){

        val defaultActionBar = supportActionBar!!

        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        defaultActionBar.setDisplayShowCustomEnabled(true) 위랑 같은코드
        defaultActionBar.setCustomView(R.layout.my_custom_actionbar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)
    }
}