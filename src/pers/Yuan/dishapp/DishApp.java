package pers.Yuan.dishapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
*
* 点菜的主程序
* */
public class DishApp {
    //提前准备一些菜品。展示给用户，同时用户可以点菜
    //定义集合（表示小饭店拥有的菜品
    static List<Dish> dishList=new ArrayList<>();
    //用户已点的菜品集合
    static List<Dish> personDish=new ArrayList<>();

    public static void main(String[] args) {
        //初始化菜品
        intDish();
        //创建扫描器对象，获取到控制台输入的内容
        Scanner s=new Scanner(System.in);
        //写死循环
        while (true){
            //给用户展示主菜单
            showMenu();
            //获取内容
            int num=s.nextInt();
            switch (num){
                case 1:
                    while (true){
                        //展示菜单
                        showDishMenu();
                        //获取用户输入内容
                        int id=s.nextInt();
                        //
                        if(id==0){
                        break;}
                        //从饭店的集合中获取到菜品的对象
                        Dish dish = dishList.get(id - 1);
                        System.out.println("亲，您点了:"+dish.name);
                        personDish.add(dish);

                    }

                case 2:
                    //展示已点菜品
                        showPersonDish();
                        break;
                case 3:
                    //买单
                    buy();
                    return;
            }
        }

    }

    /**
     *     展示菜单
     */
    private static void showDishMenu() {
        System.out.println("----请您点菜----");
        for (int i = 0; i < dishList.size(); i++) {
            Dish dish=dishList.get(i);
            System.out.println(dish.id+"\t"+dish.name+"\t"+dish.price);
        }
        System.out.println("----输入序号点菜。按0返回上一级----");
    }

    /**
     *     展示菜品
     */
    private static void showPersonDish() {
            System.out.println("----您已点的菜品----");
        for (Dish dish:personDish
             ) {
            System.out.println(dish.id+"\t"+dish.name+"\t"+dish.price);
        }

    }

    /**
     *    买单
     */
    private static void buy() {
        System.out.println("----正在计算中----");
        double total=0f;
        for (Dish dish:personDish
             ) {
            total+=dish.price;
        }
        System.out.println("您本次共消费"+total);
    }

    /**
     * 展示主菜单
     */
    public static void showMenu(){
        System.out.println("----主菜单----");
        System.out.println("菜单\t\t\t 1");
        System.out.println("已点菜品\t\t 2");
        System.out.println("买单\t\t\t 3");
        System.out.println("----根据编号请选择服务----");
    }

    /**
     * 初始化菜品
     */
    public static void intDish(){
        dishList.add(new Dish(1,"香辣肉丝",28.0));
        dishList.add(new Dish(2,"油闷大虾",38.0));
        dishList.add(new Dish(3,"猪肉炒菜",19.0));
        dishList.add(new Dish(4,"麻婆豆腐",29.0));
        dishList.add(new Dish(5,"麻辣龙虾",25.0));


    }
}
