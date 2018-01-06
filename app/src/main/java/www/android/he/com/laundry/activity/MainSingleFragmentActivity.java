package www.android.he.com.laundry.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import www.android.he.com.laundry.R;

/**
 * Created by dao on 2017/12/24.
 */

public abstract class MainSingleFragmentActivity extends AppCompatActivity{

    protected abstract Fragment newFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.home_flayout_container);
        if (fragment == null){
            fragment = newFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.home_flayout_container,fragment)
                    .commit();
        }
    }
}
