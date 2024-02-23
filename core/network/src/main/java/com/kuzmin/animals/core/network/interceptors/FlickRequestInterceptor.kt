package com.kuzmin.animals.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class FlickRequestInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "cd278152e8d3e7e92270d7ddb0d41721"
        private const val SECRET_KEY = "c5b58de84f16c5b2"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzA3MjMzMDExLCJlbWFpbCI6ImFkbWluQHRvd2Vycy5sYXduZG9nLmNvbSJ9.gb72GO8B0iYHLx6zkxdcr5qJofiOzstc3cfXKwHmC8s")
            .build()
        /*println("Token: $token")
        println("Outgoing request to: ${request.url}")
        println("Outgoing headers: ${request.headers}")*/
        return chain.proceed(request)
    }
}