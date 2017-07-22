package cn.tedu.shoot;

/**
 * 类：英雄机类，继承FlyingObjcet（飞行物类）
 * 
 * @author Jankin
 *
 */
public class Hero extends FlyingObject {
	private int doubleFire;
	public Hero() {
		image = ShootGame.heroI;
		x = ShootGame.WIDTH / 2;
		y = ShootGame.HEIGHT / 2;
		doubleFire = 500;
	}

	@Override
	public void step() {
		
	}
	
	public Bullet[] shoot() {
		int xStep = this.width / 4;
		int yStep = 20;
		if (doubleFire > 0) {
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x + xStep,this.y - yStep);
			bs[1] = new Bullet(this.x + 3 * xStep,this.y -yStep);
			doubleFire -= 2;
			System.out.println("doubleFire" + doubleFire);
			return bs;
		} else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x + 2 * xStep,this.y -yStep);
			System.out.println("没有双倍");
			return bs;
			
		}
	}
}
