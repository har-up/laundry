package www.android.he.com.laundry.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.android.he.com.laundry.R;

/**
 * Created by dao on 2017/12/22.
 */

public class MoreTabFragment extends Fragment{
    public static MoreTabFragment newInstance(){
        return new MoreTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_more,container,false);
        return view;
    }
}
