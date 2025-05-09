package com.libill.kmm.mmkv

import cocoapods.MMKV.MMKV
import com.libill.kmm.alog.ALog
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class PlatformMMKV actual constructor(
    val configFileName: String?,
    val relativePath: String?
) {
    private var mmkvInstance: MMKV

    init {
        MMKV.initialize()
        MMKV.initializeMMKV(relativePath)
        val mConfigFileName = configFileName ?: "a"
        mmkvInstance = mmkvWithId(mConfigFileName, relativePath)
    }

    private fun mmkvWithId(configName: String): MMKV {
        return MMKV.mmkvWithID(configName)
            ?: throw Exception("create mmkv with id $configName failed")
    }

    private fun mmkvWithId(configName: String, relativePath: String?): MMKV {
        return MMKV.mmkvWithID(mmapID = configName, relativePath = relativePath)
            ?: throw Exception("create mmkv with id configName:$configName,relativePath:$relativePath failed")
    }

    private fun getSp(configFileName: String): MMKV {
        return mmkvInstance
    }

    actual fun putString(configFileName: String, key: String, value: String) {
        try {
            getSp(configFileName).setString(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putString: $configFileName")
        }
    }

    actual fun putInt(configFileName: String, key: String, value: Int) {
        try {
            getSp(configFileName).setInt32(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putInt: $configFileName")
        }
    }

    actual fun putLong(configFileName: String, key: String, value: Long) {
        try {
            getSp(configFileName).setInt64(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putLong: $configFileName")
        }
    }

    actual fun putFloat(configFileName: String, key: String, value: Float) {
        try {
            getSp(configFileName).setFloat(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putFloat: $configFileName")
        }
    }

    actual fun putBoolean(configFileName: String, key: String, value: Boolean) {
        try {
            getSp(configFileName).setBool(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "putBoolean: $configFileName")
        }
    }

    actual fun remove(configFileName: String, key: String) {
        try {
            getSp(configFileName).removeValueForKey(key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "remove: $configFileName")
        }
    }

    actual fun clear(configFileName: String) {
        try {
            getSp(configFileName).clearAll()
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "clear: $configFileName")
        }
    }

    actual fun getString(configFileName: String, key: String, defValue: String): String {
        try {
            return getSp(configFileName).getStringForKey(key, defValue) ?:defValue
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getString: $configFileName")
        }
        return defValue
    }

    actual fun getInt(configFileName: String, key: String, defValue: Int): Int {
        try {
            return getSp(configFileName).getInt32ForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getInt: $configFileName")
        }
        return defValue
    }

    actual fun getLong(configFileName: String, key: String, defValue: Long): Long {
        try {
            return getSp(configFileName).getInt64ForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getLong: $configFileName")
        }
        return defValue
    }

    actual fun getFloat(configFileName: String, key: String, defValue: Float): Float {
        try {
            return getSp(configFileName).getFloatForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getFloat: $configFileName")
        }
        return defValue
    }

    actual fun getBoolean(configFileName: String, key: String, defValue: Boolean): Boolean {
        try {
            return getSp(configFileName).getBoolForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "getBoolean: $configFileName")
        }
        return defValue
    }

    actual fun contains(configFileName: String, key: String): Boolean {
        try {
            return getSp(configFileName).containsKey(key)
        } catch (e: Exception) {
            e.printStackTrace()
            ALog.e(TAG, "contains: $configFileName")
        }
        return false
    }
}