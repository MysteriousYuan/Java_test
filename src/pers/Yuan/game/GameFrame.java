package pers.Yuan.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
    //创建英雄战机
    HeroPlane heroPlane;
    //定义子弹集合
    Vector<Buliet> buliets=new Vector<>();
    //敌机集合
    Vector<EnemyPlane> enemys=new Vector<>();
    GameFrame frame;
    public GameFrame(){
        frame=this;
        heroPlane=new HeroPlane();
        heroPlane.start();
        //设置窗体宽高
        this.setSize(500,760);
        this.setTitle("雷霆战机");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //窗口可见
        this.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //产生敌机的线程
        new Thread(new Runnable() {
            Random r=new Random();
            @Override
            public void run() {
                while (true){
                    EnemyPlane enemyPlane=new EnemyPlane(frame,r.nextInt(500),0);
                    enemyPlane.start();
                    enemys.add(enemyPlane);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * 在窗口上画内容
     */
    public void paint(Graphics g){
        //System.out.println("绘制画板");
        //画背景
        BufferedImage image=(BufferedImage) this.createImage(this.getSize().width,this.getSize().height);
        //高效缓存的画笔
        Graphics bi=image.getGraphics();

        bi.drawImage(new ImageIcon("img/MAP02_01.png").getImage(),0,0,null);
        bi.drawImage(heroPlane.img,heroPlane.x,heroPlane.y,heroPlane.width,heroPlane.height,null);

        //飞机发射
        for (int i = 0; i < buliets.size(); i++) {
            Buliet buliet=buliets.get(i);
            if(buliet.y>0)
                bi.drawImage(buliet.image,buliet.x,buliet.y-=buliet.speed,buliet.width,buliet.height,null);
            else
                buliets.remove(buliet);
        }
        //画敌机
        for (int i = 0; i < enemys.size(); i++) {
            EnemyPlane ep=enemys.get(i);
            if (ep.y <760)
                bi.drawImage(ep.img, ep.x, ep.y += ep.speed, ep.width, ep.height, null);
            else
                enemys.remove(ep);
        }
        //生效
        g.drawImage(image,0,0,null);
    }

    public static void main(String[] args) {
        GameFrame frame=new GameFrame();
        Player player=new Player(frame);
        frame.addKeyListener(player);
    }

}
