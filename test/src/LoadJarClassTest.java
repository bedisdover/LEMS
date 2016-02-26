import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
/**
 * Created by 宋益明 on 16-2-26.
 *
 *
 */
public class LoadJarClassTest {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";

        /*动态加载指定类*/
        File file;//类路径(包文件上一层)
        URL url;
        try {
            file = new File("/home/song/Programming/workspace/java/LEMS/lib/mysql-connector-java-5.1.38-bin.jar");//jar包的路径
            url = file.toURI().toURL();
            ClassLoader loader = new URLClassLoader(new URL[]{url});//创建类加载器
            loader.loadClass(driver);//加载指定类，注意一定要带上类的包名
            System.out.println("done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
