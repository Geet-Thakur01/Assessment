package com.example.inhilenttest.screen

import android.net.Uri
import com.example.inhilenttest.data.Product


const val DATA="data"
sealed class Screens(val route:String){
    object ProductList:Screens(route = "productList")
    object Details:Screens(route = "details")
    object Favorites:Screens(route = "Favorites")
}
