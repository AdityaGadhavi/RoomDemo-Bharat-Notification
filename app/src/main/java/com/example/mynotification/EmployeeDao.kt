package com.example.mynotification

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Query("SELECT * FROM employee_table")
    fun getAllEmployees(): List<Employee>

    @Query("DELETE FROM employee_table WHERE id = :employeeId")
    suspend fun deleteEmployee(employeeId: Int)

    @Query("SELECT * FROM employee_table WHERE name LIKE '%' || :name || '%'")
    fun searchEmployeesByName(name: String): List<Employee>

    

}
