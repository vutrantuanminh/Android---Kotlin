package com.example.studentmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.databinding.ActivityStudentDetailBinding

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailBinding
    private lateinit var student: Student
    private var position: Int = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get student data from intent
        student = intent.getParcelableExtra<Student>("STUDENT") ?: run {
            finish()
            return
        }
        position = intent.getIntExtra("POSITION", -1)

        displayStudentInfo()
        setupClickListeners()
    }

    private fun displayStudentInfo() {
        binding.etMssv.setText(student.mssv)
        binding.etName.setText(student.name)
        binding.etPhone.setText(student.phone)
        binding.etAddress.setText(student.address)
    }

    private fun setupClickListeners() {
        binding.btnEdit.setOnClickListener {
            if (isEditMode) {
                saveChanges()
            } else {
                enableEditMode()
            }
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmation()
        }
    }

    private fun enableEditMode() {
        isEditMode = true
        binding.etName.isEnabled = true
        binding.etPhone.isEnabled = true
        binding.etAddress.isEnabled = true
        binding.btnEdit.text = getString(R.string.btn_save)
        binding.btnEdit.setIconResource(android.R.drawable.ic_menu_save)
    }

    private fun disableEditMode() {
        isEditMode = false
        binding.etName.isEnabled = false
        binding.etPhone.isEnabled = false
        binding.etAddress.isEnabled = false
        binding.btnEdit.text = getString(R.string.btn_edit)
        binding.btnEdit.setIconResource(android.R.drawable.ic_menu_edit)
    }

    private fun saveChanges() {
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()

        // Validation
        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, R.string.error_all_fields_required, Toast.LENGTH_SHORT).show()
            return
        }

        // Update student object
        student.name = name
        student.phone = phone
        student.address = address

        // Return result
        val resultIntent = Intent()
        resultIntent.putExtra("UPDATED_STUDENT", student)
        resultIntent.putExtra("POSITION", position)
        resultIntent.putExtra("ACTION", "UPDATE")
        setResult(RESULT_OK, resultIntent)

        Toast.makeText(this, R.string.success_updated, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(this)
            .setTitle(R.string.confirm_delete_title)
            .setMessage(R.string.confirm_delete)
            .setPositiveButton(R.string.yes) { _, _ ->
                deleteStudent()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    private fun deleteStudent() {
        val resultIntent = Intent()
        resultIntent.putExtra("POSITION", position)
        resultIntent.putExtra("ACTION", "DELETE")
        setResult(RESULT_OK, resultIntent)

        Toast.makeText(this, R.string.success_deleted, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
