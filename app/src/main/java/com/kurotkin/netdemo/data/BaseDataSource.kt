package com.kurotkin.netdemo.data

import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NetResource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return NetResource.success(body)
            }
            return NetResource.error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return NetResource.error(e.message ?: e.toString())
        }
    }
}