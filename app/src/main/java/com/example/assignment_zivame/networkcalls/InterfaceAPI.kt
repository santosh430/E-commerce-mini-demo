package com.example.assignment_zivame.networkcalls

import com.example.assignment_zivame.networkcalls.dataclassapi.DataClassAPI
import retrofit2.Response
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("/nancymadan/assignment/db")
    suspend fun getProducts():Response<DataClassAPI>
}