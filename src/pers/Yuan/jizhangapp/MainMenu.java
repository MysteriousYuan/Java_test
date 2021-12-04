package pers.Yuan.jizhangapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu {

    //创建一个集合来存储数组
    static List<Bills> billsList=new ArrayList<>();

    //类加载的时候会第一时间执行，在billsList里面加入初始化的数据
    static {
        billsList.add(new Bills("吃饭","农行","支出",200,"2021-10-01","家庭聚餐"));
        billsList.add(new Bills("逛街","现金","支出",5000,"2021-10-01","购物"));
        billsList.add(new Bills("股票","农行","收入",2000,"2021-10-02","股票上涨"));
        billsList.add(new Bills("工资","交行","收入",3000,"2021-10-03","发工资"));
        billsList.add(new Bills("交通","交行","支出",200,"2021-10-03","飞机票"));
        billsList.add(new Bills("吃饭","现金","支出",100,"2021-10-05","家庭聚餐"));
    }

    public static void main(String[] args) {
        run();
    }
    public static void showMain(){
        System.out.println("------随手记------");
        System.out.println("1.添加账务 2.删除账务 3.查询账务 4.退出系统");
        System.out.println("请输入功能序号【1-4】：");
    }
    public static void run(){
        showMain();

        //创建一个flag标志
        boolean flag=true;

        //创建扫描器
        Scanner scanner=new Scanner(System.in);

        while (flag){
            //获得输入选项
            int op=scanner.nextInt();
            //判断
            switch (op){
                case 1:
                    addBills();
                    break;
                case 2:
                    delBills();
                    break;
                case 3:
                    select();
                    break;
                case 4:
                    flag=false;
                    break;
                default:
                    System.out.println("请重新输入：");
            }
        }
        System.out.println("已成功退出系统！");

    }

    private static void delBills() {
        System.out.println("随手记>>账务删除");
        System.out.println("请输入：所要删除的id");
        Scanner inScanner=new Scanner(System.in);
        int id=inScanner.nextInt();
        billsList.remove(id-1);
        System.out.println("删除成功");
        showMain();
    }

    /**
     * 添加账务
     */
    private static void addBills() {
        System.out.println("随手记>>账务添加");
        Scanner inScanner=new Scanner(System.in);
        Bills bills=new Bills();

        System.out.println("请输入类别");
        bills.setName(inScanner.next());
        System.out.println("请输入账户");
        bills.setAccount(inScanner.next());
        System.out.println("请输入支出收入类型");
        bills.setType(inScanner.next());
        System.out.println("请输入金额");
        bills.setTotal(inScanner.nextDouble());
        System.out.println("请输入时间");
        bills.setTime(inScanner.next());
        System.out.println("请输入备注");
        bills.setDesc(inScanner.next());

        billsList.add(bills);
        System.out.println("添加账务成功！");
        showMain();
    }

    /**
     * 三种查询 1.查询所有 2.按时间区间查询 3.按收入和支出类型查询
     */
    private static void select() {
        System.out.println("随手记>>账务查询");
        System.out.println("1.查询所有 2.按时间区间查询 3.按收入和支出类型查询");
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        int op=scanner.nextInt();
        switch (op){
            case 1:
                selectAll();
                break;
            case 2:
                selectByDate();
                break;
            case 3:
                selectByType();
                break;
        }
        showMain();

    }
    private static void selectAll() {
        print(billsList);
    }

    private static void selectByDate() {
        //创建一个时间格式化的对象
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("随手记>>账务查询>>按时间区间来查询");
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入: 开始时间");
        String start=scanner.next();
        System.out.println("请输入: 结束时间");
        String end=scanner.next();
        List<Bills> billsList = MainMenu.billsList.stream().filter(bills -> {
            String time = bills.getTime();
            //把字符串解析成具体的时间
            try {
                Date startDate = format.parse(start);
                Date endDate = format.parse(end);
                Date timeDate = format.parse(time);
                if (timeDate.before(endDate) && timeDate.after(startDate)) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        print(billsList);
    }

    private static void selectByType() {
        System.out.println("随手记>>账务查询>>收入和支出的类型");
        System.out.println("请输入: 收入或者支出");
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        //收入 支出
        String type=scanner.next();
        //筛选一下集合中type是支出的元素
        //集合stream流
        List<Bills> billsList1 = billsList.stream().filter(bills -> {
            String type1 = bills.getType();
            return type1.equals(type);
        }).collect(Collectors.toList());
        print(billsList1);
    }
    public static void print(List<Bills> billsList){
        System.out.println("ID\t\t类别\t\t账户\t\t类型\t\t金额\t\t\t时间\t\t\t\t备注\t\t");
        for (int i = 0; i < billsList.size(); i++) {
            Bills bills=billsList.get(i);
            System.out.println(i+1+"\t\t"+bills.getName()+"\t\t"+bills.getAccount()+"\t\t"+bills.getType()+"\t\t"+bills.getTotal()+"\t\t"+bills.getTime()+"\t\t"+bills.getDesc());
        }
    }
}
