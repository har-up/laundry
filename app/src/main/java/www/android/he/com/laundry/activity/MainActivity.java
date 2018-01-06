package www.android.he.com.laundry.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.fragments.MainTabFragment;
import www.android.he.com.laundry.fragments.MeTabFragment;
import www.android.he.com.laundry.fragments.MoreTabFragment;
import www.android.he.com.laundry.fragments.ShareTabFragment;

public class MainActivity extends MainSingleFragmentActivity implements RadioGroup.OnCheckedChangeListener{

    private FrameLayout mFragment_container;
    private RadioGroup  mRadioGroup;
    private RadioButton mRadioButton_main;
    private RadioButton mRadioButton_me;
    private RadioButton mRadioButton_share;
    private RadioButton mRadioButton_more;

    @Override
    protected Fragment newFragment() {
        return MainTabFragment.newInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }



    private void initUI(){
        mFragment_container = (FrameLayout)findViewById(R.id.home_flayout_container);
        mRadioGroup = (RadioGroup)findViewById(R.id.home_radio_group);
        mRadioButton_main = (RadioButton)findViewById(R.id.home_tab_main);
        mRadioButton_me = (RadioButton)findViewById(R.id.home_tab_me);
        mRadioButton_share = (RadioButton)findViewById(R.id.home_tab_share);
        mRadioButton_more = (RadioButton)findViewById(R.id.home_tab_more);
        mRadioGroup.setOnCheckedChangeListener(this);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Fragment fragment = null;
        switch (checkedId){
            case R.id.home_tab_main:
                fragment = MainTabFragment.newInstance();
                break;
            case R.id.home_tab_me:
                fragment = MeTabFragment.newInstance();
                break;
            case R.id.home_tab_share:
                fragment = ShareTabFragment.newInstance();
                break;
            case R.id.home_tab_more:
                fragment = MoreTabFragment.newInstance();
                break;
        }
        if (fragment == null){
            return;
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_flayout_container,fragment)
                    .commit();
        }
    }
}
