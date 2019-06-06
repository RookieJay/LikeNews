package pers.ll.likenews.model;

import android.annotation.SuppressLint;
import android.os.BatteryManager;
import com.raizlabs.android.dbflow.StringUtils;

public class Battery {

    private int level;
    private int scale;
    private int temperature;
    private String technology;
    private int health;
    private int status;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SuppressLint("DefaultLocale")
    private String getPercent() {
        return String.format("%d", (level * 100) / scale);
    }

    public String getStatusStr() {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "充电中";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "放电中";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "满电";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "不充电";
            case BatteryManager.BATTERY_STATUS_UNKNOWN :
                return "未知";
            default:
                return "未知";
        }
    }

    public String getHealthStr() {
        switch (health) {
            case android.os.BatteryManager.BATTERY_HEALTH_DEAD:
                return "电池损坏";
            case android.os.BatteryManager.BATTERY_HEALTH_GOOD:
                return "电池健康";
            case android.os.BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "电池过热";
            case android.os.BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "电池电压过大";
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                return "未知状态";
            case android.os.BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE :
                return "未知故障";
            default:
                return "电池健康";
        }
    }
}
