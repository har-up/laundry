package www.android.he.com.laundry.request;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import www.android.he.com.laundry.utils.LogUtil;
import www.android.he.com.laundry.utils.SPUtil;


/**
 * 数据请求监听
 *
 * @author you.y
 */
public class RequestListener implements Listener<String>, ErrorListener {

    private String requestTag = ""; // 请求标签
    private Class<? extends RequestBean> Claz; // 返回的数据模型
    private RequestCallback callBack; // 回调函数
    private Config config = new Config(); // 显示参数

    public void setParams(String requestTag,
                          Class<? extends RequestBean> Claz,
                          RequestCallback callBack) {
        this.requestTag = requestTag;
        this.Claz = Claz;
        this.callBack = callBack;
    }

    // 如需控制显示，可修改Config
    public void setParams(String requestTag, Class<? extends RequestBean> Claz,
                          RequestCallback callBack, Config config) {
        setParams(requestTag, Claz, callBack);
        if (config != null) {
            this.config = config;
        } else {
            LogUtil.e("RequestListener --- config为空！");
        }
    }

    /**
     * 请求成功
     */
    @Override
    public void onResponse(String response) {
        LogUtil.e("返回数据"+response); // 打印日志
        LogUtil.e("请求成功");

        RequestBean bean; // 返回的数据
        try {
            // 使用fastJson解析数据
            if (Claz != null) {
                bean = JSON.parseObject(response, Claz);
            } else {
                bean = JSON.parseObject(response, RequestBean.class);
                LogUtil.w("RequestListener  --- claz为空，返回基础数据模型");
            }
        } catch (Exception e) {
            bean = new RequestBean();
            bean.setMessage((Error.FormatError));
            onFail(bean); // 回调
            LogUtil.e("RequestListener --- 解析数据出现异常！" + e.toString());
            return;
        }

        // 根据返回的errorCode处理数据
        Integer errCode = bean.getErrCode();
        if (errCode == null) {
            onFail(bean); // 回调
            LogUtil.e("RequestListener  --- errCode为空！");
            return;
        }

        if (errCode == 0) {
            if ("0".equals(bean.getStatus())) {
//                bean.beanJson = response;
                onSuccess(bean); // 回调
                return;
            }
        } else if (errCode == Error.TokenInvalid  // token 过期
                || errCode == Error.USER_NOT_EXIST) {  // 用户不存在
            // token失效，清空token
            SPUtil.getInstance().clearToken();
            onTokenInvalid(requestTag); // 回调

            // 如果服务器没有返回提示语，则我们在本地添加提示语
            /*if (!TextUtils.isEmpty(bean.getMessage())) {
                bean.setMessage(Error.TokenInvalidString);
            }*/
        } else if (errCode == Error.ACCOUNT_CLOSURE) {
            SPUtil.getInstance().clearToken();
        }

        // ServiceError.setErrorToastMsg(bean); // 11-26添加, 主要为了处理500错误的提示语。
        onFail(bean); // 回调
    }

    /**
     * 请求失败
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        LogUtil.e("请求失败");
        RequestBean bean = new RequestBean();
        bean.setMessage(Error.NetError);
        setRequestTag(bean);
        onFail(bean); // 回调
        LogUtil.e(error.toString());
    }

    private void onSuccess(RequestBean bean) {
        if (callBack != null) {
            setRequestTag(bean);
            callBack.requestSuccess(bean, config);

        } else {
            LogUtil.e("RequestListener  --- callback为空！");
        }
    }

    private void onFail(RequestBean bean) {
        if (callBack != null) {
            setRequestTag(bean);
            callBack.requestFail(bean, config);

        } else {
            LogUtil.e("RequestListener  --- callback为空！");
        }
    }

    private void onTokenInvalid(String requestTag) {
        if (callBack != null) {
            callBack.requestTokenInvalid(requestTag, config);

        } else {
            LogUtil.e("RequestListener  --- callback为空！");
        }
    }

    private void setRequestTag(RequestBean bean) {
        if (TextUtils.isEmpty(requestTag)) {
            LogUtil.w("RequestListener  --- requestTag为空字符串");
        }
        bean.setRequestTag(requestTag); // 用于区分不同请求的回调
    }

}
