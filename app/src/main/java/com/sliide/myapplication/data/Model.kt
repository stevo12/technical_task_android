package com.sliide.myapplication.data

import com.google.gson.annotations.SerializedName

data class BaseResponseModel(
    @SerializedName("code") val code: Int,
    @SerializedName("meta") val metadata: MetaDataModel,
    @SerializedName("data") val data: List<UserResponseModel>
)

data class MetaDataModel(
    @SerializedName("pagination") val pagination: PaginationModel
)

data class PaginationModel(
    @SerializedName("total") val total: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("page") val currentPage: Int,
    @SerializedName("limit") val limit: Int
)

data class UserResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("created_at") val creationTime: String?,
    @SerializedName("updated_at") val updatedTime: String?
)
