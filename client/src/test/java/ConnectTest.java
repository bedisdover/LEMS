import dataservice.DataBaseFactory;
import utility.ConnectConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-27.
 *
 * 连接测试类
 * 测试客户端与服务器的连接
 *
 * TODO 单元测试
 * 测试成功
 */
public class ConnectTest {

    public static void main(String[] args) {
        try {
            DataBaseFactory dataBaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.URL);
            System.out.println("OK!");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
