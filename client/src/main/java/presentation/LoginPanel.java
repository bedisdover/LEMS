package presentation;

import bl.Login;
import blservice.LoginBLservice;
import presentation.config.LoginConfig;
import presentation.reader.ReaderMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-1-22.
 * <p>
 * 登录界面
 */
public final class LoginPanel extends JPanel {
    public static LoginPanel loginPanel;

    private JLabel labelID, labelPass;
    private JTextField textID;
    private JPasswordField textPass;
    private JCheckBox remember, autoLogin;
    private JButton btnOK, btnCancel;

    private Font font;

    private LoginConfig config;

    static {
        loginPanel = new LoginPanel();
    }

    private LoginPanel() {
        config = new LoginConfig();

        this.init();
        this.createUIComponents();
        this.initUIComponents();
        this.addListener();
    }

    public static JPanel getLoginPanel() {
        return loginPanel;
    }

    public String getID() {
        return textID.getText();
    }

    public String getPassword() {
        return new String(textPass.getPassword());
    }

    public boolean getRemember() {
        return remember.isSelected();
    }

    public boolean getAutoLogin() {
        return autoLogin.isSelected();
    }

    /**
     * 初始化
     */
    private void init() {
        this.setLayout(null);
    }

    /**
     * 创建组件
     */
    private void createUIComponents() {
        font = new Font("Courier 10 Pitch", Font.PLAIN, 20);

        labelID = new JLabel("账户");
        labelPass = new JLabel("密码");

        textID = new JTextField();
        textPass = new JPasswordField();

        remember = new JCheckBox("  记住密码");
        autoLogin = new JCheckBox("  自动登录");

        btnOK = new JButton("登录");
        btnCancel = new JButton("取消");

        this.add(labelID);
        this.add(labelPass);
        this.add(textID);
        this.add(textPass);
        this.add(remember);
        this.add(autoLogin);
        this.add(btnOK);
        this.add(btnCancel);
    }

    /**
     * 初始化组件
     */
    private void initUIComponents() {
        labelID.setBounds(214, 161, 61, 17);
        labelPass.setBounds(214, 199, 61, 17);

        textID.setBounds(293, 159, 142, 21);
        textPass.setBounds(293, 197, 142, 21);

        remember.setBounds(214, 237, 119, 25);
        autoLogin.setBounds(357, 237, 119, 25);

        btnOK.setBounds(214, 290, 75, 25);
        btnCancel.setBounds(357, 290, 75, 25);

        try {
            textID.setText(config.loadID());
            textPass.setText(config.loadPass());
            remember.setSelected(config.loadRemember());
            autoLogin.setSelected(config.loadAutoLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListener() {
        textID.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                idChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                idChanged();
            }

            public void changedUpdate(DocumentEvent e) {}
        });

        btnOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (remember.isSelected()) {
                    try {
                        config.store();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                LoginBLservice service = new Login();

                if (service.isLegal(textID.getText(), textPass.getPassword().toString())) {
                    service.Login(textID.getText());
                } else {
                    JOptionPane.showMessageDialog(loginPanel, null, "密码错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * 用户名改变后,清空密码,记住密码选项设为空
     */
    private void idChanged() {
        textPass.setText("");
        remember.setSelected(false);
    }
}
