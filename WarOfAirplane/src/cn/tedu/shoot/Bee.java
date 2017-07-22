package cn.tedu.shoot;

import java.util.Random;

/**
 * 类：蜜蜂类，实现了Award(奖励)接口
 * @author Jankin
 *
 */
public class Bee extends FlyingObject implements Award {
	/** 小蜜蜂x坐标速度 */
	int xSpeed = 1;
	/** 小蜜蜂y坐标速度 */
	int ySpeed = 1;
	public Bee() {
		Random rand = new Random();
		image = ShootGame.bee;
		width = image.getWidth();
		height = image.getHeight();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = -this.height;
	}

	@Override
	public void step() {
		this.y += ySpeed;
		this.x += xSpeed;
		if (this.x <= 0 || this.x >= (ShootGame.WIDTH - this.width)) {
			xSpeed = -xSpeed;
		}
		
	}
}
