package com.example.mynotification

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val phone: String,
    val works: String
)

data class Work(
    val workName: String
) {
    companion object {
        private var lastGeneratedId = 0
    }

    val id: Int = ++lastGeneratedId

}

data class EmployeesData(
    val id: Int,
    val name: String,
    val phone: String,
    val works: ArrayList<Work>
)




