package com.example.myapplication;

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class Datas : ViewModel() {
    private var ip : String = ""
    private var data = MutableLiveData<String>()


    fun setadresseIP(s : String): LiveData<String> {
        Log.d("test",s+" old :"+ data.value)
        if(s == ip)
            return data

        ip = s

        Thread(){

                var url = URL("${s}")
                Log.d("test",url.toString())
                var conn = url.openConnection() as HttpURLConnection
                conn.requestMethod= "GET"
                var reponseCode = conn.responseCode

                if(reponseCode == HttpURLConnection.HTTP_OK)
                {
                    var inputStream = conn.inputStream
                    var inputStreamAdapte = BufferedReader(InputStreamReader(inputStream))
                    var stringBuild : StringBuilder = StringBuilder()
                    var line :String?
                    while (inputStreamAdapte.readLine().also { line = it } != null)
                    {
                        stringBuild.append(line)
                    }
                    data.postValue(stringBuild.toString())
                }
                conn.disconnect()
        }.start()
        return data
    }

    fun getadresseIP()=ip
    fun getJsondata()=data


}

