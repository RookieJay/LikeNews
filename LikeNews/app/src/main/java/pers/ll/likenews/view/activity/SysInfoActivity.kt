package pers.ll.likenews.view.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.telephony.TelephonyManager
import kotlinx.android.synthetic.main.activity_system_info.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Battery
import pers.ll.likenews.model.SystemInfo
import pers.ll.likenews.utils.FileUtils
import pers.ll.likenews.utils.FormatUtils
import pers.ll.likenews.utils.MacUtils
import pers.ll.likenews.utils.ScreenManager





class SysInfoActivity : AppCompatActivity() {

    private var mList = ArrayList<SystemInfo>()
    private lateinit var mAdapter : SysInfoAdapter
    private lateinit var battery : Battery
    private var mReceiver = BatteryReceiver()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_info)
        registerReceiver(mReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        initData()
        initView()
    }

    private fun initView() {
        mAdapter = SysInfoAdapter(this, mList)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private fun initData() {
        addTitle("基本信息")
        addContentText("品牌:", Build.MANUFACTURER)
        addContentText("型号:", Build.MODEL)
        addContentText("CPU:", Build.CPU_ABI)
        addContentText("分辨率:", ScreenManager.getInstance(this).w.toString() + " x " + ScreenManager.getInstance(this).h)
        addContentText("IMEI:", (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId)
        addContentText("MAC:", MacUtils.getMac(this))

        addTitle("存储信息")
        addContentText("总大小:", FormatUtils.formatSize(FileUtils.getSDSize(0)) + "")
        addContentText("可用大小:", FormatUtils.formatSize(FileUtils.getSDSize(1)) + "")
        addContentText(
            "运行内存:",
            FormatUtils.formatSize(FileUtils.getAvailMemory(this, 0))
                    + " / " +
                    FormatUtils.formatSize(FileUtils.getAvailMemory(this, 1))
        )

        addTitle("电池")
        if (battery != null) {
            addContentText("类型", battery.technology)
            addContentText("电量", battery.level.toString())
            addContentText("总量", battery.scale.toString())
            addContentText("温度", String.format("%d°", battery.temperature))
            addContentText("健康状态", battery.healthStr)
            addContentText("充电状态", battery.statusStr)
        }

        addTitle("OS")
        addContentText("Android版本：", Build.VERSION.SDK_INT.toString())

        addContentText("内核版本", FileUtils.getLinuxVersion())

        addTitle("传输")
        addContentText("NFC", if (packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)) "支持" else "不支持")

        addTitle("传感器")
    }

    private fun addTitle(title : String) {
        val model = SystemInfo()
        model.type = Const.Type.SYS_INFO_TYPE_TITLE
        model.title = title
        mList.add(model)
    }

    private fun addContentText(key: String, value: String) {
        val model = SystemInfo()
        model.type = Const.Type.SYS_INFO_TYPE_CONTENT
        model.key = key
        model.value = value
        mList.add(model)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    inner class BatteryReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                val bundle = intent.extras
                val current = bundle.getInt("level") // 获得当前电量
                val total = bundle.getInt("scale") // 获得总电量
                val temperature = bundle.getInt("temperature") //温度
                val technology = bundle.getString("technology") //电池类型
                val health = bundle.getInt("health") //电池的健康状态
                val status = bundle.getInt("status") //电池的充电状态
                battery = Battery()
                battery.level = current
                battery.scale = total
                battery.technology = technology
                battery.temperature = temperature
                battery.health = health
                battery.status = status
            }

        }

    }
}
