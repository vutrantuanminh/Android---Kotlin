package com.example.studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentmanagement.databinding.FragmentEditStudentBinding

class EditStudentFragment : Fragment() {

    private var _binding: FragmentEditStudentBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: StudentViewModel by activityViewModels()
    private val args: EditStudentFragmentArgs by navArgs()
    
    private var isEditMode = false
    private lateinit var currentStudent: Student
    private var position: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        currentStudent = args.student
        position = args.position
        
        displayStudentInfo()
        setupClickListeners()
    }

    private fun displayStudentInfo() {
        binding.etMssv.setText(currentStudent.mssv)
        binding.etName.setText(currentStudent.name)
        binding.etPhone.setText(currentStudent.phone)
        binding.etAddress.setText(currentStudent.address)
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
            Toast.makeText(
                requireContext(),
                R.string.error_all_fields_required,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Update student object
        val updatedStudent = Student(
            id = currentStudent.id,
            name = name,
            mssv = currentStudent.mssv,
            phone = phone,
            address = address
        )

        // Update via ViewModel
        viewModel.updateStudent(updatedStudent, position)

        Toast.makeText(
            requireContext(),
            R.string.success_updated,
            Toast.LENGTH_SHORT
        ).show()

        // Navigate back
        findNavController().navigateUp()
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.confirm_delete_title)
            .setMessage(R.string.confirm_delete)
            .setPositiveButton(R.string.yes) { _, _ ->
                deleteStudent()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    private fun deleteStudent() {
        viewModel.deleteStudent(position)
        
        Toast.makeText(
            requireContext(),
            R.string.success_deleted,
            Toast.LENGTH_SHORT
        ).show()

        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
