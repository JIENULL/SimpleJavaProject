package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 类：主类
 * 
 * @author soft01
 *
 */
public class ShootGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 窗体的宽 */
	public static final int WIDTH = 512;
	/** 窗体的高 */
	public static final int HEIGHT = 768;

	/** 背景图片 */
	public static BufferedImage background;
	/** 蜜蜂图片 */
	public static BufferedImage bee;
	/** 子弹图片 */
	public static BufferedImage bullet;
	/** 游戏结束图片 */
	public static BufferedImage gameover;
	/** 敌机图片 */
	public static BufferedImage airplane;
	/** 英雄机图片1 */
	public static BufferedImage heroI;
	/** 英雄机图片2 */
	// public static BufferedImage hero1;
	/** 暂停状态图片 */
	public static BufferedImage pause;
	/** 开始状态图片 */
	public static BufferedImage start;

	static {
		try {
			background = ImageIO.read(ShootGame.class.getResource("background2.jpg"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet1.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane3.png"));
			heroI = ImageIO.read(ShootGame.class.getResource("boss3.png"));
			// hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			start = ImageIO.read(ShootGame.class.getResource("gameName.png"));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

	// 创建对象数组
	Hero hero = new Hero();
	FlyingObject[] flyings = {};
	Bullet[] bullets = {};

	
	/**
	 * 创建敌人对象
	 * 
	 */
	public FlyingObject nextOne() {
		Random rand = new Random();
		if (rand.nextInt(100)+1 <=10) {
			return new Bee();
		} else {
			return new Airplane();
		}
	}
	
	/**
	 * 向飞行物数组中添加敌机或小蜜蜂
	 */
	int flyEnterIndex = 0;
	public void enterAction() {
		flyEnterIndex++;
		if (flyEnterIndex % 40 == 0) {
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length-1] = obj;
			System.out.println("添加成功");
		}
		System.out.println("给flyings数组添加对象");
	}
	/**
	 * 向子弹数组中添加元素
	 */
	int shootIndex = 0;
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 20 == 0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
		}
	}
	
	/** 飞行物和子弹走一步 */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		for (int i = 0; i < bullets.length;i ++) {
			bullets[i].step();
		}
	}
	
	
	
	/**
	 * 开始执行
	 */
	public void action() {
		Timer timer = new Timer();
		/** 定时器执行的时间间隔 */
		int intervel = 10;
		timer.schedule(new TimerTask() {
			public void run() {
				enterAction();
				repaint();
				stepAction();// 飞行物移动
				shootAction();// 子弹移动
				System.out.println("重画所有物体");
			}
		}, intervel, intervel);
	}

	/**
	 * 画所有的物体
	 */
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintHero(g);
		paintFlyingObject(g);
		paintBullet(g);
		System.out.println("画所有物体");
	}

	/**
	 * 画英雄机
	 */
	public void paintHero(Graphics g) {
		System.out.println("画英雄机");
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	/**
	 * 画飞行物
	 */
	public void paintFlyingObject(Graphics g) {
		System.out.println("画飞行物");
		
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
			System.out.println(f.x + "," +f.y);
		}
	}
	
	public void paintBullet(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
	
	/** main方法，程序的开始 */
	public static void main(String[] args) {
		// 创建窗体
		JFrame frame = new JFrame("飞机大战");
		ShootGame game = new ShootGame();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// 程序开始
		game.action();
	}
}
