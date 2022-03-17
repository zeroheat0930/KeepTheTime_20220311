package com.zeroheat.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.zeroheat.keepthetime_20220311.databinding.ActivityEditMyPlaceBinding

class EditMyPlaceActivity : BaseActivity() {
    lateinit var binding: ActivityEditMyPlaceBinding

//    선택된 장소 저장 변수 / 마커 표시 변수 => 초기값 널 세팅
    var mSelectedPoint : LatLng? = null
    var marker : Marker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_my_place)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

            val naverMap = it

            naverMap.setOnMapClickListener { pointF, latLng ->
                if(mSelectedPoint == null){
//                    처음 지도를 클릭한 상황
//                    마커를 새로 만들어주자. => 위치 정보는? latLng 변수가 대입될 예정. 새로만들필요 X
                    marker = Marker()
                    marker!!.icon = OverlayImage.fromResource(R.drawable.red_marker_icon)
                }

                mSelectedPoint = latLng

                marker!!.position = latLng
                marker!!.map = naverMap
            }
        }

    }
}