package com.learning.firebasehw.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.firebasehw.R
import com.learning.firebasehw.databinding.ListItemStudentBinding
import com.learning.firebasehw.model.StudentModel

class StudentAdapter(
    private val studentList : List<StudentModel>,
    private val onDeleteClick : (StudentModel) -> (Unit),
    private val onItemClick : (StudentModel) -> (Unit)
) :RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder = StudentViewHolder(ListItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int = studentList.size

    inner class StudentViewHolder(val binding : ListItemStudentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(student : StudentModel) = with(binding){
            tvName.text = student.studentName
            tvStudentId.text = student.studentId.toString()
            tvGrade.text = student.studentGrade
            tvRoom.text = student.studentRoom
            ivGender.setImageResource(if(student.studentGender == 1) R.drawable.male else R.drawable.femenine)
        }
    }
}