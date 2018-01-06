package www.android.he.com.laundry.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import www.android.he.com.laundry.request.RequestBean;


/**
 * 基础参数的操作
 */
public class SPUtil {

    private static final String DBName = "qianxx"; // 基础参数放置的数据库名称
    private static Context mContext;
    private static SPUtil instance;
    private static SharedPreferences sp;

    public static void initContext(Context mContext) {
        SPUtil.mContext = mContext;
    }

    private SPUtil() {
        if (mContext == null) {
            throw new Error("必须先调用 SPUtil.initContext(context);");
        }
        sp = mContext.getSharedPreferences(DBName, Context.MODE_PRIVATE);
    }

    public static SPUtil getInstance() {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil();
                }
            }
        }
        return instance;
    }

    // 获取SP的实例
    public SharedPreferences getSP() {
        return sp;
    }


    /**
     * 获取登录token
     */
    public String getToken() {
        return sp.getString("token", "");
    }

    /**
     * 设置登录token
     */
    public void setToken(String token) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public String getData(String key) {
        return sp.getString("data-" + key, "");
    }

    public void setData(String key, String data) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data-" + key, data);

        editor.commit();
    }

    /**
     * 储存Boolean值
     * @param key
     * @param data
     */
    public void setBoolean(String key, Boolean data) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("data-" + key, data);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key,false);
    }

    /**
     * 存储 Json 数据
     *
     * @param key
     * @param json
     */
    public void setBean(String key, String json) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, json);
        editor.commit();
    }

    /**
     * 获取 Json 数据
     *
     * @param key
     * @param clazz
     * @return
     */
    public RequestBean getBean(String key, Class<? extends RequestBean> clazz) {
        String value = sp.getString(key, null);
        RequestBean data = null;
        try {
            if (clazz != null && value != null) {
                data = JSON.parseObject(value, clazz);
            }
        } catch (Exception e) {
            Log.e("Json","SPUtil --- 解析数据出现异常！");
        }
        return data;
    }

    /**
     * 获取登录用户id
     */
    public String getUserId() {
        return sp.getString("UserId", "");
    }

    /**
     * 设置登录用户id
     */
    public void setUserId(String userId) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UserId", userId);
        editor.commit();
    }

    /**
     * 获取战队id
     */
    public String getTeamId() {
        return sp.getString("TeamId", "");
    }

    /**
     * 设置战队id
     */
    public void setTeamId(String TeamId) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("TeamId", TeamId);
        editor.commit();
    }

    /**
     * 清空token
     */
    public void clearToken() {
        setToken("");
    }

    /**
     * 是否为首次打开应用
     */
    public boolean isFirst() {
        return sp.getBoolean("isFirst", true);
    }

    /**
     * 设置"是否打开语音"；
     * <p/>
     * 调用时，一般设置false
     */
    public void setFirst(boolean isFirst) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirst", isFirst);
        editor.commit();
    }

    /**
     * 是否打开语音
     */
    public boolean isOpenSound() {
        return sp.getBoolean("isOpenSound", true);
    }

    /**
     * 设置"是否打开语音；
     * <p/>
     * 调用时，一般设置false
     */
    public void setOpenSound(boolean isOpenSound) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isOpenSound", isOpenSound);
        editor.commit();
    }

public void clean(){
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    editor.commit();
}
    /**
     * 是否为首次打开应用
     */
    public boolean isIntegral() {
        return sp.getBoolean("isIntegral", true);
    }

    /**
     * 设置"是否为首次打开应用"；
     * <p/>
     * 调用时，一般设置false
     */
    public void setIntegral(boolean isIntegral) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isIntegral", isIntegral);
        editor.commit();
    }

    //记录车载线下订单状态
//    public void setStatu(int Statu) {
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("Statu", Statu);
//        editor.commit();
//    }
//
//    public int getStatu() {
//        return sp.getInt("Statu", 0);
//    }

}
