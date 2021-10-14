package com.libill.kmm.mmkv

import android.content.SharedPreferences
import com.libill.kmm.alog.ALog
import com.libill.kmm.utils.appContext
import com.tencent.mmkv.MMKV

actual class PlatformMMKV actual constructor(
    configFileName: String?,
    relativePath: String?
) {
    private var mMMKVInstance: MMKV

    init {
        MMKV.initialize(appContext)
        mMMKVInstance = if (configFileName != null || relativePath != null) {
            mmkvWithId(configFileName ?: "defaultMMKV", relativePath ?: "")
        } else {
            MMKV.defaultMMKV()
        }
    }

    private fun mmkvWithId(configName: String): MMKV {
        return MMKV.mmkvWithID(configName)
    }

    private fun mmkvWithId(configName: String, relativePath: String): MMKV {
        return MMKV.mmkvWithID(configName, relativePath)
            ?: throw Exception("create mmkv with id $configName failed")
    }

    private fun getSp(configFileName: String): MMKV {
        return mMMKVInstance
    }

    private fun getEditor(configFileName: String): SharedPreferences.Editor {
        return getSp(configFileName).edit()
    }

    actual fun putString(configFileName: String, key: String, value: String) {
        try {
            getEditor(configFileName).putString(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putString: $configFileName")
        }
    }

    actual fun putInt(configFileName: String, key: String, value: Int) {
        try {
            getEditor(configFileName).putInt(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putInt: $configFileName")
        }
    }

    actual fun putLong(configFileName: String, key: String, value: Long) {
        try {
            getEditor(configFileName).putLong(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putLong: $configFileName")
        }
    }

    actual fun putFloat(configFileName: String, key: String, value: Float) {
        try {
            getEditor(configFileName).putFloat(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putFloat: $configFileName")
        }
    }

    actual fun putBoolean(configFileName: String, key: String, value: Boolean) {
        try {
            getEditor(configFileName).putBoolean(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putBoolean: $configFileName")
        }
    }

    actual fun remove(configFileName: String, key: String) {
        try {
            getEditor(configFileName).remove(key).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "remove: $configFileName")
        }
    }

    actual fun clear(configFileName: String) {
        try {
            getEditor(configFileName).clear().commit()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "clear: $configFileName")
        }
    }

    actual fun getString(configFileName: String, key: String, defValue: String): String {
        try {
            return getSp(configFileName).getString(key, defValue) ?: defValue
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getString: $configFileName")
        }
        return defValue
    }


    actual fun getInt(configFileName: String, key: String, defValue: Int): Int {
        try {
            return getSp(configFileName).getInt(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getInt: $configFileName")
        }
        return defValue
    }

    actual fun getLong(configFileName: String, key: String, defValue: Long): Long {
        try {
            return getSp(configFileName).getLong(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getLong: $configFileName")
        }
        return defValue
    }

    actual fun getFloat(configFileName: String, key: String, defValue: Float): Float {
        try {
            return getSp(configFileName).getFloat(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getFloat: $configFileName")
        }
        return defValue
    }

    actual fun getBoolean(configFileName: String, key: String, defValue: Boolean): Boolean {
        try {
            return getSp(configFileName).getBoolean(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getBoolean: $configFileName")
        }
        return defValue
    }

    actual fun contains(configFileName: String, key: String): Boolean {
        try {
            return getSp(configFileName).contains(key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "contains: $configFileName")
        }
        return false
    }
}