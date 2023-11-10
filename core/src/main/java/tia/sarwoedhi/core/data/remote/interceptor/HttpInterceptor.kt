package tia.sarwoedhi.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(addHeader(chain.request()))
    }

    //
    //if failed please change the bearerToken in your account
    private fun addHeader(oriRequest: Request): Request {
        return oriRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjY4ZjVlZDYzNGQ1ZTk3YWRlZWFlZGU5MWU4OTU3NCIsInN1YiI6IjVjYTlhYWQ4YzNhMzY4M2Y0YTYxMGIzMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wiwSL79-XLdWSM2bvHuVCHWE1vvaZ3Tkc6E8MD2UNZ8")
            .build()
    }
}