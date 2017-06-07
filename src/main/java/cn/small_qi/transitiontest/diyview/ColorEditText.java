package cn.small_qi.transitiontest.diyview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by small_qi on 2017/6/7.
 */

public class ColorEditText extends AppCompatEditText {
    private int repeatTime;
    private int sa,sr,sg,sb,ea,er,eg,eb;
    public ColorEditText(Context context) {
        super(context);
    }
    public ColorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ColorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setTextTransitionColor(int startColor,int endColor){
        sa= Color.alpha(startColor);
        sr=Color.red(startColor);
        sg=Color.green(startColor);
        sb=Color.blue(startColor);
        ea=Color.alpha(endColor);
        er=Color.red(endColor);
        eg=Color.green(endColor);
        eb=Color.blue(endColor);
        startTransition();
    }
    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }
    private void startTransition() {
        ValueAnimator colorAnim =ValueAnimator.ofFloat(0,1);
        colorAnim.setDuration(repeatTime);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float sv = (float)animation.getAnimatedValue();
                float  ev= 1f-(float)animation.getAnimatedValue();
                setTextColor(Color.argb((int)(ea*ev+sa*sv),(int)(sr*sv+er*ev),(int)(sg*sv+eg*ev),(int)(sb*sv+eb*ev)));
                invalidate();
            }
        });
        colorAnim.start();
    }
}
