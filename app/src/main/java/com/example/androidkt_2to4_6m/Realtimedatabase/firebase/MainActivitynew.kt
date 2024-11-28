package com.example.androidkt_2to4_6m.Realtimedatabase.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidkt_2to4_6m.R
import com.example.androidkt_2to4_6m.Realtimedatabase.firebase.model.MenuModel

class MainActivitynew : AppCompatActivity() {

    val arrayList = ArrayList<MenuModel>()
//    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)

    }

}