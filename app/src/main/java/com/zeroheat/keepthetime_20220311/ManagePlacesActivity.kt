package com.zeroheat.keepthetime_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.databinding.ActivityManagePlacesBinding
import com.zeroheat.keepthetime_20220311.datas.BasicResponse
import com.zeroheat.keepthetime_20220311.datas.PlaceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagePlacesActivity : BaseActivity() {

    lateinit var binding:ActivityManagePlacesBinding

    val mPlaceList = ArrayList<PlaceData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_places)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {



    }

    override fun onResume() {
        super.onResume()
        getMyplacesFromServer()
    }

    fun getMyplacesFromServer(){

        apiList.getRequestUserPlaces().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!

                    mPlaceList.clear()

                    mPlaceList.addAll(br.data.places)

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}