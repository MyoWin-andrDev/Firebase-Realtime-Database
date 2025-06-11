package com.learning.firebasehw.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.firebasehw.R
import com.learning.firebasehw.databinding.ListItemStudentBinding
import com.learning.firebasehw.model.StudentModel

class StudentAdapter (private var studentList :List<StudentModel>, private val onDeleteClick : (StudentModel) -> Unit, private val onItemLongClick : (StudentModel) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class  StudentViewHolder(val binding : ListItemStudentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) = with(binding){
            val student = studentList[position]
            tvStudentId.text = student.studentId.toString()
            tvName.text = student.studentName
            tvGrade.text = student.studentGrade
            tvRoomNo.text = student.studentRoom

            ivGender.setImageResource(
                when(student.studentGender){
                    1 -> R.drawable.male
                    0 -> R.drawable.femenine
                    else -> R.drawable.gender
                }
            )
            btnDelete.setOnClickListener {
                onDeleteClick(student)
            }
            main.setOnLongClickListener {
                onItemLongClick(student)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
        StudentViewHolder(ListItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = studentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshStudent(newStudentList : List<StudentModel>){
        this.studentList = newStudentList.toList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int){
        holder.bind(position)
    }
}