package com.zeroheat.keepthetime_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener
import com.zeroheat.keepthetime_20220311.adapters.AppointmentRecyclerAdapter
import com.zeroheat.keepthetime_20220311.databinding.ActivityViewMapBinding
import com.zeroheat.keepthetime_20220311.datas.AppointmentData

class ViewMapActivity : BaseActivity() {

    lateinit var binding: ActivityViewMapBinding

    lateinit var mAppointment: AppointmentData //데이터 가져온거 전역변수설정하고 아래 intent.get시리어블엑스트라 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_map)
        mAppointment = intent.getSerializableExtra("appointment") as AppointmentData
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

            val naverMap = it

//            도착지 자체를 변수화.
            val destLatLng = LatLng(mAppointment.latitude, mAppointment.longitude)
//            도착지로 카메라 이동

            val cameraUpdate = CameraUpdate.scrollTo(destLatLng)
            naverMap.moveCamera(cameraUpdate)

//            마커도 찍어주자.

            val marker = Marker()
            marker.position = destLatLng
            marker.map = naverMap
        }

    }
}