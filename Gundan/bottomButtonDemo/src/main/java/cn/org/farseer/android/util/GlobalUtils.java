package cn.org.farseer.android.util;

import android.view.Display;
import android.view.WindowManager;

/**
 * Author  : 简洋
 * Date    : 2011-9-22
 *
 * Note    : 
 */
public class GlobalUtils 
{
	private static GlobalUtils instance;
	
	private int screenHeight;
	private int screenWidth;
	
	public GlobalUtils(WindowManager windowManager)
	{
		instance = this;
		Display display = windowManager.getDefaultDisplay();
		this.screenHeight = display.getHeight(); //屏幕高度
		this.screenWidth = display.getWidth();  //屏幕宽度
	}
	public static GlobalUtils getInstance()
	{
		return instance;
	}
	
	
	/**
	 * 获得屏幕的高度。
	 * @return
	 */
	public int getScreenHeight() {
		return screenHeight;
	}
	
	/**
	 * 获得屏幕的宽度。
	 * @return
	 */
	public int getScreenWidth() {
		return screenWidth;
	}
	
}
