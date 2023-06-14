package com.example.inhilenttest.api

import com.example.inhilenttest.data.DataList
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getProducts(): DataList {
        return apiService.getProducts();
    }
}