package io.ecosed.framework.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.system.Os
import android.util.Log
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.PermissionUtils
import com.farmerbb.taskbar.lib.Taskbar
import io.ecosed.framework.BuildConfig
import io.ecosed.framework.EcosedFramework
import io.ecosed.framework.R
import io.ecosed.framework.utils.ChineseCaleUtils
import io.ecosed.framework.utils.EnvironmentUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rikka.shizuku.Shizuku

class EcosedService : Service() {

    private val poem: ArrayList<String> = arrayListOf(
        "不向焦虑与抑郁投降，这个世界终会有我们存在的地方。",
        "把喜欢的一切留在身边，这便是努力的意义。",
        "治愈、温暖，这就是我们最终幸福的结局。",
        "我有一个梦，也许有一天，灿烂的阳光能照进黑暗森林。",
        "如果必须要失去，那么不如一开始就不曾拥有。",
        "我们的终点就是与幸福同在。",
        "孤独的人不会伤害别人，只会不断地伤害自己罢了。",
        "如果你能记住我的名字，如果你们都能记住我的名字，也许我或者我们，终有一天能自由地生存着。",
        "对于所有生命来说，不会死亡的绝望，是最可怕的审判。",
        "我不曾活着，又何必害怕死亡。"
    )

    override fun onCreate() {
        super.onCreate()
        setupNotificationChannel()
        startForeground(notificationId, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return object : EcosedFramework.Stub() {
            override fun getFrameworkVersion(): String = frameworkVersion()
            override fun getShizukuVersion(): String = shizukuVersion()
            override fun getAndroidVersion(): String = systemVersion()
            override fun getKernelVersion(): String = Os.uname().release
            override fun getSystemVersion(): String = Os.uname().version
            override fun getMachineArch(): String = Os.uname().machine
            override fun isWatch(): Boolean = EnvironmentUtils.isWatch(this@EcosedService)
            override fun isUseDynamicColors(): Boolean  = true
            override fun isUseDesktopMode(): Boolean = true
            override fun openTaskbarSettings() = Taskbar.openSettings(this@EcosedService, "", R.style.Theme_EcosedFramework_ActionBar)
            override fun openEcosedSettings() = ecosedSettings()
            override fun getChineseCale(): String = ChineseCaleUtils.getChineseCale()
            override fun getOnePoem(): String = poem[(poem.indices).random()]
            override fun execCmd(cmd: String?) {}
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun ecosedSettings() {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }

    private fun frameworkVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    private fun shizukuVersion(): String {
        return try {
            "Shizuku ${Shizuku.getVersion()}"
        } catch (e: Exception) {
            Log.getStackTraceString(e)
        }
    }

    private fun systemVersion(): String {
        return when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.N -> "Android Nougat 7.0"
            Build.VERSION_CODES.N_MR1 -> "Android Nougat 7.1"
            Build.VERSION_CODES.O -> "Android Oreo 8.0"
            Build.VERSION_CODES.O_MR1 -> "Android Oreo 8.1"
            Build.VERSION_CODES.P -> "Android Pie 9"
            Build.VERSION_CODES.Q -> "Android Q 10"
            Build.VERSION_CODES.R -> "Android R 11"
            Build.VERSION_CODES.S -> "Android S 12"
            Build.VERSION_CODES.S_V2 -> "Android Sv2 12.1"
            Build.VERSION_CODES.TIRAMISU -> "Android Tiramisu 13"
            34 -> "Android UpsideDownCake 14"
            else -> "unknown"
        }
    }

    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        // 创建一个通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, getString(R.string.app_name), importance).apply {
                description = "descriptionText"
            }
            // 在系统中注册通知渠道
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionUtils.permission(Manifest.permission.POST_NOTIFICATIONS)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("应用正在运行")
            .setSmallIcon(R.drawable.baseline_keyboard_command_key_24)
            .build()

        notification.flags = Notification.FLAG_ONGOING_EVENT

        return notification
    }

    companion object {
        private const val channelId = "id"
        private const val notificationId = 1
    }
}