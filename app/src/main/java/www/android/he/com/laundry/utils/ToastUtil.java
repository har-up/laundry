package www.android.he.com.laundry.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import www.android.he.com.laundry.R;


/**
 * 自定义Toast
 * */
public class ToastUtil {


    private ToastUtil() {
	}

	private static ToastUtil instance;

	private static Context mContext;

	public static ToastUtil getInstance() {
		if (instance == null) {
			synchronized (ToastUtil.class) {
				if (instance == null) {
					instance = new ToastUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 在Application中调用，省去每次toast需要传的context,
	 * */
	public static void init(Context mContext) {
		ToastUtil.mContext = mContext.getApplicationContext();
	}

	// Toast对象
	private Toast mToast;

	/**
	 * 显示toast
	 */
	public void toast(String content) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = new Toast(mContext);
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.base_lay_toast, null);
		TextView tv = (TextView) view.findViewById(R.id.tvToast);
		mToast.setView(view);
		// mToast.setDuration(Toast.LENGTH_LONG);
		mToast.setDuration(Toast.LENGTH_SHORT);
		tv.setText(content);
		mToast.show();
	}

	/**
	 * 显示toast
	 * */
	public void toast(int resId) {
		String content = mContext.getString(resId);
		toast(content);
	}

	public void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

}
