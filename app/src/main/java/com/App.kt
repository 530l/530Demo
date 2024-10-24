package com

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.elvishew.xlog.XLog
import com.elvishew.xlog.printer.Printer
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        isOverlayEnabled(this@App)
        val filePrinter: Printer = FilePrinter.Builder(cacheDir.absolutePath) // 指定保存日志文件的路径
            .fileNameGenerator(DateFileNameGenerator()) // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
            .backupStrategy(NeverBackupStrategy()) // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
            .build()
        XLog.init(filePrinter)

        LogUtils.getConfig()
            .setLogSwitch(true)
            .setConsoleSwitch(true)
            .setGlobalTag("abc")
            .setLogHeadSwitch(false)
            .setFilePrefix("-->")
            .setBorderSwitch(false)
            .setSingleTagSwitch(true)
    }

    companion object {
        var app: App? = null
    }

    private fun isOverlayEnabled(context: Context): Boolean {
        val contentResolver = context.contentResolver
        Toast.makeText(context, Settings.System.canWrite(context).toString(), Toast.LENGTH_SHORT)
            .show()
        return Settings.System.canWrite(context)
    }

}