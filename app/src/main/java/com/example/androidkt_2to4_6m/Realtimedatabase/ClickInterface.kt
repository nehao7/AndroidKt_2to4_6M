package com.example.androidkt_2to4_6m.Realtimedatabase.firebase

import com.example.androidkt_2to4_6m.Realtimedatabase.firebase.model.MenuModel

interface ClickInterface {
    fun editClick(menuModel: MenuModel, position:Int)
    fun deleteClick(menuModel: MenuModel,position: Int)


}