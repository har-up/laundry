package www.android.he.com.laundry.SetProvider;

import java.util.ArrayList;
import java.util.List;

import www.android.he.com.laundry.entity.ClothType;

/**
 * Created by dao on 2017/12/23.
 */

public class ClothTypeProvider {
    private static List<ClothType> mClothTypes;

    public static List<ClothType> getClothTypes() {
        mClothTypes = new ArrayList<>();
        for (int i=0; i < 6; i++){
            ClothType clothType = new ClothType();
            clothType.setId(1);
            clothType.setName("上衣");
            clothType.setClean_price(25);
            clothType.setCount(3);
            mClothTypes.add(clothType);
        }
        return mClothTypes;
    }
}
