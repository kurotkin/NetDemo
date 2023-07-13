package com.kurotkin.netdemo.data

data class NetResource<T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): NetResource<T> {
            return NetResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): NetResource<T> {
            return NetResource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): NetResource<T> {
            return NetResource(Status.LOADING, data, null)
        }
    }
}