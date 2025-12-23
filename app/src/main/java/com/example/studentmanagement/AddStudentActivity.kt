package com.example.studentmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.databinding.ActivityAddStudentBinding
import java.util.UUID

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {
            saveStudent()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveStudent() {
        val mssv = binding.etMssv.text.toString().trim()
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()

        // Validation
        if (mssv.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, R.string.error_all_fields_required, Toast.LENGTH_SHORT).show()
            return
        }

        // Create new student
        val newStudent = Student(
            id = UUID.randomUUID().toString(),
            name = name,
            mssv = mssv,
            phone = phone,
            address = address
        )

        // Return result to MainActivity
        val resultIntent = Intent()
        resultIntent.putExtra("NEW_STUDENT", newStudent)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
