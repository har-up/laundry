package www.android.he.com.laundry.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import www.android.he.com.laundry.Interface.IConstants;
import www.android.he.com.laundry.R;
import www.android.he.com.laundry.request.Config;
import www.android.he.com.laundry.request.RM;
import www.android.he.com.laundry.request.RequestBean;
import www.android.he.com.laundry.request.RequestCallback;
import www.android.he.com.laundry.request.RequestParams;
import www.android.he.com.laundry.request.RequestUtil;
import www.android.he.com.laundry.utils.LogUtil;
import www.android.he.com.laundry.utils.PhoneUtils;
import www.android.he.com.laundry.utils.SPUtil;
import www.android.he.com.laundry.utils.ToastUtil;
import www.android.he.com.laundry.utils.Urls;

/**
 * Created by dao on 2017/12/30.
 */

public class LoginFragment extends Fragment implements View.OnClickListener,RequestCallback{


    private EditText mLoginUserName;
    private EditText mLoginPassword;
    private Button mLoginEnter;
    private TextView mToRegister;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initUI(view);
        return view;
    }

    private void initUI(View view){
        mLoginUserName = (EditText) view.findViewById(R.id.aty_login_username);
        mLoginPassword = (EditText) view.findViewById(R.id.aty_login_password);
        mLoginEnter = (Button) view.findViewById(R.id.aty_login_enter);
        mToRegister = (TextView) view.findViewById(R.id.aty_login_register);
        mLoginEnter.setOnClickListener(this);
        mToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aty_login_enter:
                loginTo();
                break;
            case R.id.aty_login_register:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,RegisterFragment.newInstance())
                        .commit();
                break;
        }
    }

    private void loginTo(){
        ToastUtil.init(getContext());
        SPUtil.initContext(getContext());
        if (TextUtils.isEmpty(getUserName())){
            ToastUtil.getInstance().toast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(getPassword())){
            ToastUtil.getInstance().toast("请输入密码");
            return;
        }
        if (!PhoneUtils.isMobileNO(getUserName())){
            ToastUtil.getInstance().toast("请输入正确的手机账号");
            return;
        }
        RequestParams requestParams = new RequestParams.Builder()
                .putParam("mobile",getUserName())
                .putParam("password",getPassword())
                .build();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Urls.getLoginApi();
        RequestUtil.requestData(requestQueue
                ,IConstants.LOGIN,
                url,
                RM.GET,
                RequestBean.class,
                requestParams,this);
    }

    private String getPassword() {
        return mLoginPassword.getText().toString().trim();
    }

    private String getUserName() {
        return mLoginUserName.getText().toString().trim();
    }

    @Override
    public void requestSuccess(RequestBean bean, Config config) {
        LogUtil.e("requestSuccess数据="+bean.beanJson);
        if (bean.getStatusCode()==2){
            ToastUtil.getInstance().toast("账号不存在");
            return;
        }
        if (bean.getStatusCode()==1){
            ToastUtil.getInstance().toast("密码错误");
            return;
        }
        String []userdata=bean.beanJson.split("=");
        LogUtil.e("用户："+userdata[0]);
        //存储用户信息
        SPUtil.getInstance().setData("beanJson",userdata[0]);
        //存储车辆信息

        /*String []adrs = userdata[1].replace("[","").replace("]","").split("\\},");
        SPUtil.getInstance().setData("adr_count",String.valueOf(adrs.length));
        LogUtil.e("地址表长："+adrs.length);
        for(int i = 0;i < adrs.length;i++){
            if(adrs[i].indexOf("}")<0){
                adrs[i]+="}";
            }
            SPUtil.getInstance().setData("adr_"+(i+1),adrs[i]);
        }*/

        //存储密码
        SPUtil.getInstance().setData("password",getPassword());

        //跳转到主界面
        getActivity().finish();

    }

    @Override
    public void requestFail(RequestBean bean, Config config) {

    }

    @Override
    public void requestTokenInvalid(String requestTag, Config config) {

    }
}
