package www.android.he.com.laundry.request;

import java.io.Serializable;

/**
 * 数据请求的基础模型
 */
public class RequestBean implements Serializable {

//	private static final long serialVersionUID = -1597046427419406428L;

	private String status;// 响应代码[1：访问成功，2：请求参数非法，3：权限校验失败，
							// 4：服务器系统异常，5：服务器业务异常]
	public String beanJson;

	private String requestTag;// 请求标签用来区别同个页面的不同请求


	private String message;// "返回的提示语",

	private Integer errCode;// 当status为非1时 查找错误信息时使用

    private Integer statusCode;//

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrCode() {
		if (errCode == null) {
			errCode = -1;
		}
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getRequestTag() {
		return requestTag;
	}

	public void setRequestTag(String requestTag) {
		this.requestTag = requestTag;
	}

}
