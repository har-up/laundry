package www.android.he.com.laundry.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.entity.ClothType;
import www.android.he.com.laundry.fragments.ShopsSettleFragment;

/**
 * Created by dao on 2017/12/24.
 */

public class ShopsSettleActivity extends AppCompatActivity{


    private TextView mTotalTextView;
    private Button mPayButton;
    private Serializable mGetSerial;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_settle);
        mGetSerial = getIntent().getSerializableExtra(ShopsSettleFragment.SHOPSELECTED);
        initUI();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.settle_fragment_container);
        if (fragment == null){
            fragment = newFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.settle_fragment_container,fragment)
                    .commit();
        }
    }

    protected android.support.v4.app.Fragment newFragment() {
        return ShopsSettleFragment
                .newInstance(getIntent().getSerializableExtra(ShopsSettleFragment.SHOPSELECTED));
    }

    private void initUI(){
        mTotalTextView = (TextView) findViewById(R.id.settle_total_money);
        mPayButton = (Button) findViewById(R.id.settle_pay_button);
        List<ClothType> list = (List<ClothType>) mGetSerial;
        double moneyNeedPay = 0;
        for (ClothType clothType : list){
            moneyNeedPay += clothType.getClean_price() * clothType.getCount();
        }
        mTotalTextView.setText(String.valueOf(moneyNeedPay));
    }


}
