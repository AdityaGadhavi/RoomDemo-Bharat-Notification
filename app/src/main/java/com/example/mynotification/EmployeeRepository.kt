package com.example.mynotification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    private val gson = Gson()
    private val allCategoriesLiveData: LiveData<List<EmployeesData>> = MediatorLiveData()

    suspend fun insertEmployee(employee: EmployeesData) {

        val employeeEntity = Employee(
            id = employee.id,
            name = employee.name,
            phone = employee.phone,
            works = gson.toJson(employee.works)
        )
        employeeDao.insertEmployee(employeeEntity)
    }


    suspend fun fetchCategories() {
        withContext(Dispatchers.IO) {
            val categoryEntities = employeeDao.getAllEmployees()
            val categoriesList = categoryEntities.map { employee ->
                EmployeesData(
                    id = employee.id,
                    name = employee.name,
                    phone = employee.phone,
                    works = gson.fromJson(
                        employee.works,
                        object : TypeToken<ArrayList<Work>>() {}.type
                    )
                )
            }
            (allCategoriesLiveData as MediatorLiveData).postValue(categoriesList)
        }
    }

    val allCategories: LiveData<List<EmployeesData>> = allCategoriesLiveData


//    fun getAllEmployees(): LiveData<List<Employee>> {
//        return employeeDao.getAllEmployees()
//    }



}
