package com.manuelarestrepo.adoptapp.ui.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.manuelarestrepo.adoptapp.R
import java.text.DateFormat
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar:Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            activity, // Context
            // Put 0 to system default theme or remove this parameter
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this, // DatePickerDialog.OnDateSetListener
            year, // Year
            month, // Month of year
            day // Day of month
        )
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        Toast.makeText(
            activity,
            "Fecha seleccionada: ${formatDate(year, month, day)}"
            , Toast.LENGTH_SHORT
        ).show()

        // Display the selected date in text view
        activity.findViewById<TextView>(R.id.fechaNacimiento_button).text =
            formatDate(year, month, day)
    }


    // Custom method to format date
    private fun formatDate(year:Int, month:Int, day:Int):String{
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time

        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }
}