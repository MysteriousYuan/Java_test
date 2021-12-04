package pers.Yuan.studentDemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 学生管理系统主程序
 */
public class StuMain {
    public static void main(String[] args) {
        System.out.println("----欢迎使用学生管理系统----");

        //创建学生对象
        Student student=new Student();

        //给学生对象的属性赋值
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入学号：");
        student.id=scanner.nextInt();
        System.out.println("请输入姓名：");
        student.username=scanner.next();
        System.out.println("请输入性别：");
        student.sex=scanner.next();
        System.out.println("请输入语文成绩：");
        student.chinese=scanner.nextInt();
        System.out.println("请输入数学成绩：");
        student.math=scanner.nextInt();
        System.out.println("请输入英语成绩：");
        student.english=scanner.nextInt();

        writeStudent(student);
    }

    /**
     * 把student数据写入到txt文档中
     * @param student
     */
    public static void writeStudent(Student student){
        //创建文件对象
        File file=new File("stu.txt");
        ///创建输出流
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(file));
            //写入内容
            bufferedWriter.write("学号\t姓名\t性别\t语文\t数学\t英语\t");
            //换行
            bufferedWriter.newLine();
            //写入学生数据
            bufferedWriter.write(student.id+"\t");
            bufferedWriter.write(student.username+"\t");
            bufferedWriter.write(student.sex+"\t");
            bufferedWriter.write(student.chinese+"\t");
            bufferedWriter.write(student.math+"\t");
            bufferedWriter.write(student.english+"\t");
            bufferedWriter.newLine();
            //刷新
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
