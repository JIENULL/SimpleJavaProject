package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/**
 * 抽象类：所有飞行物的父类
 * 
 * @author Jankin
 *
 */
public abstract class FlyingObject {
	/** 飞行物的图片 */
	BufferedImage image;
	/** 飞行物的x坐标 */
	int x;
	/** 飞行物的y坐标 */
	int y;
	/** 飞行物的图片高度 */
	int height;
	/** 飞行物的图片宽度 */
	int width;
	
	/** 飞行物走一步 */
	public abstract void step();
}
