package data.user;

import data.user.UserData;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import po.UserPO;
import po.UserRole;
import utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-26.
 */
public class UserDataTest extends TestCase {

    private UserData data;
    private UserPO user;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        try {
            data = new UserData();
            user = new UserPO("test", "1234", UserRole.UNDERGRADUATE);

            user.setPassword("123456");
            user.setBorrowList("10001 10002 10003 10004");
            user.setRenewList("10005 10006");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println(1234);
        assertEquals(data.insert(user), ResultMessage.SUCCESS);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}