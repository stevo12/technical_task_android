package com.sliide.myapplication.networking

import com.sliide.myapplication.data.BaseResponseModel
import com.sliide.myapplication.data.UserResponseModel
import com.sliide.myapplication.networking.GoRestClient.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.http.*

interface GoRestService {

    @GET(BASE_URL)
    fun getUsers(): Observable<BaseResponseModel>

    @GET(BASE_URL)
    fun getUserFromLastPage(@Query("page") pageNumber: Int): Observable<BaseResponseModel>

    @POST(BASE_URL)
    fun createNewUser(@Body userResponseModel: UserResponseModel): Observable<BaseResponseModel>

    @DELETE("$BASE_URL/{id}")
    fun deleteUser(@Path("id") id: Int)
}