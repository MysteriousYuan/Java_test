package pers.Yuan.game;

import javax.swing.*;
import java.awt.*;

public class EnemyPlane  extends Thread {
    public GameFrame gf;

    int x,y,width=50,height=50;
    int speed=2;

    Image img=new ImageIcon("img/10037.png").getImage();
    public EnemyPlane(GameFrame gf, int x, int y) {
        this.gf = gf;
        this.x = x;
        this.y = y;
    }

    public EnemyPlane(GameFrame gf, int x, int y, int width, int height) {
        super();
        this.gf = gf;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while (true){
            //碰撞到了
            if (hit()){
                System.out.println("hit..............");
                this.speed=0;
                this.img=new ImageIcon("img/MAP_YUN03.png").getImage();
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gf.enemys.remove(this);
                break;
            }
            if (this.y>=760){
                break;}
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hit() {
        Rectangle myrect=new Rectangle(this.x,this.y,this.width,this.height);
        Rectangle rect=null;
        for (int i = 0; i < gf.buliets.size(); i++) {
            Buliet buliet=gf.buliets.get(i);
            rect=new Rectangle(buliet.x,buliet.y-1,buliet.width,buliet.height);
            if (myrect.intersects(rect)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "EnemyPlane{" +
                "gf=" + gf +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", speed=" + speed +
                ", img=" + img +
                '}';
    }
}
