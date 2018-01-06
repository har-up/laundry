package www.android.he.com.laundry.Interface;


/**
 * 一些常量
 */
public interface IConstants {

    String KEY = "748608cf3ce28aef747596210761f900e07c65ef4b3947840deacdc1c30f06e7"; // 请求参数时必须携带的key
    int GET_LASTLOCATION_FAILED=-200;
    String REGISTER = "REGISTER";
    String LOGIN = "LOGIN";
    String FACILITY = "FACILITY";
    String LOCATION = "LOCATION";
    String UPDATAPAW = "UPDATAPAW";
    String FIXDISTANCE = "FIXDISTANCE";
    String TRAJECTORY="TRAJECTORY";//轨迹



    int BindSucceed = 0;//绑定设备成功
    int BindFail = 1;//绑定设备失败




    int Sort = 40; // 排序
    int NewMsg = 41; // 新消息
    int Insert = 42; // 插入数据

    int Cancel = 50; // 取消
    int Confirm = 51; // 确定
    int Select = 52; // 选择
    int Start = 53; // 出发地
    int End = 54; // 目的地
    int Geo = 55; // 反编码地理位置
    int Error = 56; // 出错
    int Success = 57; // 操作成功
    int Delete = 58; // 删除
    int Edit = 59; // 编辑
    int Skip = 60;
    int Complain = 61; // 投诉
    int Cancel_Apply = 62; // 取消申请
    int Cancel_Order = 63; // 取消订单
    int Apply = 64; // 申请
    int Submit = 65; // 提交
    int Reject_Apply = 66; // 拒绝乘客
    int Evaluate = 67; // 评价
    int Pay = 68; // 支付

    int SelectTime = 101; // 选择时间
    int SelectPlace = 102; // 选择网吧
    int SelectArea = 103; // 选择区服
    int SelectCity = 104; // 选择城市
    int SetWeixinCount = 105; // 设置账号
    int SetNote = 106; // 设置留言
    int SelectStartAddr = 107; // 选择出发地
    int SelectEndAddr = 108; // 选择目的地
    int SelectHomeAddr = 109; // 选择家
    int SelectWorkAddr = 110; // 选择工作地
    int WHEEL_REQUEST_CODE = 111; // 设置时间(滚轮)
    int SelectCoupon = 112; // 选择优惠券
    int SelectSurcharge = 113; // 选择空驶费
    int ConnectClosed = 114; // 链接关闭
    int Refresh = 115; // 刷新


    int LoginFail = 1000; // 失败
    int LoginWechat = 1001; // 微信登录
    int LoginQq = 1002; // QQ登录
    int LoginWeibo = 1003; // 微博登录


}
