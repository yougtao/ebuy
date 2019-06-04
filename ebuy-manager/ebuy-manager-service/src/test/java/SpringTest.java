import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringTest
{
    public static void main(String[] args) {
        // 手动创建spring容器
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            System.out.println("容器创建完成!");
            System.in.read();
            System.out.println("程序结束!");
        } catch (IOException e) {
            System.out.println("有错误");
            e.printStackTrace();
        }
    }
}
