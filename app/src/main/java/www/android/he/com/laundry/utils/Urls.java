package www.android.he.com.laundry.utils;

/**
 * Created by dao on 2018/1/2.
 */

public class Urls {
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    public static final String LOCALHOST = "192.168.0.103:8080";

    public static String APP_URL_DOMAIN = HTTP + LOCALHOST + "/laundrybg";

    private static String REGISTER_API = APP_URL_DOMAIN+"/appregister/register.dop";//注册
    private static String LOGIN_API = APP_URL_DOMAIN + "/applogin/logincheck.dop";

    public static String getRegisterApi() {
        return REGISTER_API;
    }

    public static String getLoginApi() {
        return LOGIN_API;
    }
}
