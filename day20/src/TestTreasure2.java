import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class TestTreasure2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {



        ClassLoader cl=new ClassLoader() {
             @Override
             protected Class<?> findClass(String name) throws ClassNotFoundException {
                 FileInputStream in= null;
                 try {
                     in = new FileInputStream("D:\\挖宝文件\\Treasure.class");
                     byte[] bytes = new byte[1024 * 8];
                     int len = in.read(bytes);
                     //将字节数组转为
                     return defineClass(name, bytes, 0, len);
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                     return null;
                 } catch (IOException e) {
                     e.printStackTrace();
                     return null;
                 }
             }
         };
        Class<?> clazz = cl.loadClass("com.westos.Treasure");
        //访问私有构造方法，创建对象实例
        Constructor<?> cons = clazz.getDeclaredConstructor();
        cons.setAccessible(true);
        Object o = cons.newInstance();
        //???
        //找到有注解的方法，并反射调用
        Arrays.stream(clazz.getMethods())
                .filter(m->m.getAnnotations().length>0)
                .findFirst()
                .ifPresent((method)->{
                    try {
                        method.invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }
}