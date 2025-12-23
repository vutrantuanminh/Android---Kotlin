package com.example.studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanagement.databinding.FragmentAddStudentBinding
import java.util.UUID

class AddStudentFragment : Fragment() {

    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: StudentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {
            saveStudent()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun saveStudent() {
        val mssv = binding.etMssv.text.toString().trim()
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()

        // Validation
        if (mssv.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(
                requireContext(),
                R.string.error_all_fields_required,
                Toast.LENGTH_SHORT
            ).show()
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

        // Add to ViewModel
        viewModel.addStudent(newStudent)

        // Show success message
        Toast.makeText(
            requireContext(),
            R.string.success_added,
            Toast.LENGTH_SHORT
        ).show()

        // Navigate back
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
