package gjg.com.stepview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author : gongdaocai
 * @date : 2017/6/22
 * FileName:
 * @description:
 */


public class StepView extends View {
    //总步数
    private int mStepNum;
    //默认颜色
    private int mNormalColor;
    //已走过的步的颜色
    private int mStepColor;
    //线的宽度
    private int mLineWidth;
    //圆的半径
    private int mCircleRadius;

    //当前步数
    private int mCurrentStep;
    //画笔
    private Paint mPaint;

    //控件的宽度、高度
    private int mWidth,mHeight;
    //每一步的宽度
    private float mStepPerWidth;


    public StepView(Context context) {
        this(context,null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        mStepNum = typedArray.getInt(R.styleable.StepView_step_num,3);
        mNormalColor = typedArray.getColor(R.styleable.StepView_step_normal_color, Color.GRAY);
        mStepColor = typedArray.getColor(R.styleable.StepView_step_step_color, Color.RED);
        mLineWidth = typedArray.getDimensionPixelSize(R.styleable.StepView_step_line_width,6);
        mCircleRadius = typedArray.getDimensionPixelSize(R.styleable.StepView_step_circle_radius,10);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int needHeight = mCircleRadius * 2 + mLineWidth * 2;
        setMeasuredDimension(widthMeasureSpec, Math.max(height,needHeight));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        init();
    }
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mNormalColor);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mStepPerWidth = (mWidth-mCircleRadius*2 - mLineWidth) * 1.0f / (mStepNum-1);

        mCurrentStep = 0;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制默认背景
        mPaint.setColor(mNormalColor);
        float startX = mCircleRadius+mLineWidth*1.0f/2;
        float y = mHeight*1.0f/2;
        float endX = mWidth - (mCircleRadius+mLineWidth*1.0f/2);
        canvas.drawLine(startX,y,endX,y,mPaint);

        for(int i=0;i<mStepNum;i++){
            float cx = startX + i*mStepPerWidth;
            canvas.drawCircle(cx,y,mCircleRadius,mPaint);
        }
        //绘制步数
        mPaint.setColor(mStepColor);
        canvas.drawLine(startX,y,startX+mCurrentStep*mStepPerWidth*1.0f,y,mPaint);
        for(int i=0;i<=mCurrentStep;i++){
            float cx = startX + i*mStepPerWidth;
            canvas.drawCircle(cx,y,mCircleRadius,mPaint);
        }
    }

    public void changeStep(int step){
        mCurrentStep = step;
        if(mCurrentStep < mStepNum){
            invalidate();
        }
    }

    public void setMaxStep(int maxStep){
        if(maxStep > 3){
            mStepNum = maxStep;
        }
    }
}
