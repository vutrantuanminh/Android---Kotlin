package com.example.studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanagement.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter(
            students = mutableListOf(),
            onItemClick = { student, position ->
                navigateToEdit(student, position)
            }
        )
        
        binding.rvStudents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@StudentListFragment.adapter
        }
    }

    private fun setupObservers() {
        viewModel.students.observe(viewLifecycleOwner) { students ->
            updateStudentList(students)
        }
    }

    private fun updateStudentList(students: List<Student>) {
        adapter = StudentAdapter(
            students = students.toMutableList(),
            onItemClick = { student, position ->
                navigateToEdit(student, position)
            }
        )
        binding.rvStudents.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            navigateToAdd()
        }
    }

    private fun navigateToAdd() {
        val action = StudentListFragmentDirections.actionListToAdd()
        findNavController().navigate(action)
    }

    private fun navigateToEdit(student: Student, position: Int) {
        val action = StudentListFragmentDirections.actionListToEdit(student, position)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
