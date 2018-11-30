import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket ss = new Socket("localhost", 5000);
        //客户端启动线程，不断读取来自服务器的数据
        new Thread(new ClientThread(ss)).start();
        //获取Socket对应的输出流
        PrintStream p=new PrintStream(ss.getOutputStream());
        String line=null;
        //不断读取键盘输入
        System.out.println("请输入发送内容：");
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));//系统键盘流
        while((line=b.readLine())!=null){
            p.println(line);
        }

    }
}
