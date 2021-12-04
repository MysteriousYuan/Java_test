package pers.Yuan.yanzhengma;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class ImageCode {

    //数组
    static String[] strs={"a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
            "5","6","7","8","9","0"};

    public static void main(String[] args) throws Exception{
        //大的需求:通过java代码的方式生成图片（图片上含有字母或者数字或者干扰线)
        /**
         * 1.画板 纸
         * 2.准备画笔
         * 3.随机从数组中获取4个
         * 4.通过画笔把获取到的数据画到画板上
         * 5.生成一张真正的图片
         */
        Random random=new Random();

        //定义图片宽高
        int width=150;
        int height=50;
        //图片的类型
        int imageType=BufferedImage.TYPE_INT_RGB;
        //int imageType=1;

        //1.画笔 纸  JDK中提供画板类，Ctrl+p快捷键查看方法参数
        BufferedImage image=new BufferedImage(width,height,imageType);
        // 大的需求: 把图片的颜色修改
        //先获取到画笔的对象
        Graphics graphics = image.getGraphics();
        //设置画笔颜色 随机颜色
        graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        //画填充 矩形
        graphics.fillRect(0,0,width,height);

        //3.准备一些数据，随机从数组中获取4个
        int x=15;
        int y=30;

        graphics.setFont(new Font("黑体",Font.BOLD,25));
        for (int i = 0; i < 4; i++) {
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));

            x+=20;
            int num=random.nextInt(strs.length);
            String str=strs[num];
            //将取到的字符串画上去
            graphics.drawString(str,x,y);

        }
        //画干扰线

        for (int i = 0; i < 4; i++) {
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            int x1=random.nextInt(30);
            int y1=random.nextInt(50);
            int x2=random.nextInt(30)+120;
            int y2=random.nextInt(50);
            graphics.drawLine(x1,y1,x2,y2);
        }
        //5.把image生成到本地的磁盘上
        ImageIO.write(image,"jpg",new File("C:\\Users\\Yuan\\Desktop\\demo\\yzm.jpg"));
    }
}
