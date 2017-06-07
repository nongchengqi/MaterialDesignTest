package cn.small_qi.transitiontest.diyview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by small_qi on 2017/6/6.
 */

public class ColorTextView extends View {
    private String content;
    private Paint mPaint;
    private int textSize=24;
    private int textColor= Color.BLACK;
    private int startColor= Color.BLACK;
    private int endColor= Color.BLACK;
    private int repeatTime;
    private Rect textBounds;
    private boolean isAnim,textChange;
    private int sa,sr,sg,sb,ea,er,eg,eb;


    public ColorTextView(Context context) {
        super(context);
    }


    public ColorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public ColorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (content==null){
            return;
        }
        initPaint();
        canvas.save();
        canvas.drawText(content,getWidth()/2-textBounds.width()/2,getHeight()/2+textBounds.height()/2,mPaint);
        canvas.restore();
    }

    private void initPaint() {
        mPaint.setTextSize(textSize);
        if (!isAnim)
        mPaint.setColor(textColor);
        if (textChange){
            textBounds =new Rect();
            mPaint.getTextBounds(content,0,content.length(),textBounds);
            textChange=false;
        }
    }

    public void setText(String text){
        content=text;
        textChange=true;

    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public void setTextColor(int color){
        textColor=color;
        invalidate();
    }
    public void setTextTransitionColor(int startColor,int endColor){
        this.startColor=startColor;
        this.endColor=endColor;
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
        sa=Color.alpha(startColor);
        sr=Color.red(startColor);
        sg=Color.green(startColor);
        sb=Color.blue(startColor);

        ea=Color.alpha(endColor);
        er=Color.red(endColor);
        eg=Color.green(endColor);
        eb=Color.blue(endColor);

        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float sv = (float)animation.getAnimatedValue();
                float  ev= 1f-(float)animation.getAnimatedValue();
                mPaint.setColor(Color.argb((int)(ea*ev+sa*sv),(int)(sr*sv+er*ev),(int)(sg*sv+eg*ev),(int)(sb*sv+eb*ev)));
                invalidate();
            }
        });
        colorAnim.start();
        isAnim=true;
    }

}
