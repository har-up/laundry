package www.android.he.com.laundry.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.activity.SingleFragmentActivity;

/**
 * Created by dao on 2018/1/4.
 */

public class RegisterFragment extends Fragment{

    public static Fragment newInstance(){
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }
}
