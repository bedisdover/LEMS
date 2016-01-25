package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 用户持久化对象
 * 包括   基本信息(ID, 姓名, 身份, 密码)
 * 以及   借阅信息(借阅列表, 预约列表)
 */
public class UserPO implements Serializable {

    /**
     * 用户ID
     */
    private final String ID;

    /**
     * 用户姓名
     */
    private final String name;

    /**
     * 用户身份(教师, 研究生, 本科生)
     */
    private final UserRole role;

    /**
     * 密码
     */
    private String password;

    /**
     * 借阅列表 TODO 类型不见得合适
     */
    private List<String> brorrowList;

    /**
     * 预约列表 TODO 类型不见得合适
     */
    private List<String> renewList;

    public UserPO(String name, String ID, UserRole role) {
        this.name = name;
        this.ID = ID;
        this.role = role;

        //初始密码为用户ID
        this.password = ID;

        brorrowList = new ArrayList<String>();
        renewList = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public List<String> getBrorrowList() {
        return brorrowList;
    }

    public List<String> getRenewList() {
        return renewList;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setBrorrowList(BookPO book) {
        this.brorrowList.add(book.getISBN());
    }

    public void setRenewList(BookPO book) {
        this.renewList.add(book.getISBN());
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
