package com.example.testbase.util;

import java.util.List;





import com.example.testbase.kuangjia.R;
import com.example.testbase.log.ToastMy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class NetUtil {
	private NetUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean netIsAvailable(Context cxt) {

		if (!isConnected(cxt)) {
			ToastMy.showShortToast(cxt, "��ǰ�����ѶϿ�");
			return false;

		}
		return true;
	}

	/**
	 * �ж������Ƿ�����
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * �����Ѿ����ӣ�Ȼ��ȥ�ж���wifi���ӻ���GPRS���� ����һЩ�Լ����߼�����
	 */
	public static void isNetworkAvailableNew(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (gprs == State.CONNECTED || gprs == State.CONNECTING) {

			Toast.makeText(context, "��ǰΪ GPRS���磬��ע��ʹ������", Toast.LENGTH_LONG)
					.show();
		}
		// �ж�Ϊwifi״̬�²ż��ع�棬�����GPRS�ֻ������򲻼��أ�
		if (wifi == State.CONNECTED || wifi == State.CONNECTING) {

			Toast.makeText(context, "��ǰΪ WIFI���� ", Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * ����δ����ʱ���������÷���
	 */
	public static void setNetwork(final Activity activity) {
		Toast.makeText(activity, "��ǰ�����ѹرգ����", Toast.LENGTH_LONG).show();

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("������ʾ��Ϣ");
		builder.setMessage("���粻���ã���������������������磡");
		builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				/**
				 * �ж��ֻ�ϵͳ�İ汾�����API����10 ����3.0+ ��Ϊ3.0���ϵİ汾�����ú�3.0���µ����ò�һ�������õķ�����ͬ
				 */
				if (android.os.Build.VERSION.SDK_INT > 10) {
					intent = new Intent(
							android.provider.Settings.ACTION_WIFI_SETTINGS);
				} else {
					intent = new Intent();
					ComponentName component = new ComponentName(
							"com.android.settings",
							"com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				activity.startActivity(intent);
			}
		});
		builder.create();
		builder.show();
	}

	public static boolean isConnectingToInternet(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		}
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		return (info != null) && info.isAvailable();
	}

	// �ж���������
	public static NetType getNetType(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return null;
		}
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null) {// ������
			return null;
		}
		String type = info.getTypeName();
		if (type.equalsIgnoreCase("wifi")) {// wifi����������
			return null;
		} else if (type.equalsIgnoreCase("mobile")) {// GPRS
			Uri uri = Uri.parse("content://telephony/carriers/preferapn");
			Cursor cr = context.getContentResolver().query(uri, null, null,
					null, null);
			while (cr != null && cr.moveToNext()) {
				NetType netType = new NetType();
				if (cr.getString(cr.getColumnIndex("proxy")) != null
						&& !cr.getString(cr.getColumnIndex("proxy")).equals("")) {
					netType.setProxy(cr.getString(cr.getColumnIndex("proxy")));
					netType.setPort(cr.getInt(cr.getColumnIndex("port")));
					netType.setWap(true);
				} else {
					netType.setWap(false);
				}
				return netType;
			}
		}
		return null;
	}

	// �������ʵ��
	public static class NetType {
		private String proxy = "";
		private int port = 0;
		private boolean isWap = false;

		public String getProxy() {
			return proxy;
		}

		public void setProxy(String proxy) {
			this.proxy = proxy;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public boolean isWap() {
			return isWap;
		}

		public void setWap(boolean isWap) {
			this.isWap = isWap;
		}

	}

	// �ж�wifi�����Ƿ�����
	public static boolean isWiFiActive(Context inContext) {
		WifiManager mWifiManager = (WifiManager) inContext

		.getSystemService(Context.WIFI_SERVICE);

		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();

		int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();

		if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
			System.out.println("**** WIFI is on");
			return true;
		} else {
			System.out.println("**** WIFI is off");
			return false;

		}
	}

	// �ж�3G�����Ƿ�����
	public static boolean isNetworkAvailableAdd(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			System.out.println("**** newwork is off");
			return false;
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info == null) {
				System.out.println("**** newwork is off");
				return false;
			} else {
				if (info.isAvailable()) {
					System.out.println("**** newwork is on");
					return true;
				}
			}
		}
		System.out.println("**** newwork is off");
		return false;
	}

	public static boolean checkNet(Context context) {
		// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// ��ȡ�������ӹ���Ķ���
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// �жϵ�ǰ�����Ƿ��Ѿ�����
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �����Ƿ����
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * ����������ʾ
	 * 
	 * @param context
	 * @return
	 */
	public static void networkStateTips(Context context) {
		if (!isNetworkAvailable(context)) {
			ToastMy.showShortToast(context, "������ϣ����ȼ����������");
		}
	}

	/**
	 * Gps�Ƿ��
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * wifi�Ƿ��
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * �жϵ�ǰ�����Ƿ���wifi����
	 * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //�ж�3G��
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * �жϵ�ǰ�����Ƿ���3G����
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

}
