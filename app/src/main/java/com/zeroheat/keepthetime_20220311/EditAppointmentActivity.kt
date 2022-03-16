package com.zeroheat.keepthetime_20220311

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import com.zeroheat.keepthetime_20220311.databinding.ActivityEditAppointmentBinding
import com.zeroheat.keepthetime_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

//    약속 시간 일/시 를 저장해줄 Calendar. (월 값이 0~11로 움직이게 맞춰져있다.)
    val mSelectedAppointmentDateTime = Calendar.getInstance() // 기본값 : 현재 일시

//    약속 장소 관련 멤버변수.
    var marker : Marker? = null // 지도에 표시될 하나의 마커. 처음에는 찍지않은 상태.

    var mSelectedLatLng : LatLng? = null // 약속 장소 위/경도도 처음에는 설정하지 않은 상태.


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
//        binding.naverMapView.onCreate(savedInstanceState)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {
//        저장 버튼이 눌리면
        binding.btnSave.setOnClickListener {

//            입력값들이 제대로 되어있는지? 확인 => 잘못되었따면 막아주자. (input validation)

            val inputTitle = binding.edtTitle.text.toString()

//           제목을 입력하지 않았다면 거부.(예시)
            if(inputTitle.isEmpty()){

                Toast.makeText(mContext, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

//            시간을 선택하지 않았다면 막자.
//            기준? txtDate, txtTime 두개의 문구중 하나라도 처음 문구 그대로면 입력 안했다고 간주

            if(binding.txtDate.text == "약속 일자"){
                Toast.makeText(mContext,"일자를 선택 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.txtTime.text == "약속 시간"){
                Toast.makeText(mContext,"시간을 선택 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//              선택한 일시가, 지금보다 이전의 일시라면 "현재 이후의 시간으로 선택해주세요."

            val now = Calendar.getInstance() // 저장버튼을 누른 현재 시간.

            if(mSelectedAppointmentDateTime.timeInMillis < now.timeInMillis){
                Toast.makeText(mContext,"현재 이후의 시간으로 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            장소 이름도 반드시 입력하게.
            val inputplaceName = binding.edtPlaceName.text.toString()
            if(inputplaceName.isEmpty()){
                Toast.makeText(mContext,"장소 이름을 기입해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            장소를 선택했는지? 안했다면 등록 거부

            if(mSelectedLatLng == null){

                Toast.makeText(mContext, "약속 장소를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            Log.d("선택한약속장소 - 위도", "위도 : ${mSelectedLatLng!!.latitude}")
            Log.d("선택한약속장소 - 경도", "경도 : ${mSelectedLatLng!!.longitude}")

//            약속일시 - yyyy-MM-dd HH:mm 양식을 서버가 지정해서 요청.

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")

            apiList.postRequestAddAppointment(
                inputTitle,
                sdf.format( mSelectedAppointmentDateTime.time ),
                inputplaceName,
                mSelectedLatLng!!.latitude,
                mSelectedLatLng!!.longitude,

                ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })


        }


//        날짜 선택 텍스트뷰 클릭 이벤트 - DatePickerDialog

        binding.txtDate.setOnClickListener {

            val dsl = object : DatePickerDialog.OnDateSetListener {

                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    연/월/일은, JAVA / Kotlin 언어의 기준 (0~11월)으로 월 값을 줌. (사람은 1~12월)
//                    주는 그대로 Calendar에 set 하게되면, 올바른 월로 세팅됨.

                    mSelectedAppointmentDateTime.set( year, month, dayOfMonth ) // 연월일 한번에 세팅 함수.

//                    약속일자의 문구를 22/03/08 등의 형식으로 바꿔서 보여주자.
//                    SimpleDateFormat을 활용 => 월값도 알아서 보정.

                    val sdf = SimpleDateFormat("yy/MM/dd")

                    binding.txtDate.text =  sdf.format(mSelectedAppointmentDateTime.time)

                }

            }

            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedAppointmentDateTime.get( Calendar.YEAR ),
                mSelectedAppointmentDateTime.get( Calendar.MONTH ),
                mSelectedAppointmentDateTime.get( Calendar.DAY_OF_MONTH )
            ).show()

        }

//        시간 선택 텍스트뷰 클릭 이벤트 - TimePickDialog

        binding.txtTime.setOnClickListener {

            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {

//                    약속 일시의 시간으로 설정.

                    mSelectedAppointmentDateTime.set( Calendar.HOUR_OF_DAY, hourOfDay )
                    mSelectedAppointmentDateTime.set( Calendar.MINUTE, minute )

                    val sdf = SimpleDateFormat("a h시 m분")
                    binding.txtTime.text = sdf.format(mSelectedAppointmentDateTime.time)
                }

            }

            val tpd = TimePickerDialog(
                mContext,
                tsl,
                18,
                0,
                false
            ).show()

        }

    }

    override fun setValues() {
//        네이버 지도 객체 얻어오기 => 얻어와지면 할 일 (Interface) 코딩

        binding.naverMapView.getMapAsync {

//            지도 로딩이 끝나고 난 후에 얻어낸 온전한 지도 객체
            val naverMap = it

//            지도 시작지점 : 학원 위/경도
            val coord = LatLng( 37.57547882650325, 127.22420159469945 )

//            coord 에 설정한 좌표로 > 네이버지도의 카메라 이동.

            val cameraUpdate = CameraUpdate.scrollTo( coord )

            naverMap.moveCamera( cameraUpdate )

//           첫 마커 좌표 -> 집

//            val marker = Marker() => 멤버변수의 하나의 마커만 만들어서 관리하자.
//            marker = Marker()
//            marker!!.position = coord
//            marker!!.map = naverMap

//            처음 선택된 좌표 -> 집
//            mSelectedLatLng = coord

//            지도 클릭 이벤트

            naverMap.setOnMapClickListener { pointF, latLng ->

//                Log.d("클릭된 위/경도", "위도 : ${latLng.latitude}, 경도 :${latLng.longitude} " )

//               (찍혀있는 마커가 없다면) 마커를 새로 추가
                if(marker == null){
                    marker = Marker()
                }
//                그 마커의 위치 / 지도 적용
                marker!!.position = latLng
                marker!!.map = naverMap

//                약속 장소도 새 좌표로 설정.
                mSelectedLatLng = latLng

            }

        }



    }
}