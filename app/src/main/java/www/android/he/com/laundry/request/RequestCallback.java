package www.android.he.com.laundry.request;

/**
 * 网络请求回调
 */
public interface RequestCallback {

	/**
	 * 网络请求成功的回调
	 */
	public void requestSuccess(RequestBean bean, Config config);

	/**
	 * 网络请求失败的回调
	 * 
	 * 仅Volley请求的ErrorListener触发时，bean = null;
	 * 
	 * 其他情况bean != null。
	 */
	public void requestFail(RequestBean bean, Config config);

	/**
	 * token失效的回调
	 */
	public void requestTokenInvalid(String requestTag, Config config);

}
