package www.android.he.com.laundry.request;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import www.android.he.com.laundry.utils.LogUtil;
import www.android.he.com.laundry.utils.SPUtil;


/**
 * 数据请求 工具类
 *
 * @author you.y
 */
public class RequestUtil {

    /**
     * 网络请求
     *
     * @param requestTag -请求标签（用于区分不同请求）
     * @param url        -配置的api接口
     *                   //	 * @param method
     *                   -请求方法（目前仅支持GET，POST）
     * @param bean       -返回的数据类型
     * @param params     -参数信息
     */
    public static void requestData(RequestQueue mRequest, String requestTag,
                                   String url, RM rm, Class<? extends RequestBean> bean,
                                   HashMap<String, String> params, RequestCallback callback) {
        if (mRequest == null || params == null) {
            LogUtil.w("RequestUtils --- mRequest或者params为空");
            return;
        }
        RequestListener listener = new RequestListener();
        listener.setParams(requestTag, bean, callback); // 为监听，设置回调
        requestData(mRequest, requestTag, url, rm, bean, params, listener);
    }

    /**
     * Config用于控制显示
     */
    public static void requestData(RequestQueue mRequest, String requestTag,
                                   String url, RM rm, Class<? extends RequestBean> bean,
                                   HashMap<String, String> params, RequestCallback callback,
                                   Config config) {
        RequestListener listener = new RequestListener();
        listener.setParams(requestTag, bean, callback, config);
        requestData(mRequest, requestTag, url, rm, bean, params, listener);
    }

    /**
     * 真正请求数据
     */
    private static void requestData(RequestQueue mRequest, String requestTag,
                                    String url, RM rm, Class<? extends RequestBean> bean,
                                    HashMap<String, String> params, RequestListener listener) {
        StringRequest sr = null;
        switch (rm) {
            case GET:
                sr = getRequestGET(mRequest, url, params, listener);
                break;
            case POST:
                sr = getRequestPOST(mRequest, url, params, listener);
                break;
        }
        sr.setRetryPolicy(new DefaultRetryPolicy(30000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)); // 解决重复发订单，暂时延长等待时间

        if (sr != null) {
            /*
             * 设置标签，便于控制； 可调用mRequst.cancelAll(tag)来取消请求； 说明：取消的请求
			 * 并不是真的取消，而是不会触发Volley的回调
			 */
            sr.setTag(requestTag);
            // 放入消息队列，发起请求回调RequestCallback
            mRequest.add(sr);
        } else {
            LogUtil.w("RequestUtils --- StringRequest为空！");
        }
    }

    /**
     * 获取Get请求使用的StringRequest
     */
    private static StringRequest getRequestGET(RequestQueue mRequest,
                                               String url,
                                               HashMap<String, String> params,
                                               RequestListener listener) {
//        params.put("key", "748608cf3ce28aef747596210761f900e07c65ef4b3947840deacdc1c30f06e7");
        String getUrl = getURL(url, params);
        LogUtil.d("getRequestGET --- url=" + getUrl);
        StringRequest sr = new StringRequest(getUrl, listener, listener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                String token = SPUtil.getInstance().getToken();
                if (!TextUtils.isEmpty(token)) {
                    header.put("token", token);
                }
                header.put("key","748608cf3ce28aef747596210761f900e07c65ef4b3947840deacdc1c30f06e7");
                return header;
            }

        };

        return sr;
    }

    /**
     * 获取post请求使用的StringRequest
     */
    private static StringRequest getRequestPOST(RequestQueue mRequest,
                                                String url, final HashMap<String, String> params,
                                                RequestListener listener) {
        if (params.size() > 0) {
            LogUtil.d("getRequestPOST --- 提交的参数如下：");
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                LogUtil.d(key + " = " + value);
            }
        }
        LogUtil.d(url+params);
        StringRequest sr = new StringRequest(Method.POST, url, listener,
                listener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                String token = SPUtil.getInstance().getToken();
                if (!TextUtils.isEmpty(token)) {
                    // token不为空时，放入header中
                    header.put("token", token);
                }
                header.put("key","748608cf3ce28aef747596210761f900e07c65ef4b3947840deacdc1c30f06e7");
                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        return sr;
    }

    // 获取get请求使用的url
    private static String getURL(String url, HashMap<String, String> params) {
        if (params.isEmpty()) {
            return url;
        }
        params.get("key");
        StringBuffer str = new StringBuffer();
        str.append(url);
        str.append("?key=748608cf3ce28aef747596210761f900e07c65ef4b3947840deacdc1c30f06e7&");
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            str.append("&");
            String key = iterator.next();
            str.append(key);
            str.append("=");
            String value = "";
            try {
                value = URLEncoder.encode(params.get(key), "utf-8");
            } catch (Exception e) {
                LogUtil.e("RequestUtil --- 出现异常！");
            }
            // String value = params.get(key);
            str.append(value);
        }
        str.replace(str.indexOf("&"),str.indexOf("&")+1,"");
        return str.toString();
    }

}
