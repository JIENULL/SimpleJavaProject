package cn.tedu.shoot;
/**
 * 类：子弹类，继承了FlyingObject（飞行物）类。
 * @author soft01
 *
 */
public class Bullet extends FlyingObject{
	int speed = 3;
	public Bullet(int x, int y) {
		image = ShootGame.bullet;
		this.x = x;
		this.y = y;
	}
	@Override
	public void step() {
		y -= speed;
		
	}

}
