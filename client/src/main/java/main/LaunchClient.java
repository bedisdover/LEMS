package main;

import presentation.MainFrame;

import javax.swing.*;

/**
 * Created by 宋益明 on 16-1-22.
 *
 * 启动服务器
 * 包含程序唯一主方法,负责初始化用户界面
 */
public class LaunchClient {

    public static void main(String[] args) {
        JFrame mainFrame = MainFrame.getMainFrame();
        mainFrame.setVisible(true);
    }
}
