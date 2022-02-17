package com.example.age_in_minutesapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.age_in_minutesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.buttonGetDate.setOnClickListener {
            pickDate()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun pickDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, SelectedDayOfMonth ->

                Toast.makeText(this,"Year was $selectedYear,Month was ${selectedMonth +1},Day was $SelectedDayOfMonth",Toast.LENGTH_SHORT).show()

                val selectedDate ="$selectedYear/${selectedMonth+1}/$SelectedDayOfMonth"

                binding.textViewSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time/ 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentInMinutes = currentDate.time/ 60000

                        val differenceInMinutes = currentInMinutes-selectedDateInMinutes

                        binding.ageInMinutes.text = differenceInMinutes.toString()

                    }




                }




            },

            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()



    }
}