package com.example.studentmanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: MutableList<Student>,
    private val onItemClick: (Student, Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvStudentName)
        val tvMssv: TextView = itemView.findViewById(R.id.tvStudentMssv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvMssv.text = "MSSV: ${student.mssv}"

        holder.itemView.setOnClickListener {
            onItemClick(student, position)
        }
    }

    override fun getItemCount(): Int = students.size

    fun addStudent(student: Student) {
        students.add(student)
        notifyItemInserted(students.size - 1)
    }

    fun updateStudent(student: Student, position: Int) {
        if (position >= 0 && position < students.size) {
            students[position] = student
            notifyItemChanged(position)
        }
    }

    fun removeStudent(position: Int) {
        if (position >= 0 && position < students.size) {
            students.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
