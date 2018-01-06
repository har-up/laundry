package www.android.he.com.laundry.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import www.android.he.com.laundry.R;
import www.android.he.com.laundry.SetProvider.ClothTypeProvider;
import www.android.he.com.laundry.activity.ShopsSettleActivity;
import www.android.he.com.laundry.entity.ClothType;

/**
 * Created by dao on 2017/12/22.
 */

public class MainTabFragment extends Fragment{


    private RecyclerView mRecyclerView;
    private List<ClothType> mClothTypes;
    public static MainTabFragment newInstance(){
        return new MainTabFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_main,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.type_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mClothTypes = ClothTypeProvider.getClothTypes();
        mRecyclerView.setAdapter(new MyAdapter(mClothTypes));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_shops,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_my_shops:
                List<ClothType> clothTypes = new ArrayList<>();
                for (ClothType clothType : mClothTypes){
                    if (clothType.getCount() > 0){
                        clothTypes.add(clothType);
                    }
                }
                Intent intent = new Intent(getActivity(), ShopsSettleActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(ShopsSettleFragment.SHOPSELECTED, (Serializable) clothTypes);
                startActivity(intent );
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private ClothType mClothType;
        private ImageView mImageView;
        private TextView mTextView;
        private ImageButton mImageButtonAdd;
        private ImageButton mImageButtonSub;
        private TextView mTextViewPrice;
        private TextView mTextViewCount;

        public MyHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.imageView);
            mTextView = (TextView)itemView.findViewById(R.id.clean_type_name);
            mTextViewPrice = (TextView)itemView.findViewById(R.id.clean_type_price);
            mImageButtonAdd = (ImageButton) itemView.findViewById(R.id.number_button_add);
            mImageButtonSub = (ImageButton)itemView.findViewById(R.id.number_button_sub);
            mTextViewCount =(TextView)itemView.findViewById(R.id.num_textView);
            mImageButtonAdd.setOnClickListener(this);
            itemView.setOnClickListener(this);
            mImageButtonSub.setOnClickListener(this);
        }

        public void bind(ClothType clothType){
            mClothType = clothType;
            mTextView.setText(clothType.getName());
            mTextViewPrice.setText(String.valueOf(clothType.getClean_price()));
            mTextViewCount.setText(String.valueOf(clothType.getCount()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.number_button_add:
                    mTextViewCount.setText(String
                            .valueOf(Integer.parseInt(mTextViewCount.getText().toString()) + 1));
                    mClothType.setCount(Integer.parseInt(mTextViewCount.getText().toString()));
                    break;
                case R.id.number_button_sub:
                    mTextViewCount.setText(String
                            .valueOf(Integer.parseInt(mTextViewCount.getText().toString()) - 1));
                    mClothType.setCount(Integer.parseInt(mTextViewCount.getText().toString()));
                    break;
            }
        }
    }

    private class MyAdapter extends  RecyclerView.Adapter<MyHolder>{
        private List<ClothType> mClothTypes;

        public MyAdapter(List<ClothType> clothTypes) {
            mClothTypes = clothTypes;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.clean_type_item,parent,false);
            MyHolder myHolder = new MyHolder(view);
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
