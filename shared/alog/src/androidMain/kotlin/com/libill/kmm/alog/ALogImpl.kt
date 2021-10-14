package com.libill.kmm.alog

import com.libill.kmm.utils.appContext
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog

internal actual class ALogImpl actual constructor() : IALog {

    private val logPath = "${appContext.cacheDir}/alog"
    private val logFileName = "app_xlog"

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        initXLog()
    }

    private fun initXLog() {
        Log.setLogImp(Xlog())
        if (BuildConfig.DEBUG) {
            Log.setConsoleLogOpen(true)
            Log.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, "", logPath, logFileName, 0)
        } else {
            Log.setConsoleLogOpen(false)
            Log.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, "", logPath, logFileName, 0)
        }
    }

    override fun v(tag: String, message: String) {
        Log.v(tag, message)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun w(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

    override fun v(tag: String, format: String, vararg args: Any?) {
        Log.v(tag, format, *args)
    }

    override fun d(tag: String, format: String, vararg args: Any?) {
        Log.d(tag, format, *args)
    }

    override fun i(tag: String, format: String, vararg args: Any?) {
        Log.i(tag, format, *args)
    }

    override fun w(tag: String, format: String, vararg args: Any?) {
        Log.w(tag, format, *args)
    }

    override fun e(tag: String, format: String, vararg args: Any?) {
        Log.e(tag, format, *args)
    }

    override fun destroy() {
        Log.appenderClose()
    }
}