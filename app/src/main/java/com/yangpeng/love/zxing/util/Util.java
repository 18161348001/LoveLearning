package com.yangpeng.love.zxing.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

public class Util {

	public static Activity currentActivity = null;

	/**
	 * 获得屏幕宽度
	 *
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowWidthPix() {
		int ver = Build.VERSION.SDK_INT;
		Display display = currentActivity.getWindowManager()
				.getDefaultDisplay();
		int width = 0;
		if (ver < 13) {
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			width = dm.widthPixels;
		} else {
			Point point = new Point();
			display.getSize(point);
			width = point.x;
		}
		return width;
	}

	/**
	 * 获得屏幕高度
	 *
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowHeightPix() {
		int ver = Build.VERSION.SDK_INT;
		Display display = currentActivity.getWindowManager()
				.getDefaultDisplay();
		int height = 0;
		if (ver < 13) {
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			height = dm.heightPixels;
		} else {
			Point point = new Point();
			display.getSize(point);
			height = point.y;
		}
		return height;
	}

	public static String getIMEI(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return null;
			}else
			{
				String imeiCode = tm.getDeviceId();
				return imeiCode;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
