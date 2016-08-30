package com.lanou3g.autohome.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 *
 * 右侧的字母索引View
 *
 * @author
 *
 */

public class SideBar extends View {

	//触摸事件
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

	// 26个字母
	public static String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	//选中
	private int choose = -1;
	//画笔
	private Paint paint = new Paint();


	private TextView mTextDialog;

	/**
	 * 为SideBar显示字母的TextView
	 *
	 * @param mTextDialog
	 */
	public void setTextView(TextView mTextDialog){
		this.mTextDialog = mTextDialog;
	}


	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}
	/**
	 *
	 * 重写的onDraw的方法
	 *
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight();//获取对应的高度
		int width = getWidth();//获取对应的宽度
		int singleHeight = height/ alphabet.length;//获取每一个字母的高度
		for (int i = 0; i < alphabet.length; i++) {
			paint.setColor(Color.rgb(85, 201, 255));
			paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体的样式
			paint.setAntiAlias(true);//抗锯齿
			paint.setTextSize(20);
			//选中的状态
			if (i == choose) {
				paint.setColor(Color.parseColor("#33000f"));
				paint.setFakeBoldText(true);//设置是否为粗体文字
			}
			//x坐标等于=中间-字符串宽度的一半
			float xPos = width / 2- paint.measureText(alphabet[i])/2;
			float yPos = singleHeight*i + singleHeight;
			canvas.drawText(alphabet[i], xPos, yPos, paint);
			paint.reset();//重置画笔
		}
	}


	//事件传递机制:一个是dispatchTouchEvent:负责分发事件 一个是onTouchEvent执行事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		final int action = event.getAction();
		final float y = event.getY();//点击y坐标
		final int oldChoose = choose;

		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		//点击y坐标所占高度的比例*alphabet数组的长度就等于点击alphabet中的个数
		final int hit = (int)(y / getHeight() * alphabet.length);

		switch (action) {
			case MotionEvent.ACTION_UP:
				setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景颜色
				choose = -1;
				invalidate();
				if (mTextDialog != null) {
					mTextDialog.setVisibility(View.INVISIBLE);
				}
				break;

			default:
				setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景颜色
				//setBackgroundResource(R.drawable.sidebar_background);
				if (oldChoose != hit) {
					if (hit>=0 && hit< alphabet.length) {
						if (listener != null) {
							listener.onTouchingLetterChanged(alphabet[hit]);
						}
						if (mTextDialog != null) {
							mTextDialog.setText(alphabet[hit]);
							mTextDialog.setVisibility(View.VISIBLE);
						}
						choose = hit;
						invalidate();
					}
				}
				break;
		}

		return true;
	}
	/**
	 * 向外松开的方法
	 *
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 *
	 * 接口
	 *
	 * @author
	 *
	 */
	public interface OnTouchingLetterChangedListener {
		 void onTouchingLetterChanged(String s);
	}

}