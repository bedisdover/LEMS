package main;

import data.Config;
import data.DataBaseFactoryImpl;
import dataservice.DataBaseFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by 宋益明 on 16-1-20.
 *
 * 启动服务器
 */
public class LaunchServer {

    private static final int PORT = 1099;

    public static void main(String[] args) {
        try {
            DataBaseFactory dataBase = new DataBaseFactoryImpl();
            LocateRegistry.createRegistry(PORT);
            Naming.rebind("rmi://" + Config.IP + ":" + Config.PORT + "/data", dataBase);

            System.out.println("Ready......");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
