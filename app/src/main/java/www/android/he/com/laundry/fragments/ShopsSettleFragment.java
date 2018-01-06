package www.android.he.com.laundry.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.entity.ClothType;

/**
 * Created by dao on 2017/12/24.
 */

public class ShopsSettleFragment extends Fragment{

    private List<ClothType> mClothTypess;
    private RecyclerView mRecyclerView;

    public static final String SHOPSELECTED =
            "www.android.he.com.laundry.fragments.ShopsSettleFragment.shopselected";


    public static ShopsSettleFragment newInstance(Serializable clothTypes){
        Bundle args = new Bundle();
        args.putSerializable(ShopsSettleFragment.SHOPSELECTED,clothTypes);
        ShopsSettleFragment shopsSettleFragment = new ShopsSettleFragment();
        shopsSettleFragment.setArguments(args);
        return shopsSettleFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mClothTypess = (List<ClothType>) args.getSerializable(SHOPSELECTED);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settle,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.settle_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new MyAdapter(mClothTypess));
        return view;
    }

    private class MyHolder extends RecyclerView.ViewHolder{


        private ClothType mClothType;
        private TextView mTextView;
        private TextView mTextViewPrice;
        private TextView mTextViewCount;
        private TextView mTextViewMoney;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.clean_type_name);
            mTextViewPrice = (TextView)itemView.findViewById(R.id.clean_type_price);
            mTextViewCount =(TextView)itemView.findViewById(R.id.settle_item_number);
            mTextViewMoney = (TextView)itemView.findViewById(R.id.settle_item_money);
        }

        public void bind(ClothType clothType){
            mClothType = clothType;
            mTextView.setText(clothType.getName());
            mTextViewPrice.setText(String.valueOf(clothType.getClean_price()));
            mTextViewCount.setText(String.valueOf(clothType.getCount()));
            mTextViewMoney
                    .setText(String.valueOf(clothType.getClean_price()*clothType.getCount()));
        }

    }

    private class MyAdapter extends  RecyclerView.Adapter<ShopsSettleFragment.MyHolder>{
        private List<ClothType> mClothTypes;

        public MyAdapter(List<ClothType> clothTypes) {
            mClothTypes = clothTypes;
        }

        @Override
        public ShopsSettleFragment.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.settle_item,parent,false);
            ShopsSettleFragment.MyHolder myHolder = new ShopsSettleFragment.MyHolder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.bind(mClothTypes.get(position));
        }


        @Override
        public int getItemCount() {
            return mClothTypes.size();
        }
    }
}
