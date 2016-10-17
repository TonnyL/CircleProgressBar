package io.github.marktony.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lizhaotailang on 16/10/16.
 */

public class CircleProgressBar extends View {

    private Paint mPaint; // 画笔对象的引用

    private float mCircleWidth; // 圆形进度条的宽度

    private int mMaxProgress; // 最大的进度
    private int mInitProgress; // 初始进度
    private int mCurrentProgress; // 当前的进度

    private int mCentreColor; // 圆形中内部的填充色
    private int mCircleColor; // 圆环的颜色
    private int mProgressColor; // 圆环进度的颜色
    private int mTextColor; // 文字进度部分字体的颜色
    private float mTextSize; // 文字进度字体的大小

    private int mStartAngle; // 进度开始的角度

    private boolean mTextProgressEnable; // 是否显示文字进度


    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        // 获取自定义属性和默认值，第一个参数为从用户属性中得到的值
        // 如果用户没有设置，那么就用默认的设置，也就是第二个参数的值

        // 圆环的宽度
        mCircleWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circleWidth, 10);

        // 最大的进度值
        mMaxProgress = typedArray.getInteger(R.styleable.CircleProgressBar_maxProgress, 100);

        // 默认的进度值
        mInitProgress = typedArray.getInteger(R.styleable.CircleProgressBar_initProgress, 0);

        mCurrentProgress = mInitProgress;

        // 圆中心的填充色
        mCentreColor = typedArray.getColor(R.styleable.CircleProgressBar_centreColor, 0);

        // 文字的颜色
        mTextColor = typedArray.getColor(R.styleable.CircleProgressBar_textColor, 0xff000000);

        // 文字的大小
        mTextSize = typedArray.getDimension(R.styleable.CircleProgressBar_textSize, 25);

        // 圆环的颜色
        mCircleColor = typedArray.getColor(R.styleable.CircleProgressBar_circleColor, 0xff50c0e9);

        // 圆环进度的颜色
        mProgressColor = typedArray.getColor(R.styleable.CircleProgressBar_progressColor, 0xffffc641);

        // 进度开始的角度
        mStartAngle = typedArray.getInteger(R.styleable.CircleProgressBar_startAngle, -90);

        // 是否显示文字进度
        mTextProgressEnable = typedArray.getBoolean(R.styleable.CircleProgressBar_textProgressEnable, false);

        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centre = getWidth()/2; // 圆心的x坐标
        int radius = (int) (centre - mCircleWidth / 2); // 圆环的半径

        // 画圆中心的填充色
        if (mCentreColor != 0) {
            mPaint.setAntiAlias(true); // 消除锯齿
            mPaint.setColor(mCentreColor); // 设置填充的颜色
            mPaint.setStyle(Paint.Style.FILL); //  设置样式为填充
            canvas.drawCircle(centre, centre, centre, mPaint); // 画出圆
        }

        // 画圆环
        mPaint.setColor(mCircleColor); // 设置圆环的颜色
        mPaint.setStyle(Paint.Style.STROKE); // 设置为空心
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环

        // 画圆弧，也就是进度
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆弧的宽度
        mPaint.setColor(mProgressColor); // 设置圆弧的颜色

        // oval 指定圆弧的外轮廓矩形区域
        // startAngle 指定进度开始的角度，－90表示从12点方向开始走，0表示从三点钟方向开始走，依次类推
        // 圆弧起始角度，单位为度
        // sweep angle 圆弧扫过的角度，顺时针方向，单位为度
        // use center 如果为true，在绘制圆时将把圆心包括在内，通常用于绘制扇形
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        mPaint.setStyle(Paint.Style.STROKE); // 设置为画圆弧
        canvas.drawArc(oval, mStartAngle, 360 * mCurrentProgress / mMaxProgress, false, mPaint); // 画出圆弧

        // 画文字进度
        mPaint.setStrokeWidth(0);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setTypeface(Typeface.DEFAULT);

        // 文字进度的百分比，需要先转换为float进行计算，否则都为0
        int percent = (int) (((float)mCurrentProgress / (float) mMaxProgress) * 100);
        float textWidth = mPaint.measureText(percent + "%");

        if (mTextProgressEnable && mCurrentProgress !=0) {
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + mTextSize/2, mPaint);
        }

    }

    public synchronized int getMaxProgress() {
        return mMaxProgress;
    }

    public synchronized void setMaxProgress(int maxProgress) {
        if (maxProgress < 0 ) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.mMaxProgress = maxProgress;
    }

    public synchronized int getCurrentProgress() {
        return  mCurrentProgress;
    }

    /**
     * 设置刷新进度，此为线程安全控件，需要同步
     * 刷新界面调用postInvalidate()能在非ui进程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress) {

        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }

        if (progress > mMaxProgress) {
            progress = mMaxProgress;
        }

        if (progress <= mMaxProgress) {
            this.mCurrentProgress = progress;
            postInvalidate();
        }

    }

    public float getmCircleWidth() {
        return mCircleWidth;
    }

    public void setmCircleWidth(float mCircleWidth) {
        this.mCircleWidth = mCircleWidth;
    }

    public int getmCurrentProgress() {
        return mCurrentProgress;
    }

    public void setmCurrentProgress(int mCurrentProgress) {
        this.mCurrentProgress = mCurrentProgress;
    }

    public int getmCentreColor() {
        return mCentreColor;
    }

    public void setmCentreColor(int mCentreColor) {
        this.mCentreColor = mCentreColor;
    }

    public int getmCircleColor() {
        return mCircleColor;
    }

    public void setmCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
    }

    public int getmProgressColor() {
        return mProgressColor;
    }

    public void setmProgressColor(int mProgressColor) {
        this.mProgressColor = mProgressColor;
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public float getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getmStartAngle() {
        return mStartAngle;
    }

    public void setmStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
    }

    public boolean ismTextProgressEnable() {
        return mTextProgressEnable;
    }

    public void setmTextProgressEnable(boolean mTextProgressEnable) {
        this.mTextProgressEnable = mTextProgressEnable;
    }
}
