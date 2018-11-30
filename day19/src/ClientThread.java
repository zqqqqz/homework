import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable  {
    private Socket ss=null;
    BufferedReader b=null;
    public ClientThread(Socket ss)throws IOException {
        this.ss=ss;
        b=new BufferedReader(new InputStreamReader(ss.getInputStream()));
    }
    @Override
    public void run() {
        String c=null;
        //不断的读取输入流中的内容，并将内容打印

        try {
            while((c=b.readLine())!=null){
                System.out.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
