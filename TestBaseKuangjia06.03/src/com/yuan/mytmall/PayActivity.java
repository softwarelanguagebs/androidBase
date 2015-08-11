
package com.yuan.mytmall;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

public class PayActivity extends Activity {

    private MagicScrollView mScrollView;
    private MagicTextView mIncomeTxt;
    private MagicTextView mTotalMoneyTxt;
    private MagicTextView mIncTotalTxt;
    private MagicTextView mIncSevTxt;
    private MagicTextView mIncWeekTxt;
    private MagicTextView mIncMonTxt;
    private MagicTextView mOverPerTxt;
    private MagicTextView mOverCountTxt;
    private LinearLayout mContainer;
    public static int mWinheight;
    int[] location = new int[2];

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int height = mContainer.getMeasuredHeight();
            Log.d("height  is ====>", "" + height);
            onMeasureTxt(mIncomeTxt);
            onMeasureTxt(mTotalMoneyTxt);
            onMeasureTxt(mIncTotalTxt);
            onMeasureTxt(mIncSevTxt);
            onMeasureTxt(mIncWeekTxt);
            onMeasureTxt(mIncMonTxt);
            onMeasureTxt(mOverPerTxt);
            onMeasureTxt(mOverCountTxt);
            mScrollView.sendScroll(MagicScrollView.UP, 0);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magic_scroll_layout);

        Rect fram = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(fram);
        mWinheight = fram.height();
        Log.d("winHeight is ====>", "" + mWinheight);
        mScrollView = (MagicScrollView) findViewById(R.id.magic_scroll);
        mIncomeTxt = (MagicTextView) findViewById(R.id.income_money);
        mTotalMoneyTxt = (MagicTextView) findViewById(R.id.total_money);
        mIncTotalTxt = (MagicTextView) findViewById(R.id.income_total_txt);
        mIncSevTxt = (MagicTextView) findViewById(R.id.income_sevdays_txt);
        mIncWeekTxt = (MagicTextView) findViewById(R.id.income_week_txt);
        mIncMonTxt = (MagicTextView) findViewById(R.id.income_month_txt);
        mOverPerTxt = (MagicTextView) findViewById(R.id.overperson_percent);
        mOverCountTxt = (MagicTextView) findViewById(R.id.overperson_count);
        mContainer = (LinearLayout) findViewById(R.id.container);

        mIncomeTxt.setValue(3.30);
        mTotalMoneyTxt.setValue(22800.56);
        mIncTotalTxt.setValue(58.56);
        mIncSevTxt.setValue(50.00);
        mIncWeekTxt.setValue(20.00);
        mIncMonTxt.setValue(40.20);
        mOverPerTxt.setValue(88.88);
        mOverCountTxt.setValue(300000000);
        initListener();
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private void initListener() {
        mScrollView.AddListener(mIncomeTxt);
        mScrollView.AddListener(mTotalMoneyTxt);
        mScrollView.AddListener(mIncTotalTxt);
        mScrollView.AddListener(mIncSevTxt);
        mScrollView.AddListener(mIncWeekTxt);
        mScrollView.AddListener(mIncMonTxt);
        mScrollView.AddListener(mOverPerTxt);
        mScrollView.AddListener(mOverCountTxt);
    }

    private void onMeasureTxt(MagicTextView view) {
        // 鐢ㄦ潵鑾峰彇view鍦ㄨ窛绂诲睆骞曢《绔殑璺濈(灞忓箷椤剁涔熸槸scrollView鐨勯《绔�)
        view.getLocationInWindow(location);
        view.setLocHeight(location[1]);
        Log.d("window y is ====>", "" + location[1]);
    }
}
