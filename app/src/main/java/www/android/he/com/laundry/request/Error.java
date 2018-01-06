package www.android.he.com.laundry.request;

public class Error {

    /**
     * token失效对应的errCode
     */
    public static final int TokenInvalid = 2000;

    /**
     * 没有权限
     */
    public static final int PERMISSION_ERROR = 401;

    /**
     * 非法访问
     */
    public static final int ILLEGAL_ACCESS = 403;

    /**
     * 用户不存在
     */
    public static final int USER_NOT_EXIST = 410;

    /**
     * 无效用户
     */
    public static final int INVALID_USER = 2001;

    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 2002;

    /**
     * 被封号
     */
    public static final int ACCOUNT_CLOSURE = 2004;

    // token失效时的提示语
    public static final String TokenInvalidString = "您在其他地方已登录，请重新登录"; // TODO

    // errCode为空时的提示语
    public static final String FormatError = "服务器出现异常";

    /*
     * Volley请求的ErrorListener触发时的提示语；
     * 同样也是requestFail()中RequestBean为空时的提示语；
     * (上述两种为同一情况)
     */
    public static final String NetError = "网络异常,请检查网络";

}
