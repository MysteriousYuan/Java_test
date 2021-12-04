package pers.Yuan.aboutPhoto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 电子相册
 */
public class MyImage extends JPanel {
    //定义成员图像
    BufferedImage bgImage;

    public static void main(String[] args) {
        //创建窗口
        JFrame frame = new JFrame();
        //设置大小
        frame.setSize(1000, 700);
        //设置标题
        frame.setTitle("java版电子相册");
        //居中显示
        frame.setLocationRelativeTo(null);
        //关闭窗口，把JVM停止
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建面板对象
        MyImage myImage = new MyImage();
        //把面板对象存放到窗口
        frame.add(myImage);
        //调用初始化图片的方法
        myImage.initImgs();
        //把图片画到窗口上,调用绘图的方法
        //myImage.repaint();

        //让ff一直变，开启新的线程
        myImage.begin();
        //显示出窗口
        frame.setVisible(true);
    }

    //标志，表示数组的下标索引值
    int num = 0;

    /**
     * 让ff变量的值一直变
     */
    public void begin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    bgImage = images[num];
                    num++;
                    if (num == 4) {
                        num = 0;
                    }

                    while (true) {
                        if (ff < 100f) {
                            ff += 2f;
                            repaint();
                        } else {
                            ff = 0f;
                            break;
                        }


                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    //图片显示比例值
    float ff = 0f;

    //需要重写父类的方法，提供绘图的方法 Ctrl+o
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //转换成子类对象(提供更多的方法
        Graphics2D graphics2D = (Graphics2D) g;

        //画东西
        if (bgImage != null) {
            //加入淡入的效果
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(ff / 100f));
            //把图片画到窗口
            graphics2D.drawImage(bgImage, 0, 0, bgImage.getWidth(), bgImage.getHeight(), null);
        }
    }

    //定义图片的数组
//    BufferedImage表示图片的对象
    BufferedImage[] images = new BufferedImage[4];

    /**
     * 加载提前准备好的一些图片
     */
    public void initImgs() {
        for (int i = 1; i <= 4; i++) {
            try {
                BufferedImage image = ImageIO.read(MyImage.class.getResource("/images/bg" + i + ".jpg"));
                images[i - 1] = image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bgImage = images[0];
    }

}
