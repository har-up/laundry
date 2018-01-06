package www.android.he.com.laundry.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.components.LimitLine;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.activity.LoginActivity;
import www.android.he.com.laundry.view.CircleImageView;

/**
 * Created by dao on 2017/12/22.
 */

public class MeTabFragment extends Fragment implements View.OnClickListener{
    public static MeTabFragment newInstance(){
        return new MeTabFragment();
    }

    private CircleImageView mMyIcon;
    private LinearLayout mMyInfo;
    private LinearLayout mMyOrder;
    private LinearLayout mMyAddress;
    private LinearLayout mSetting;
    private LinearLayout mService;
    private LinearLayout mAbout;
    private LinearLayout mShare;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        initUI(view);
        return view;
    }

    private void initUI(View view){
        mMyIcon = (CircleImageView) view.findViewById(R.id.my_icon);
        mMyInfo = (LinearLayout) view.findViewById(R.id.my_message);
        mMyOrder = (LinearLayout) view.findViewById(R.id.my_order);
        mMyAddress = (LinearLayout) view.findViewById(R.id.my_feedback);
        mSetting = (LinearLayout) view.findViewById(R.id.my_setting);
        mService = (LinearLayout) view.findViewById(R.id.my_kefu);
        mAbout = (LinearLayout) view.findViewById(R.id.my_about);
        mShare = (LinearLayout) view.findViewById(R.id.my_share);
        mMyIcon.setOnClickListener(this);
        mMyInfo.setOnClickListener(this);
        mMyOrder.setOnClickListener(this);
        mMyAddress.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mService.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_icon:
                toActivity(LoginActivity.class);
                break;
            case R.id.my_message:
                toActivity(LoginActivity.class);
                break;
            case R.id.my_order:
                toActivity(LoginActivity.class);
                break;
            case R.id.my_feedback:
                toActivity(LoginActivity.class);
                break;
            case R.id.my_setting:
                toActivity(LoginActivity.class);
                break;
            case R.id.my_kefu:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确认要拨打客服电话:18169696150？")
                        .setTitle("提示")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18169696150"));
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                    }).create().show();
                break;
            case R.id.my_about:
                break;
            case R.id.my_share:
                break;
        }
    }

    private void toActivity(Class actClass){
        Intent intent = new Intent(this.getActivity(),actClass);
        startActivity(intent);
    }
}
