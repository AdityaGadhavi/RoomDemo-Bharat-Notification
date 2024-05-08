package com.example.mynotification

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository) : ViewModel() {

    val allCategories: LiveData<List<EmployeesData>> = repository.allCategories

    fun insertEmployee(employee: EmployeesData) {
        viewModelScope.launch {
            repository.insertEmployee(employee)
        }
    }


    fun fetchCategories() {
        viewModelScope.launch {
            repository.fetchCategories()
        }
    }

//    fun getAllEmployees(): LiveData<List<Employee>> {
//        return repository.getAllEmployees()
//    }
}
