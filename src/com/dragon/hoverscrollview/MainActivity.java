package com.dragon.hoverscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private ObservableScrollView mScrollView;
	private LinearLayout mBar2;
	private LinearLayout mBar;
	private int[] mBarLocation = new int[2];
	private int[] mScrollViewLocation = new int[2];
	private RelativeLayout mBottomBar;
	private float mBottomBarY;
	protected float mInitBottomBarTop;
	protected float mInitBottomBarBottom;
	protected int firstScrollY;
	private ImageView mImg;
	protected float mImgY;
	private RelativeLayout mRootLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScrollView = (ObservableScrollView) findViewById(R.id.scrollView1);
		mImg = (ImageView) findViewById(R.id.img);
		mRootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		mBar = (LinearLayout) findViewById(R.id.bar);
		mBar2 = (LinearLayout) findViewById(R.id.bar2);
		mBottomBar = (RelativeLayout) findViewById(R.id.bottomBar);
		mScrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					getOriginPostion();
					break;
				default:
					break;
				}
				// mBottomBar.setY(620f);
				return false;
			}

			
		});
		mScrollView.setScrollViewListener(new ScrollViewListener() {


			@Override
			public void onScrollChanged(ObservableScrollView scrollView, int x,
					int y, int oldx, int oldy) {
				// TODO Auto-generated method stub
				showTopBar(scrollView);
				int offset = y - firstScrollY;
				showBottomBar(offset);
				mImg.setY(mImgY+offset*0.5f);
			}


		});
	}

	private void getOriginPostion() {
		firstScrollY = mScrollView.getScrollY();
		mBottomBarY = mBottomBar.getY();
		mImgY = mImg.getY();
	}
	
	private void showBottomBar(int offset) {
		int mBottomBarTop = mBottomBar.getTop();//��ȡ���������߶ȣ������ǲ��ָ߶ȣ�����SetY����Ȼֵ����
		if ((mBottomBar.getY()) >= mBottomBarTop
				&& (mBottomBar.getY()) <= mBottomBarTop
						+ mBottomBar.getHeight()) {
			//����Ļ�ײ����������ƶ�
			mBottomBar.setY(mBottomBarY + offset*0.5f);
		}
		if ((mBottomBar.getY()) < mBottomBarTop) {
			
			mBottomBar.setY(mBottomBarTop);
		}
		if ((mBottomBar.getY()) > mBottomBarTop
				+ mBottomBar.getHeight()) {
			mBottomBar.setY(mBottomBarTop
					+ mBottomBar.getHeight());
		}
	}
	
	private void showTopBar(ObservableScrollView scrollView) {
		if (mScrollViewLocation[1] == 0) {
			// ��һ�λ�ȡscrollview����Ļ�ĸ߶�
			scrollView.getLocationInWindow(mScrollViewLocation);
		}
		mBar.getLocationInWindow(mBarLocation);
		if (mBarLocation[1] <= mScrollViewLocation[1]) {
			// �����1����scrollview���������ص���2��ʾ��������֮��2���ء���1��ʾ
			mBar.setVisibility(View.INVISIBLE);
			mBar2.setVisibility(View.VISIBLE);
		} else {
			mBar.setVisibility(View.VISIBLE);
			mBar2.setVisibility(View.INVISIBLE);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
