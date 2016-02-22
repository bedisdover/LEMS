package blservice;

/**
 * Created by 宋益明 on 16-2-5.
 */
public interface LoginBLservice {
    public boolean isLegal(String id, String password);

    public void Login(String id);
}
