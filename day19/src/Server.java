import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    //定义Socket的集合，并包装为线程安全的
    public static List<Socket> list= Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
         //开启服务器
        ServerSocket seversocket = new ServerSocket(5000);
        System.out.println("服务器已启动");
         //等待连接
        while(true){
            Socket ss = seversocket.accept();
            System.out.println("成功连接客户端");
            list.add(ss);
            new Thread(new ServerThread(ss)).start();
        }
    }
}








