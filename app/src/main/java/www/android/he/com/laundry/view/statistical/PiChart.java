package www.android.he.com.laundry.view.statistical;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dao on 2017/12/28.
 */

public class PiChart extends View {

    public PiChart(Context context) {
        super(context);
    }

    public PiChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PiChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PiChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
