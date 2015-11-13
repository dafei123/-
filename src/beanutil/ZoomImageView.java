package beanutil;


import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.view.View.OnTouchListener;;

public class ZoomImageView extends ImageView implements OnGlobalLayoutListener, OnScaleGestureListener
      ,OnTouchListener{

	//初始化只进行一次
	private boolean mOnce;
	
	//初始化缩放的值
	private float mInitScale;
	
	//双击放大到达的值
	private float mMidScale;
	
	//放大的最大值
	private float mMaxScale;
	
	//捕获用户多点触控时缩放的比例
	private Matrix mScaleMatrix;
	
	private ScaleGestureDetector mScaleGestureDetector;
	
	//--------------------自由移动
	//记录上一次 多点触控的数量
	private int mLastPointercount;	
	//记录最后一次多指的中心位置
	private float mLastX;
	private float mLastY;
	
	private int mTouchSlop;
	private boolean isCanDrag;
	
	//private RectF matrixRectF;
	
	private boolean isCheckLeftAndRight;
	private boolean isCheckTopAndButton;
	
	//-----------------双击放大与缩小
	private GestureDetector mGestureDetector;
	
	private boolean isAutoScale;
	
	public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//init
		mScaleMatrix=new Matrix();
		setScaleType(ScaleType.MATRIX);
		
		mScaleGestureDetector=new ScaleGestureDetector(context, this);
		
	    setOnTouchListener(this);
	    
	    mTouchSlop=ViewConfiguration.get(context).getScaledTouchSlop();
	    mGestureDetector=new GestureDetector(context,
	    		new GestureDetector.SimpleOnGestureListener(){	    	
	    	@Override
	    	public boolean onDoubleTap(MotionEvent e) {
	    		
	    		if(isAutoScale)
	    	 return true;
	    		//用户单击的中心点
	    		float x=e.getX();
	    		float y=e.getY();
	    		
	    		if(getScale()<mMidScale){
	    			/*mScaleMatrix.postScale(
	    					mMidScale/getScale(),
	    					mMidScale/getScale(),
	    					x, y);
	    			setImageMatrix(mScaleMatrix);*/
	    			
	    			postDelayed(new AutoScaleRunnable(mMidScale, x, y), 16);
	    		    isAutoScale=true;
	    		}else{
	    			/*mScaleMatrix.postScale(mInitScale/getScale(),
	    					mInitScale/getScale(), x, y);
	    			setImageMatrix(mScaleMatrix);*/
	    			
	    			postDelayed(new AutoScaleRunnable(mInitScale, x, y), 16);
	    		    isAutoScale=true;
	    		}
	    		
	    		return true;
	    	}
	    });
	}

	//自动放大缩小
	public class AutoScaleRunnable implements Runnable{

		//缩放的目标值
		private float mTargetScale;
		//缩放的中心点
		private float x;
		private float y;
		//放大缩小的梯度
		private final float BIGGER=1.07f;//放大梯度
		private final float SMALl=0.93f;//缩小梯度
		
		private float tmpScale;
		
		public AutoScaleRunnable(float mTargetScale, float x, float y) {
			super();
			this.mTargetScale = mTargetScale;
			this.x = x;
			this.y = y;
			
			if(getScale()<mTargetScale){
				tmpScale=BIGGER;
			}
			if(getScale()>mTargetScale){
				tmpScale=SMALl;
			}
			
		}

		@Override
		public void run() {
			mScaleMatrix.postScale(tmpScale, tmpScale,x,y);
			checkBorderAndCenterWhenScale();
			setImageMatrix(mScaleMatrix);
			
			float currentScale=getScale();
			
			if((tmpScale>1.0f && currentScale<mTargetScale)
			||(tmpScale<1.0f && currentScale>mTargetScale)){
				postDelayed(this, 16);//在此执行run方法
			}else //设置我们的目标值
			{
				float scale=mTargetScale/currentScale;
				mScaleMatrix.postScale(scale, scale, x, y);
				checkBorderAndCenterWhenScale();
				setImageMatrix(mScaleMatrix);
				
				isAutoScale=false;
			}
			
		}
		
	}
	public ZoomImageView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		
	}

	public ZoomImageView(Context context) {
            this(context,null);
		
	}
 
	//对接口ongloblLayoutListener进行接入
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}
	
	//对接口ongloblLayoutListener进行移除
	@SuppressWarnings("deprecation")
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getViewTreeObserver().removeGlobalOnLayoutListener(this);
	}
	
	//获取ImageView加载完成的图片
	@Override
	public void onGlobalLayout() {
		
		if(!mOnce){
			//得到控件的宽和高（一般情况下都是屏幕的宽高）
			int width=getWidth();
			int height=getHeight();
			//得到图片以及宽和高
			Drawable d=getDrawable();//得到我们的图片
			if(d==null)
			return;
		int dw=d.getIntrinsicWidth();//图片的宽
		int dh=d.getIntrinsicHeight();//图片的高
		
		float scale=1.0f;//设置默认缩放的值
		
		//如果图片的宽度大于控件的宽度，但是高度小与控件的高度，我们将其缩小
		if(dw>width && dh<height)
		{
			scale = width*1.0f/dw;
		}
		
		//如果图片的高度大于控件的高度，但是宽度小与控件的宽度，我们将其缩小
		if(dh>height && dw<width)
		{
			scale=height*1.0f/dh;
		}
		
		//如果图片的高度和宽度都大于控件的或者图片的高度和宽度都大于控件的，
		//我们取缩放的最小值,将图片缩放至控件的内部；
		if((dw>width && dh>height) || (dw<width && dh<height)){
			scale=Math.min(width*1.0f/dw, height*1.0f/dh);
		}
		
		//得到初始化时缩放的比例
		mInitScale=scale;
		mMaxScale=mInitScale*4;
		mMidScale=mInitScale*2;
		
		//将图片移动至控件的中心
		int dx=getWidth()/2-dw/2;
		int dy=getHeight()/2-dh/2;
		
		mScaleMatrix.postTranslate(dx, dy);//平移
		mScaleMatrix.postScale(mInitScale, mInitScale,width/2,height/2);//缩放
		setImageMatrix(mScaleMatrix);
		
		
			mOnce=true;//保证只进行一次缩放图片的处理
		}
		
	}
	

	//得到当前图片缩放的比例
	public float getScale(){
		float[] values=new float[9];
		mScaleMatrix.getValues(values);
		return values[Matrix.MSCALE_X];
	}
	//缩放的区间：initScale,maxScale
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		
		float scale=getScale();
		float scaleFator=detector.getScaleFactor();//当前缩放的值
		
		if(getDrawable()==null){
			return true;
		}
		
		//缩放控制
		if((scale<mMaxScale && scaleFator>1.0f)
			||(scale>mInitScale && scaleFator<1.0f))
		{
			if(scale*scaleFator<mInitScale){
				scaleFator=mInitScale/scale;
			}
			if(scale*scaleFator>mMaxScale){
				scaleFator=mMaxScale/scale;
			}
			//缩放
			mScaleMatrix.postScale(scaleFator, scaleFator, 
					detector.getFocusX(),detector.getFocusY());
			
			checkBorderAndCenterWhenScale();//在缩放时进行边界控制以及位置的控制方法的
			setImageMatrix(mScaleMatrix);
		}
		
		
		return true;
	}
	
	
	//获得图片放大缩小以后的宽和高，以及l,r,t,b
	private RectF getMatrixRectF(){
		
		Matrix matrix=mScaleMatrix;
		RectF rectF=new RectF();
		
		Drawable drawable=getDrawable();
		if(drawable!=null)
		{
			rectF.set(0, 0, drawable.getIntrinsicWidth(), 
					drawable.getIntrinsicHeight());
			matrix.mapRect(rectF);
		}
			
		return rectF;
	}

	//在缩放时进行边界控制以及位置的控制
	private void checkBorderAndCenterWhenScale() {
		// TODO Auto-generated method stub
		RectF rect=getMatrixRectF();
		
		float deltaX=0;
		float deltaY=0;
		
		int width=getWidth();
		int height=getHeight();//得到控件的宽和高
		
		//缩放时进行边界检测，防止出现白边
		if(rect.width()>=width){//图片的宽度大于屏幕的宽度
			if(rect.left>0){
				//图片在屏幕左边有空隙
				deltaX=-rect.left;
			}
			if(rect.right<width){
				//图片右边距离屏幕有空隙
				deltaX=width-rect.right;
			}
		}
		
		if(rect.height()>=height){//图片高度大于控件高度
			
			if(rect.top>0){
				//图片与屏幕上边界有空隙
				deltaY=-rect.height();
			}
			if(rect.bottom<height){
				//图片与屏幕西边界有空隙
				deltaY=height-rect.bottom;
			}
		}
		
		//如果图片的宽度或者高度小于控件的宽度或者高度，让其居中显示
		if(rect.width()<width){
			deltaX=width/2f-rect.right+rect.width()/2f;
		}
		
		if(rect.height()<height){
			deltaY=height/2f-rect.bottom+rect.height()/2f;
		}
		
		mScaleMatrix.postTranslate(deltaX, deltaY);
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector arg0) {
		
	}
	//处理触摸事件
	@Override
	public boolean onTouch(View arg0, MotionEvent event)
	{
	/*if(MotionEvent.ACTION_DOWN==MotionEvent.ACTION_UP){
		Drawable drawable=getDrawable();
		drawable.Set
	}*/
		
		
		if(mGestureDetector.onTouchEvent(event))
			return true;
		mScaleGestureDetector.onTouchEvent(event);//得到手指多点触控的坐标	
		float x=0;
		float y=0;
		//得到多点触控的数量
		int pointerCount=event.getPointerCount();
		//遍历每个点的位置得出中心点的位置
		for(int i=0;i<pointerCount;i++)
		{
			x+=event.getX(i);
			y+=event.getY(i);
		}
		x/=pointerCount;
		y/=pointerCount;
		if(mLastPointercount!=pointerCount)
		{
			isCanDrag=false;//手指发生改变后重新进行判断
			mLastX=x;
			mLastY=y;
		}
		mLastPointercount=pointerCount;
		RectF rectF=getMatrixRectF();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(rectF.width()>getWidth()+0.01 || rectF.height()>getHeight()+0.01)
			{
				
				getParent().requestDisallowInterceptTouchEvent(true);
				
			}
			
			break;
		case MotionEvent .ACTION_MOVE:
			
			if(rectF.width()>getWidth()+0.01 || rectF.height()>getHeight()+0.01)
			{
				
				getParent().requestDisallowInterceptTouchEvent(true);
				
			}
			
			float dx=x-mLastX;
			float dy=y-mLastY;
			
			if(!isCanDrag){
				isCanDrag=isMoveAction(dx,dy);
			}
			if(isCanDrag){
			RectF rectF1=getMatrixRectF();//得到缩放以后的宽高
			if(getDrawable()!=null){
				isCheckLeftAndRight=isCheckTopAndButton=true;
				//图片的宽度小于控件的宽度，不允许横向移动
				if(rectF1.width()<getWidth()){
					isCheckLeftAndRight=false;
					dx=0;
				}
				//图片的高度小于空间的高度，不允许纵向移动
				if(rectF1.height()<getHeight()){
					isCheckTopAndButton=false;
					dy=0;
				}
				
				mScaleMatrix.postTranslate(dx, dy);
				checkBordeWhenTranslate();
				setImageMatrix(mScaleMatrix);
			}	
			}
			mLastX=x;
			mLastY=y;
			break;
        case MotionEvent .ACTION_UP:
        case MotionEvent .ACTION_CANCEL:
			mLastPointercount=0;
			break;
			
		default:
			break;
		}
		return true;
	}

	
	//当移动时添加边界检查
	private void checkBordeWhenTranslate() {
		RectF rectF=getMatrixRectF();
		float deltaX=0;
		float deltaY=0;
		
		int width=getWidth();
		int height=getHeight();
		
		if(rectF.top>0 && isCheckTopAndButton){
			deltaY=-rectF.top;
		}
		
		if(rectF.bottom<height && isCheckTopAndButton){
			deltaY=height-rectF.bottom;	
		}
		if(rectF.left>0 && isCheckLeftAndRight){
			deltaX=-rectF.left;
		}
		if(rectF.right<width && isCheckLeftAndRight){
			deltaX=width-rectF.right;
		}
		mScaleMatrix.postTranslate(deltaX, deltaY);
	}

	//判断是否足以移动
	private boolean isMoveAction(float dx, float dy) {
		//返回true表示当前可以移动
		return Math.sqrt(dx*dx+dy*dy)>mTouchSlop;
	}

}
