package main;

import data.DataBaseFactoryImpl;
import dataservice.DataBaseFactory;
import utility.ConnectConfig;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by 宋益明 on 16-1-20.
 * <p>
 * 启动服务器
 */
public class LaunchServer {

    public static void main(String[] args) {
        try {
            DataBaseFactory dataBase = new DataBaseFactoryImpl();
            Registry registry = LocateRegistry.createRegistry(
                    Integer.parseInt(ConnectConfig.PORT));
            registry.rebind("data", dataBase);

            System.out.println("Ready......");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
