package pers.Yuan.game;

import javax.swing.*;
import java.awt.*;

public class Buliet {
    int x,y;
    int width=50,height=50;
    int speed=10;

    Image image=new ImageIcon("img/30024.png").getImage();

    public Buliet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Buliet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
