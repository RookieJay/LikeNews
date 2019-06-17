package pers.ll.likenews.view.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_system_info.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseActivity
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Battery
import pers.ll.likenews.model.SystemInfo
import pers.ll.likenews.utils.FileUtils
import pers.ll.likenews.utils.FormatUtils
import pers.ll.likenews.utils.MacUtils
import pers.ll.likenews.utils.ScreenManager





class SysInfoActivity : BaseActivity() {

    private var mList = ArrayList<SystemInfo>()
    private lateinit var mAdapter : SysInfoAdapter
    private var battery : Battery = Battery()
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
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        barTitle.text = "系统信息"
        mAdapter = SysInfoAdapter(this, mList)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
        barLeft.setOnClickListener { finish() }
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



        addTitle("OS")
        addContentText("Android版本：", Build.VERSION.RELEASE)

        addContentText("内核版本：", FileUtils.getLinuxVersion())

        addTitle("传输")
        addContentText("NFC", if (packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)) "支持" else "不支持")

        addTitle("传感器")
        //从系统获得传感器管理器
        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val allSensors = sm.getSensorList(Sensor.TYPE_ALL)
        for (sensor in allSensors) {
            when(sensor.type) {
                //加速传感器     Sensor.TYPE_ACCELEROMETER
                Sensor.TYPE_ACCELEROMETER -> addContentText("加速传感器:", sensor.name)
                //陀螺仪传感器    Sensor.TYPE_GYROSCOPE
                Sensor.TYPE_GYROSCOPE -> addContentText("陀螺仪传感器:", sensor.name)
                //环境光仪传感器   Sensor.TYPE_LIGHT
                Sensor.TYPE_LIGHT -> addContentText("环境光仪传感器:", sensor.name)
                //电磁场传感器    Sensor.TYPE_MAGNETIC_FIELD
                Sensor.TYPE_MAGNETIC_FIELD -> addContentText("电磁场传感器:", sensor.name)
                //方向传感器    Sensor.TYPE_ORIENTATION:
                Sensor.TYPE_ORIENTATION -> addContentText("方向传感器:", sensor.name)
                //压力传感器     Sensor.TYPE_PRESSURE:
                Sensor.TYPE_PRESSURE -> addContentText("压力传感器:", sensor.name)
                //距离传感器     Sensor.TYPE_PROXIMITY:
                Sensor.TYPE_PROXIMITY -> addContentText("距离传感器:", sensor.name)
                //温度传感器     Sensor.TYPE_TEMPERATURE:
                Sensor.TYPE_TEMPERATURE -> addContentText("温度传感器:", sensor.name)
            }
        }
        addTitle("电池")
        addContentText("类型:", "")
        addContentText("电量:", "")
        addContentText("总量:", "")
        addContentText("温度:", "")
        addContentText("健康状态:", "")
        addContentText("充电状态:", "")
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

    private fun replaceContentText(key: String, value: String) {
        for (info in mList) {
            if (info.key == key) {
                info.value = value
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    inner class BatteryReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("BatteryReceiver", "onReceive")
            if (intent != null) {
                Log.d("onReceive", "获取电池信息")
                val bundle = intent.extras
                val current = bundle.getInt("level") // 获得当前电量
                val total = bundle.getInt("scale") // 获得总电量
                val temperature = bundle.getInt("temperature", 0) / 10 //温度
                val technology = bundle.getString("technology") //电池类型
                val health = bundle.getInt("health") //电池的健康状态
                val status = bundle.getInt("status") //电池的充电状态
                battery.level = current
                battery.scale = total
                battery.technology = technology
                battery.temperature = temperature
                battery.health = health
                battery.status = status
                replaceContentText("类型:", battery.technology)
                replaceContentText("电量:", battery.level.toString())
                replaceContentText("总量:", battery.scale.toString())
                replaceContentText("温度:", String.format("%d°", battery.temperature))
                replaceContentText("健康状态:", battery.healthStr)
                replaceContentText("充电状态:", battery.statusStr)
                mAdapter.refresh(mList)
            }
        }

    }
}
