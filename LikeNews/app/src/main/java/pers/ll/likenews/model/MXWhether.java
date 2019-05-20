package pers.ll.likenews.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MXWhether implements Parcelable {
    /**
     * alarms : [{"alarmContent":"三明市气象台2019年05月17日13时57分变更发布暴雨橙色预警信号：过去6小时明溪、将乐、沙县、三元、尤溪五个县（区）的部分乡镇出现50毫米以上的强降水，预计未来3小时宁化、清流、明溪、将乐、泰宁、永安、沙县、尤溪八个县（市、区）的部分乡镇仍有50毫米以上降水。请注意防范持续强降水可能引发的山洪、城乡渍涝和山体滑坡等次生灾害。（预警信息来源：国家预警信息发布中心）","alarmDesc":"福建省三明市气象台发布暴雨橙色预警","alarmId":"c0aec553e2dd6a4192269052e4885c96","alarmLevelNo":"03","alarmLevelNoDesc":"橙色","alarmType":"02","alarmTypeDesc":"暴雨橙色","precaution":"1.不要在积水中行走，防止跌入窨井、地坑等；\n\n2.驾驶车辆遇到路面积水过深时，应尽量绕行；\n\n3.危险地带的学校和单位应当停课、停业。","publishTime":"2019-05-17 14:20:00"},{"alarmContent":"福建省气象台5月17日12时56分继续发布雷电黄色预警信号。预计未来6小时全省部分县市有雷电活动，局部伴有短时强降水、7-9级雷雨大风或小冰雹等强对流天气。请注意防范!（预警信息来源：国家预警信息发布中心）","alarmDesc":"福建省气象台发布雷电黄色预警","alarmId":"13cef5595482828011513ea73f5e265f","alarmLevelNo":"02","alarmLevelNoDesc":"黄色","alarmType":"09","alarmTypeDesc":"雷电黄色","precaution":"1.尽量避免户外活动；\n\n2.注意接收后续天气预报预警信号。","publishTime":"2019-05-17 12:56:29"},{"alarmContent":"福建省气象台5月17日5时38分继续发布大雾黄色预警信号。今天白天到夜间，沿海的部分县市和内陆的局部县市、沿海海区、台湾海峡有能见度小于500米的大雾，请注意防范。（预警信息来源：国家预警信息发布中心）","alarmDesc":"福建省气象台发布大雾黄色预警","alarmId":"c942b66739bc18820645548294e0e91e","alarmLevelNo":"02","alarmLevelNoDesc":"黄色","alarmType":"12","alarmTypeDesc":"大雾黄色","precaution":"1.户外活动注意安全；\n\n2.驾驶员注意雾的变化，小心驾驶。","publishTime":"2019-05-17 05:38:57"}]
     * city : 三明
     * cityid : 101230801
     * indexes : [{"abbreviation":"gm","alias":"","content":"感冒容易发生，少去人群密集的场所有利于降低感冒的几率。","level":"易发","name":"感冒指数"},{"abbreviation":"pp","alias":"","content":"建议用露质面霜打底，水质无油粉底霜，透明粉饼，粉质胭脂。","level":"控油","name":"化妆指数"},{"abbreviation":"yd","alias":"","content":"受到大雨天气的影响，不宜在户外运动。","level":"不适宜","name":"运动指数"},{"abbreviation":"xc","alias":"","content":"雨(雪)水和泥水会弄脏您的爱车，不适宜清洗车辆。","level":"不宜","name":"洗车指数"},{"abbreviation":"ct","alias":"","content":"天气较热，衣物精干简洁，室内酌情添加空调衫。","level":"热","name":"穿衣指数"},{"abbreviation":"uv","alias":"","content":"辐射较弱，涂擦SPF12-15、PA+护肤品。","level":"弱","name":"紫外线强度指数"}]
     * pm25 : {"advice":"0","aqi":"19","citycount":674,"cityrank":96,"co":"16","color":"0","level":"0","no2":"12","o3":"18","pm10":"19","pm25":"10","quality":"优","so2":"2","timestamp":"","upDateTime":"2019-05-17 15:00:00"}
     * provinceName : 福建省
     * realtime : {"img":"8","sD":"88","sendibleTemp":"27","temp":"26","time":"2019-05-17 16:20:08","wD":"东风","wS":"1级","weather":"雨","ziwaixian":"N/A"}
     * weatherDetailsInfo : {"publishTime":"2019-05-17 16:00:00","weather3HoursDetailsInfos":[{"endTime":"2019-05-17 20:00:00","highestTemperature":"25","img":"2","isRainFall":"","lowerestTemperature":"25","precipitation":"0","startTime":"2019-05-17 17:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-17 23:00:00","highestTemperature":"23","img":"2","isRainFall":"","lowerestTemperature":"23","precipitation":"0","startTime":"2019-05-17 20:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 02:00:00","highestTemperature":"23","img":"2","isRainFall":"","lowerestTemperature":"23","precipitation":"0","startTime":"2019-05-17 23:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 05:00:00","highestTemperature":"24","img":"2","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 02:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 08:00:00","highestTemperature":"24","img":"2","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 05:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 11:00:00","highestTemperature":"24","img":"0","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 08:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-05-18 14:00:00","highestTemperature":"27","img":"0","isRainFall":"","lowerestTemperature":"27","precipitation":"0","startTime":"2019-05-18 11:00:00","wd":"","weather":"晴","ws":""}]}
     * weathers : [{"date":"2019-05-17","img":"9","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"30","temp_day_f":"86.0","temp_night_c":"23","temp_night_f":"73.4","wd":"","weather":"大雨","week":"星期五","ws":""},{"date":"2019-05-18","img":"8","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"30","temp_day_f":"86.0","temp_night_c":"24","temp_night_f":"75.2","wd":"","weather":"中雨","week":"星期六","ws":""},{"date":"2019-05-19","img":"7","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"31","temp_day_f":"87.8","temp_night_c":"24","temp_night_f":"75.2","wd":"","weather":"小雨","week":"星期日","ws":""},{"date":"2019-05-20","img":"7","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"27","temp_day_f":"80.6","temp_night_c":"21","temp_night_f":"69.8","wd":"","weather":"小雨","week":"星期一","ws":""},{"date":"2019-05-21","img":"1","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"32","temp_day_f":"89.6","temp_night_c":"17","temp_night_f":"62.6","wd":"","weather":"多云","week":"星期二","ws":""},{"date":"2019-05-22","img":"0","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"32","temp_day_f":"89.6","temp_night_c":"18","temp_night_f":"64.4","wd":"","weather":"晴","week":"星期三","ws":""},{"date":"2019-05-16","img":"9","sun_down_time":"18:50","sun_rise_time":"05:22","temp_day_c":"30","temp_day_f":"86.0","temp_night_c":"21","temp_night_f":"69.8","wd":"","weather":"大雨","week":"星期四","ws":""}]
     */
    private String city;
    private int cityid;
    private Pm25 pm25;
    private String provinceName;
    private RealtimeWhether realtime;
    private WeatherDetailsInfo weatherDetailsInfo;
    private List<Alarms> alarms;
    private List<Indexes> indexes;
    private List<Forecast> weathers;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public Pm25 getPm25() {
        return pm25;
    }

    public void setPm25(Pm25 pm25) {
        this.pm25 = pm25;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public RealtimeWhether getRealtime() {
        return realtime;
    }

    public void setRealtime(RealtimeWhether realtime) {
        this.realtime = realtime;
    }

    public WeatherDetailsInfo getWeatherDetailsInfo() {
        return weatherDetailsInfo;
    }

    public void setWeatherDetailsInfo(WeatherDetailsInfo weatherDetailsInfo) {
        this.weatherDetailsInfo = weatherDetailsInfo;
    }

    public List<Alarms> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarms> alarms) {
        this.alarms = alarms;
    }

    public List<Indexes> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Indexes> indexes) {
        this.indexes = indexes;
    }

    public List<Forecast> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Forecast> weathers) {
        this.weathers = weathers;
    }

    public static class Pm25 implements Parcelable {
        /**
         * advice : 0
         * aqi : 19
         * citycount : 674
         * cityrank : 96
         * co : 16
         * color : 0
         * level : 0
         * no2 : 12
         * o3 : 18
         * pm10 : 19
         * pm25 : 10
         * quality : 优
         * so2 : 2
         * timestamp :
         * upDateTime : 2019-05-17 15:00:00
         */

        private String advice;
        private String aqi;
        private int citycount;
        private int cityrank;
        private String co;
        private String color;
        private String level;
        private String no2;
        private String o3;
        private String pm10;
        private String pm25;
        private String quality;
        private String so2;
        private String timestamp;
        private String upDateTime;

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public int getCitycount() {
            return citycount;
        }

        public void setCitycount(int citycount) {
            this.citycount = citycount;
        }

        public int getCityrank() {
            return cityrank;
        }

        public void setCityrank(int cityrank) {
            this.cityrank = cityrank;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getUpDateTime() {
            return upDateTime;
        }

        public void setUpDateTime(String upDateTime) {
            this.upDateTime = upDateTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.advice);
            dest.writeString(this.aqi);
            dest.writeInt(this.citycount);
            dest.writeInt(this.cityrank);
            dest.writeString(this.co);
            dest.writeString(this.color);
            dest.writeString(this.level);
            dest.writeString(this.no2);
            dest.writeString(this.o3);
            dest.writeString(this.pm10);
            dest.writeString(this.pm25);
            dest.writeString(this.quality);
            dest.writeString(this.so2);
            dest.writeString(this.timestamp);
            dest.writeString(this.upDateTime);
        }

        public Pm25() {
        }

        protected Pm25(Parcel in) {
            this.advice = in.readString();
            this.aqi = in.readString();
            this.citycount = in.readInt();
            this.cityrank = in.readInt();
            this.co = in.readString();
            this.color = in.readString();
            this.level = in.readString();
            this.no2 = in.readString();
            this.o3 = in.readString();
            this.pm10 = in.readString();
            this.pm25 = in.readString();
            this.quality = in.readString();
            this.so2 = in.readString();
            this.timestamp = in.readString();
            this.upDateTime = in.readString();
        }

        public static final Creator<Pm25> CREATOR = new Creator<Pm25>() {
            @Override
            public Pm25 createFromParcel(Parcel source) {
                return new Pm25(source);
            }

            @Override
            public Pm25[] newArray(int size) {
                return new Pm25[size];
            }
        };
    }

    public static class RealtimeWhether implements Parcelable {
        /**
         * img : 8
         * sD : 88
         * sendibleTemp : 27
         * temp : 26
         * time : 2019-05-17 16:20:08
         * wD : 东风
         * wS : 1级
         * weather : 雨
         * ziwaixian : N/A
         */

        private String img;
        private String sD;
        private String sendibleTemp;
        private String temp;
        private String time;
        private String wD;
        private String wS;
        private String weather;
        private String ziwaixian;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSD() {
            return sD;
        }

        public void setSD(String sD) {
            this.sD = sD;
        }

        public String getSendibleTemp() {
            return sendibleTemp;
        }

        public void setSendibleTemp(String sendibleTemp) {
            this.sendibleTemp = sendibleTemp;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWD() {
            return wD;
        }

        public void setWD(String wD) {
            this.wD = wD;
        }

        public String getWS() {
            return wS;
        }

        public void setWS(String wS) {
            this.wS = wS;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(String ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.img);
            dest.writeString(this.sD);
            dest.writeString(this.sendibleTemp);
            dest.writeString(this.temp);
            dest.writeString(this.time);
            dest.writeString(this.wD);
            dest.writeString(this.wS);
            dest.writeString(this.weather);
            dest.writeString(this.ziwaixian);
        }

        public RealtimeWhether() {
        }

        protected RealtimeWhether(Parcel in) {
            this.img = in.readString();
            this.sD = in.readString();
            this.sendibleTemp = in.readString();
            this.temp = in.readString();
            this.time = in.readString();
            this.wD = in.readString();
            this.wS = in.readString();
            this.weather = in.readString();
            this.ziwaixian = in.readString();
        }

        public static final Creator<RealtimeWhether> CREATOR = new Creator<RealtimeWhether>() {
            @Override
            public RealtimeWhether createFromParcel(Parcel source) {
                return new RealtimeWhether(source);
            }

            @Override
            public RealtimeWhether[] newArray(int size) {
                return new RealtimeWhether[size];
            }
        };
    }

    public static class WeatherDetailsInfo implements Parcelable {
        /**
         * publishTime : 2019-05-17 16:00:00
         * weather3HoursDetailsInfos : [{"endTime":"2019-05-17 20:00:00","highestTemperature":"25","img":"2","isRainFall":"","lowerestTemperature":"25","precipitation":"0","startTime":"2019-05-17 17:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-17 23:00:00","highestTemperature":"23","img":"2","isRainFall":"","lowerestTemperature":"23","precipitation":"0","startTime":"2019-05-17 20:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 02:00:00","highestTemperature":"23","img":"2","isRainFall":"","lowerestTemperature":"23","precipitation":"0","startTime":"2019-05-17 23:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 05:00:00","highestTemperature":"24","img":"2","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 02:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 08:00:00","highestTemperature":"24","img":"2","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 05:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-05-18 11:00:00","highestTemperature":"24","img":"0","isRainFall":"","lowerestTemperature":"24","precipitation":"0","startTime":"2019-05-18 08:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-05-18 14:00:00","highestTemperature":"27","img":"0","isRainFall":"","lowerestTemperature":"27","precipitation":"0","startTime":"2019-05-18 11:00:00","wd":"","weather":"晴","ws":""}]
         */

        private String publishTime;
        private List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos;

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public List<Weather3HoursDetailsInfosBean> getWeather3HoursDetailsInfos() {
            return weather3HoursDetailsInfos;
        }

        public void setWeather3HoursDetailsInfos(List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos) {
            this.weather3HoursDetailsInfos = weather3HoursDetailsInfos;
        }

        public static class Weather3HoursDetailsInfosBean implements Parcelable {
            /**
             * endTime : 2019-05-17 20:00:00
             * highestTemperature : 25
             * img : 2
             * isRainFall :
             * lowerestTemperature : 25
             * precipitation : 0
             * startTime : 2019-05-17 17:00:00
             * wd :
             * weather : 阴
             * ws :
             */

            private String endTime;
            private String highestTemperature;
            private String img;
            private String isRainFall;
            private String lowerestTemperature;
            private String precipitation;
            private String startTime;
            private String wd;
            private String weather;
            private String ws;

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getHighestTemperature() {
                return highestTemperature;
            }

            public void setHighestTemperature(String highestTemperature) {
                this.highestTemperature = highestTemperature;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getIsRainFall() {
                return isRainFall;
            }

            public void setIsRainFall(String isRainFall) {
                this.isRainFall = isRainFall;
            }

            public String getLowerestTemperature() {
                return lowerestTemperature;
            }

            public void setLowerestTemperature(String lowerestTemperature) {
                this.lowerestTemperature = lowerestTemperature;
            }

            public String getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(String precipitation) {
                this.precipitation = precipitation;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getWd() {
                return wd;
            }

            public void setWd(String wd) {
                this.wd = wd;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWs() {
                return ws;
            }

            public void setWs(String ws) {
                this.ws = ws;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.endTime);
                dest.writeString(this.highestTemperature);
                dest.writeString(this.img);
                dest.writeString(this.isRainFall);
                dest.writeString(this.lowerestTemperature);
                dest.writeString(this.precipitation);
                dest.writeString(this.startTime);
                dest.writeString(this.wd);
                dest.writeString(this.weather);
                dest.writeString(this.ws);
            }

            public Weather3HoursDetailsInfosBean() {
            }

            protected Weather3HoursDetailsInfosBean(Parcel in) {
                this.endTime = in.readString();
                this.highestTemperature = in.readString();
                this.img = in.readString();
                this.isRainFall = in.readString();
                this.lowerestTemperature = in.readString();
                this.precipitation = in.readString();
                this.startTime = in.readString();
                this.wd = in.readString();
                this.weather = in.readString();
                this.ws = in.readString();
            }

            public static final Creator<Weather3HoursDetailsInfosBean> CREATOR = new Creator<Weather3HoursDetailsInfosBean>() {
                @Override
                public Weather3HoursDetailsInfosBean createFromParcel(Parcel source) {
                    return new Weather3HoursDetailsInfosBean(source);
                }

                @Override
                public Weather3HoursDetailsInfosBean[] newArray(int size) {
                    return new Weather3HoursDetailsInfosBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.publishTime);
            dest.writeList(this.weather3HoursDetailsInfos);
        }

        public WeatherDetailsInfo() {
        }

        protected WeatherDetailsInfo(Parcel in) {
            this.publishTime = in.readString();
            this.weather3HoursDetailsInfos = new ArrayList<Weather3HoursDetailsInfosBean>();
            in.readList(this.weather3HoursDetailsInfos, Weather3HoursDetailsInfosBean.class.getClassLoader());
        }

        public static final Creator<WeatherDetailsInfo> CREATOR = new Creator<WeatherDetailsInfo>() {
            @Override
            public WeatherDetailsInfo createFromParcel(Parcel source) {
                return new WeatherDetailsInfo(source);
            }

            @Override
            public WeatherDetailsInfo[] newArray(int size) {
                return new WeatherDetailsInfo[size];
            }
        };
    }

    public static class Alarms implements Parcelable {
        /**
         * alarmContent : 三明市气象台2019年05月17日13时57分变更发布暴雨橙色预警信号：过去6小时明溪、将乐、沙县、三元、尤溪五个县（区）的部分乡镇出现50毫米以上的强降水，预计未来3小时宁化、清流、明溪、将乐、泰宁、永安、沙县、尤溪八个县（市、区）的部分乡镇仍有50毫米以上降水。请注意防范持续强降水可能引发的山洪、城乡渍涝和山体滑坡等次生灾害。（预警信息来源：国家预警信息发布中心）
         * alarmDesc : 福建省三明市气象台发布暴雨橙色预警
         * alarmId : c0aec553e2dd6a4192269052e4885c96
         * alarmLevelNo : 03
         * alarmLevelNoDesc : 橙色
         * alarmType : 02
         * alarmTypeDesc : 暴雨橙色
         * precaution : 1.不要在积水中行走，防止跌入窨井、地坑等；

         2.驾驶车辆遇到路面积水过深时，应尽量绕行；

         3.危险地带的学校和单位应当停课、停业。
         * publishTime : 2019-05-17 14:20:00
         */

        private String alarmContent;
        private String alarmDesc;
        private String alarmId;
        private String alarmLevelNo;
        private String alarmLevelNoDesc;
        private String alarmType;
        private String alarmTypeDesc;
        private String precaution;
        private String publishTime;

        public String getAlarmContent() {
            return alarmContent;
        }

        public void setAlarmContent(String alarmContent) {
            this.alarmContent = alarmContent;
        }

        public String getAlarmDesc() {
            return alarmDesc;
        }

        public void setAlarmDesc(String alarmDesc) {
            this.alarmDesc = alarmDesc;
        }

        public String getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(String alarmId) {
            this.alarmId = alarmId;
        }

        public String getAlarmLevelNo() {
            return alarmLevelNo;
        }

        public void setAlarmLevelNo(String alarmLevelNo) {
            this.alarmLevelNo = alarmLevelNo;
        }

        public String getAlarmLevelNoDesc() {
            return alarmLevelNoDesc;
        }

        public void setAlarmLevelNoDesc(String alarmLevelNoDesc) {
            this.alarmLevelNoDesc = alarmLevelNoDesc;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getAlarmTypeDesc() {
            return alarmTypeDesc;
        }

        public void setAlarmTypeDesc(String alarmTypeDesc) {
            this.alarmTypeDesc = alarmTypeDesc;
        }

        public String getPrecaution() {
            return precaution;
        }

        public void setPrecaution(String precaution) {
            this.precaution = precaution;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.alarmContent);
            dest.writeString(this.alarmDesc);
            dest.writeString(this.alarmId);
            dest.writeString(this.alarmLevelNo);
            dest.writeString(this.alarmLevelNoDesc);
            dest.writeString(this.alarmType);
            dest.writeString(this.alarmTypeDesc);
            dest.writeString(this.precaution);
            dest.writeString(this.publishTime);
        }

        public Alarms() {
        }

        protected Alarms(Parcel in) {
            this.alarmContent = in.readString();
            this.alarmDesc = in.readString();
            this.alarmId = in.readString();
            this.alarmLevelNo = in.readString();
            this.alarmLevelNoDesc = in.readString();
            this.alarmType = in.readString();
            this.alarmTypeDesc = in.readString();
            this.precaution = in.readString();
            this.publishTime = in.readString();
        }

        public static final Creator<Alarms> CREATOR = new Creator<Alarms>() {
            @Override
            public Alarms createFromParcel(Parcel source) {
                return new Alarms(source);
            }

            @Override
            public Alarms[] newArray(int size) {
                return new Alarms[size];
            }
        };
    }

    public static class Indexes implements Parcelable {
        /**
         * abbreviation : gm
         * alias :
         * content : 感冒容易发生，少去人群密集的场所有利于降低感冒的几率。
         * level : 易发
         * name : 感冒指数
         */

        private String abbreviation;
        private String alias;
        private String content;
        private String level;
        private String name;

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.abbreviation);
            dest.writeString(this.alias);
            dest.writeString(this.content);
            dest.writeString(this.level);
            dest.writeString(this.name);
        }

        public Indexes() {
        }

        protected Indexes(Parcel in) {
            this.abbreviation = in.readString();
            this.alias = in.readString();
            this.content = in.readString();
            this.level = in.readString();
            this.name = in.readString();
        }

        public static final Creator<Indexes> CREATOR = new Creator<Indexes>() {
            @Override
            public Indexes createFromParcel(Parcel source) {
                return new Indexes(source);
            }

            @Override
            public Indexes[] newArray(int size) {
                return new Indexes[size];
            }
        };
    }

    public static class Forecast implements Parcelable {
        /**
         * date : 2019-05-17
         * img : 9
         * sun_down_time : 18:50
         * sun_rise_time : 05:22
         * temp_day_c : 30
         * temp_day_f : 86.0
         * temp_night_c : 23
         * temp_night_f : 73.4
         * wd :
         * weather : 大雨
         * week : 星期五
         * ws :
         */

        private String date;
        private String img;
        private String sun_down_time;
        private String sun_rise_time;
        private String temp_day_c;
        private String temp_day_f;
        private String temp_night_c;
        private String temp_night_f;
        private String wd;
        private String weather;
        private String week;
        private String ws;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSun_down_time() {
            return sun_down_time;
        }

        public void setSun_down_time(String sun_down_time) {
            this.sun_down_time = sun_down_time;
        }

        public String getSun_rise_time() {
            return sun_rise_time;
        }

        public void setSun_rise_time(String sun_rise_time) {
            this.sun_rise_time = sun_rise_time;
        }

        public String getTemp_day_c() {
            return temp_day_c;
        }

        public void setTemp_day_c(String temp_day_c) {
            this.temp_day_c = temp_day_c;
        }

        public String getTemp_day_f() {
            return temp_day_f;
        }

        public void setTemp_day_f(String temp_day_f) {
            this.temp_day_f = temp_day_f;
        }

        public String getTemp_night_c() {
            return temp_night_c;
        }

        public void setTemp_night_c(String temp_night_c) {
            this.temp_night_c = temp_night_c;
        }

        public String getTemp_night_f() {
            return temp_night_f;
        }

        public void setTemp_night_f(String temp_night_f) {
            this.temp_night_f = temp_night_f;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWs() {
            return ws;
        }

        public void setWs(String ws) {
            this.ws = ws;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.date);
            dest.writeString(this.img);
            dest.writeString(this.sun_down_time);
            dest.writeString(this.sun_rise_time);
            dest.writeString(this.temp_day_c);
            dest.writeString(this.temp_day_f);
            dest.writeString(this.temp_night_c);
            dest.writeString(this.temp_night_f);
            dest.writeString(this.wd);
            dest.writeString(this.weather);
            dest.writeString(this.week);
            dest.writeString(this.ws);
        }

        public Forecast() {
        }

        protected Forecast(Parcel in) {
            this.date = in.readString();
            this.img = in.readString();
            this.sun_down_time = in.readString();
            this.sun_rise_time = in.readString();
            this.temp_day_c = in.readString();
            this.temp_day_f = in.readString();
            this.temp_night_c = in.readString();
            this.temp_night_f = in.readString();
            this.wd = in.readString();
            this.weather = in.readString();
            this.week = in.readString();
            this.ws = in.readString();
        }

        public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
            @Override
            public Forecast createFromParcel(Parcel source) {
                return new Forecast(source);
            }

            @Override
            public Forecast[] newArray(int size) {
                return new Forecast[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeInt(this.cityid);
        dest.writeParcelable(this.pm25, flags);
        dest.writeString(this.provinceName);
        dest.writeParcelable(this.realtime, flags);
        dest.writeParcelable(this.weatherDetailsInfo, flags);
        dest.writeList(this.alarms);
        dest.writeList(this.indexes);
        dest.writeList(this.weathers);
    }

    public MXWhether() {
    }

    protected MXWhether(Parcel in) {
        this.city = in.readString();
        this.cityid = in.readInt();
        this.pm25 = in.readParcelable(Pm25.class.getClassLoader());
        this.provinceName = in.readString();
        this.realtime = in.readParcelable(RealtimeWhether.class.getClassLoader());
        this.weatherDetailsInfo = in.readParcelable(WeatherDetailsInfo.class.getClassLoader());
        this.alarms = new ArrayList<Alarms>();
        in.readList(this.alarms, Alarms.class.getClassLoader());
        this.indexes = new ArrayList<Indexes>();
        in.readList(this.indexes, Indexes.class.getClassLoader());
        this.weathers = new ArrayList<Forecast>();
        in.readList(this.weathers, Forecast.class.getClassLoader());
    }

    public static final Parcelable.Creator<MXWhether> CREATOR = new Parcelable.Creator<MXWhether>() {
        @Override
        public MXWhether createFromParcel(Parcel source) {
            return new MXWhether(source);
        }

        @Override
        public MXWhether[] newArray(int size) {
            return new MXWhether[size];
        }
    };
}