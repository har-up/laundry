package www.android.he.com.laundry.request;

/**
 * RequestMethod 枚举类型，目前仅设置GET和POST两种请求方式
 */
public enum RM {

	GET(0), POST(1);

	private RM(int method) {
		this.method = method;
	}

	private int method;

	public int getIndex() {
		return method;
	}

	public void setIndex(int method) {
		this.method = method;
	}

}
