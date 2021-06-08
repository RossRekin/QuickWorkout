package pu.fmi.quickworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pu.fmi.quickworkout.databinding.ActivityBMIBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BMIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBMIBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        binding = ActivityBMIBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        val dbHelper  = DbHelper(this)

        binding.btnCalculate.setOnClickListener {
            val weight: Float = binding.etWeight.text.toString().toFloat()
            val height: Float = binding.etHeight.text.toString().toFloat() / 100
            val bmi: Float = weight / (height * height)
            val bmiValue =
                BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString().toFloat()



            displayBmiResult(bmiValue)
            val currentDateTime = LocalDateTime.now()
            val date: String = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString()

            val bmiRecord: BMIRecord = BMIRecord(weight,height,binding.tvBMIStatus.text.toString(),bmiValue,date)
            dbHelper.addRecord(bmiRecord)

        }

        binding.btnHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    private fun displayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }
        binding.tvYourBmi.text = "Your BMI is"
        binding.tvBMIStatus.text = bmiLabel
        binding.tvDescription.text = bmiDescription
        binding.tvResult.text = bmi.toString()
    }
}