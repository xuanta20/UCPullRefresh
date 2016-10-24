package com.example.tt.fragments.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.example.tt.fragments.R;
import com.example.tt.fragments.utils.ViewOffsetHelper;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by Joe on 2016-06-16
 * Email: lovejjfg@gmail.com
 */
public class CurveView extends View  {
    private static final String TAG = "TEST";
    private Paint paint;
    private Path path;
    private float currentX;
    private float currentY;
//    private View targetView;
    @BindColor(R.color.uc_blue)
    int UC_COLOR;
    @BindDimen(R.dimen.max_drag)
    int MAX_DRAG;
//    private ViewOffsetHelper sheetOffsetHelper;
//    private int targetHeight;


    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(UC_COLOR);
        path = new Path();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                currentX = event.getRawX();
//                float rawY = event.getRawY();
//                currentY = rawY;
//                currentY = rawY >= 200 ? 200 : rawY;
//                Log.e(TAG, "onTouchEvent: " + currentX + ";;;" + currentY);
//                targetView.setTranslationY(currentY*0.5f);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                currentX = 0;
//                currentY = 0;
//                targetView.setTranslationY(0);
//                invalidate();
//                break;
//        }
//        return true;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.moveTo(0, getMeasuredHeight());
        path.quadTo(currentX, currentY + getMeasuredHeight(), getWidth(), getMeasuredHeight());
        canvas.drawPath(path, paint);
    }

//    public void setTarget(View mView) {
//        targetView = mView;
//        targetHeight = targetView.getHeight();
//        Log.e(TAG, "setTarget: " + targetHeight);
//        sheetOffsetHelper = new ViewOffsetHelper(targetView);
//    }


    public void onDispatch(float dx, float dy) {
        currentY = dy > MAX_DRAG ? MAX_DRAG : dy;
        float ddy = dy - currentY;
        currentX = dx;
        Log.e(TAG, "onDispatch: " + ddy);
//        targetView.setTranslationY(currentY * 0.5f);
        if (dy > 0) {
            invalidate();
        } else {
//            sheetOffsetHelper.offsetTopAndBottom((int) ddy);
        }

    }


    public void onDispatchUp() {
        currentY = 0;
        invalidate();
    }
}