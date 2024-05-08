package com.example.mynotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mynotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var employeeViewModel: EmployeeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.getDatabase(this)

        val repository =  EmployeeRepository(database.employeeDao())

        employeeViewModel = EmployeeViewModel(repository)


        var workList : ArrayList<Work> = arrayListOf()

        workList.add(Work("trolly"))
        workList.add(Work("cleaning"))
        workList.add(Work("opratore"))
        workList.add(Work("driver"))

        val data = EmployeesData(0,"jack" , "123456" , workList)

        employeeViewModel.insertEmployee(data)
        employeeViewModel.fetchCategories()

//        employeeViewModel.getAllEmployees().observe(this ,{employee->
//
//            Log.e("TAGGG", "employee: $employee" )
//            Log.e("TAGGG", "onCreate: ${employee[0].works}" )
//
//        })

        employeeViewModel.allCategories.observe(this,{employee->
            Log.e("TAGGG", "employee: $employee" )
            Log.e("TAGGG", "onCreate: ${employee[0].works}" )

        })

    }
}