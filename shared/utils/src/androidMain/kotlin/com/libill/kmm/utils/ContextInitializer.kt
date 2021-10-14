package com.libill.kmm.utils

import android.content.Context
import androidx.startup.Initializer

lateinit var appContext: Context

class ContextInitializer : Initializer<Context> {

    override fun create(context: Context): Context {
        appContext = context.applicationContext
        return context.applicationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}