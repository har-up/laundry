package www.android.he.com.laundry.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.fragments.LoginFragment;
import www.android.he.com.laundry.fragments.RegisterFragment;

/**
 * Created by dao on 2017/12/30.
 */

public class LoginActivity extends SingleFragmentActivity{
    @Override
    protected Fragment newFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
    }
}
