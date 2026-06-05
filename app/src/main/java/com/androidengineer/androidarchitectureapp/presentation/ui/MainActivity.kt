package com.androidengineer.androidarchitectureapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidengineer.androidarchitectureapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
}