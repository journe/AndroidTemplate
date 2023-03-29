package tech.jour.template.common.retrofit

import android.util.Log
import okhttp3.*
import java.io.IOException

object OkHttpUtil {

    fun getHttpRequset(url: String) {
        val request = Request.Builder().url(url).get().build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            /*请求失败时的回调*/
            override fun onFailure(call: Call, e: IOException) {
                Log.e("onFailure: ", e.message!!)
            }

            /*请求成功时的回调*/
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.e("ApiData:", response.body!!.string())
            }
        })
    }
}

