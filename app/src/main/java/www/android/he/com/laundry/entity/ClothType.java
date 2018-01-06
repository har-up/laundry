package www.android.he.com.laundry.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dao on 2017/12/23.
 */

public class ClothType  implements Serializable {
    int mId;
    private String mName;
    private double mClean_price;
    private String mUri;
    private int mCount;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getClean_price() {
        return mClean_price;
    }

    public void setClean_price(double clean_price) {
        mClean_price = clean_price;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }
}
