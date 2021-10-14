package com.libill.kmm.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.libill.kmm.alog.ALog
import com.libill.kmm.business.Greeting
import com.libill.kmm.business.getUser
import com.libill.kmm.business.setUser
import java.util.*

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvPlatform: TextView = findViewById(R.id.tv_platform)
        val btSaveData: TextView = findViewById(R.id.bt_save_data)
        val tvLoadData: TextView = findViewById(R.id.bt_load_data)
        val etInputName: TextView = findViewById(R.id.et_input_name)
        val tvLoadName: TextView = findViewById(R.id.tv_load_name)

        tvPlatform.text = greet()

        btSaveData.setOnClickListener {
            val userName = etInputName.text.toString()
            setUser(username = userName)
            ALog.i(TAG, "username:$userName")
        }

        tvLoadData.setOnClickListener {
            val loadName = getUser()
            tvLoadName.text = loadName
            ALog.i(TAG, "loadName: %s,  current time(milliseconds): %s", loadName, "${Date().time}")
        }
    }
}
