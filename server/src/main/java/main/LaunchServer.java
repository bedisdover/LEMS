package main;

import data.DataBaseFactoryImpl;
import dataservice.DataBaseFactory;
import utility.ConnectConfig;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by 宋益明 on 16-1-20.
 *
 * 启动服务器
 */
public class LaunchServer {

    public static void main(String[] args) {
        try {
            DataBaseFactory dataBase = new DataBaseFactoryImpl();
            Registry registry = LocateRegistry.createRegistry(
                                Integer.parseInt(ConnectConfig.PORT));
            registry.bind("data", dataBase);

            System.out.println("Ready......");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
