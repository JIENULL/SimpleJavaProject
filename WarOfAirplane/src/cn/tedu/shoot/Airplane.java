package cn.tedu.shoot;

import java.util.Random;

/**
 * 类：敌机类，继承FlyingObject(飞行物)类，实现了Enemy（敌机）接口
 * @author Jankin
 *
 */
public class Airplane extends FlyingObject implements Enemy{
	/** 敌机下落速度 */
	int speed = 1;
	
	public Airplane() {
		Random rand = new Random();
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = -this.height;
	}

	@Override
	public void step() {
		this.y +=1;
		
	}
}
