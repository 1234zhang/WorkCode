package Week10;

import count.Stack;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Level2 {
    private static void copyFile(File oldFile, File newFile){
        BufferedOutputStream bo = null;
        BufferedInputStream bi = null;
        try {
            bi = new BufferedInputStream(new FileInputStream(oldFile));
            bo = new BufferedOutputStream(new FileOutputStream(newFile));
            byte[] bytes = new byte[1024];//一次读取1kb内容；
            int length = 0;//一次实际读取内容;
            while((length = bi.read(bytes, 0, bytes.length)) != -1){
                    bo.write(bytes, 0 ,bytes.length);
            }
            bo.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException{
        MyClass myCla = new MyClass();
        File newFile = new File("src/Week10/copyFile.txt");
        File oldFile = new File("src/Week10/redrock.txt");
        int len;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("src/Week10/redrock.txt"));
        try{
            for(Student stu : myCla.list){
                bufferedOutputStream.write(stu.toString().getBytes(StandardCharsets.UTF_8));
                bufferedOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        }catch (IOException e){

        }finally {
            bufferedOutputStream.close();
        }
        copyFile(oldFile,newFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/Week10/copyFile.txt"));
        byte[] bytes = new byte[1024];
        try{
            while((len = bufferedInputStream.read(bytes ,0,bytes.length)) != -1){
                    String test = new String(bytes,0,bytes.length, StandardCharsets.UTF_8);
                System.out.println(test);
            }
        }catch (IOException e){
            System.out.println("打开失败");
        }finally {
            bufferedInputStream.close();
        }
    }
}
