package crawler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Brandon.
 * @date 2019/4/1.
 * @time 20:04.
 */

public class ThreadCrawl implements Runnable {
    private static KbCrawler kbCrawler;
    private static StudentCrawl studentCrawl;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static ArrayList<Resource> resources = new ArrayList<>();
    private static List<String> urls = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static MysqlOperation mysqlOperation;

    public ThreadCrawl(){
        kbCrawler = KbCrawler.getInstance();
        studentCrawl = new StudentCrawl();
        mysqlOperation = new MysqlOperation();
    }

    @Override
    public void run() {
        while(true){
                try {
                    synchronized (ThreadCrawl.class) {
                        if (urls.size() != 0) {
                           studentCrawl.getStudent(urls.get(0));
                        }
                        if (urls.size() != 0) {
                            urls.remove(0);
                        }
                    }
                } catch (IOException | CloneNotSupportedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 9 ; j++) {
                        urls.add("201821" + i + j);
            }
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ThreadCrawl());
        }
        executorService.shutdown();
       while(true) {
           if (executorService.isTerminated()) {
           /*try {
               Thread.sleep(50000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
               for (Student student : students) {
                   System.out.println(student + " " + student.resources.size());
               }
               long end = System.currentTimeMillis();
               System.out.println(end - begin);
               System.exit(1);
           }
       }
       }
}
