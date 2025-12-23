package com.example.studentmanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    
    private val _students = MutableLiveData<MutableList<Student>>()
    val students: LiveData<MutableList<Student>> = _students
    
    init {
        // Initialize with sample data
        _students.value = mutableListOf(
            Student("1", "Nguyễn Văn A", "20210001", "0901234567", "Hà Nội"),
            Student("2", "Trần Thị B", "20210002", "0912345678", "Hồ Chí Minh"),
            Student("3", "Lê Văn C", "20210003", "0923456789", "Đà Nẵng")
        )
    }
    
    fun addStudent(student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList.add(student)
        _students.value = currentList
    }
    
    fun updateStudent(student: Student, position: Int) {
        val currentList = _students.value ?: return
        if (position >= 0 && position < currentList.size) {
            currentList[position] = student
            _students.value = currentList
        }
    }
    
    fun deleteStudent(position: Int) {
        val currentList = _students.value ?: return
        if (position >= 0 && position < currentList.size) {
            currentList.removeAt(position)
            _students.value = currentList
        }
    }
    
    fun getStudent(position: Int): Student? {
        return _students.value?.getOrNull(position)
    }
}
