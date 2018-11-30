import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    //定义当前线程所处理的socket
    Socket ss=null;
    //该线程所处理的socket对应输入流
     BufferedReader b=null;

    public ServerThread(Socket ss)throws IOException {
        this.ss=ss;
        //初始化其对应输入流
        b=new BufferedReader(new InputStreamReader(ss.getInputStream()));

    }
    @Override
    public void run() {
        //采取循环方式不断读取
        while(true){
            String cc=null;
            while((cc=readFromClient())!=null){
                //遍历客户端集合，给每个发送一次
                for(Socket s:Server.list){//遍历Server类中的list集合
                    PrintStream p= null;
                    try {
                        p = new PrintStream(s.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.println("你收到一条群发消息：\t"+cc);

                }
            }

        }
    }
    //读取客户端的方法
    private String readFromClient()  {
        try {
            return b.readLine();//返回读的一行
        }
        //如果捕获到异常，则表明对应客户端已关闭
        catch (IOException e) {
            //删除该客户端从集合中
            Server.list.remove(ss);
        }
        return null;
    }
}
