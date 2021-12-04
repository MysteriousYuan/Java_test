package pers.Yuan.game;

import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread{
    int x=230,y=600;

    int width =50,height=50;
    //飞机移动速度
    int speed=10;
    Image img=new ImageIcon("img/10011.png").getImage();

    //定义方向键的标志
    boolean up,down,left,right;

    public HeroPlane() {
    }

    public HeroPlane(int x, int y, int witdh, int height) {
        this.x = x;
        this.y = y;
        this.width = witdh;
        this.height = height;
    }

    @Override
    public void run() {
        while (true){
            if(up){
                y-=speed;
            }
            if(down){
                y+=speed;
            }
            if(left){
                x-=speed;
            }
            if(right){
                x+=speed;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
