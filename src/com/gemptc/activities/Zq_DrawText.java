package com.gemptc.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Zq_DrawText extends View {
	
	//定义变量
	private int mColor = 0xFF0099FF;
	//绘制
	private Bitmap mIconBitmap;
	private String mText = "学霸养成记";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	//绘图工具，在内存中通过Bitmap获得一个Canvas，再通过Canvas获得一个Paint
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;

	//纯色的透明度
	private float mAlpha;

	//决定Icon的位置
	private Rect mIconRect;
	//字体的位置
	private Rect mTextBound;
	private Paint mTextPaint;

	public Zq_DrawText(Context context)
	{
		this(context, null);
	}

	public Zq_DrawText(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	/**
	 * 获取自定义属性的值
	 * 初始化操作
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public Zq_DrawText(Context context, AttributeSet attrs,
			int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		//获取四个成员变量
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Zq_DrawText);

		int n = a.getIndexCount();

		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			//从自定义属性中获取
			switch (attr)
			{
			case R.styleable.Zq_DrawText_zicon:
				//获取Icon
				BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
				mIconBitmap = drawable.getBitmap();
				break;
			case R.styleable.Zq_DrawText_zcolor:
				mColor = a.getColor(attr, 0xFF45C01A);
				break;
			case R.styleable.Zq_DrawText_ztext:
				//传入文字
				mText = a.getString(attr);
				break;
			case R.styleable.Zq_DrawText_ztext_size:
				mTextSize = (int) a.getDimension(attr, TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
								getResources().getDisplayMetrics()));
				break;
			}

		}
		//回收
		a.recycle();
		//初始化数据
		mTextBound = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0Xff555555);
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取icon的边长
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextBound.height());

		int left = getMeasuredWidth() / 2 - iconWidth / 2;
		int top = getMeasuredHeight() / 2 - (mTextBound.height() + iconWidth)
				/ 2;
		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		//先把icon绘制出来
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
		int alpha = (int) Math.ceil(255 * mAlpha);
		drawSourceText(canvas, alpha);

	}

	/**
	 * 绘制文本
	 * 
	 * @param canvas
	 * @param alpha
	 */
	private void drawSourceText(Canvas canvas, int alpha)
	{
		mTextPaint.setColor(0xff333333);
		mTextPaint.setAlpha(255 - alpha);
		int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
		int y = mIconRect.bottom + mTextBound.height();
		
		canvas.drawText(mText, x, y, mTextPaint);

	}

	private static final String INSTANCE_STATUS = "instance_status";
	private static final String STATUS_ALPHA = "status_alpha";

	//防止Activity被销毁
	@Override
	protected Parcelable onSaveInstanceState()
	{
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
		bundle.putFloat(STATUS_ALPHA, mAlpha);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state)
	{
		if (state instanceof Bundle)
		{
			Bundle bundle = (Bundle) state;
			mAlpha = bundle.getFloat(STATUS_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
			return;
		}
		super.onRestoreInstanceState(state);
	}

}
