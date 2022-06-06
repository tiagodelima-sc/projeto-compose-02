package com.example.projetocompose02

import android.app.Application
import com.example.projetocompose02.data.SchoolDatabase

class SchoolApplication: Application() {
    val schooldatabase: SchoolDatabase by lazy {
        SchoolDatabase.getInstance(this)
    }
}