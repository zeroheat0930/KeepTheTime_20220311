package com.zeroheat.keepthetime_20220311

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zeroheat.keepthetime_20220311.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

//    약속 시간 일/시 를 저장해줄 Calendar. (월 값이 0~11로 움직이게 맞춰져있다.)
    val mSelectedAppointmentDateTime = Calendar.getInstance() // 기본값 : 현재 일시


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

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

                    Toast.makeText(mContext, "${hourOfDay}시 ${minute}분 선택", Toast.LENGTH_SHORT).show()

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



    }
}