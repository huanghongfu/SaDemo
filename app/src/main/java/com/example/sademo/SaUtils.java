package com.example.sademo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imac
 * @date 19/3/19
 */
public class SaUtils {

    // debug 模式的数据接收地址 （测试，测试项目）
    final static String SA_SERVER_URL_DEBUG = "http://47.107.243.160:8106/sa?project=default";

    // release 模式的数据接收地址（发版，正式项目）
    final static String SA_SERVER_URL_RELEASE = "http://47.107.243.160:8106/sa?project=production";


    public static void init(Context context){
        initSensorsDataSDK(context);
    }

    /**
     * 初始化 SDK 、设置公共属性、开启自动采集
     */
    private static void initSensorsDataSDK(Context context) {
        try {
            // 初始化 SDK
            SensorsDataAPI.sharedInstance(
                    context,                                                                                  // 传入 Context
                    (isDebugMode(context)) ? SA_SERVER_URL_DEBUG : SA_SERVER_URL_RELEASE,   // 数据接收的 URL
                    (isDebugMode(context)) ? SensorsDataAPI.DebugMode.DEBUG_AND_TRACK : SensorsDataAPI.DebugMode.DEBUG_OFF
            );

            // 初始化SDK后，获取应用名称设置为公共属性
            JSONObject properties = new JSONObject();
            properties.put("app_name", getAppName(context));
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties);
            SensorsDataAPI.sharedInstance().enableLog(true);
            // 打开自动采集, 并指定追踪哪些 AutoTrack 事件
            List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
            // $AppStart
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
            // $AppEnd
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
            // $AppViewScreen
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
//            // $AppClick
//            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);

            SensorsDataAPI.sharedInstance().enableAutoTrack(eventTypeList);
            //初始化 SDK 之后，开启自动采集 Fragment 页面浏览事件
            SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context App 的 Context
     *                获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param context App 的 Context
     * @return debug return true,release return false
     * 用于判断是 debug 包，还是 relase 包
     */
    private static boolean isDebugMode(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
