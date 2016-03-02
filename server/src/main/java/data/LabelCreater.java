package data;

import po.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 书标生成类
 * 负责实现生成书标，分为以下步骤：
 * <p>
 * 1.搜索是否已存在相同图书（ISBN相同），若存在，直接返回该书的书标
 * 2.遍历同类别图书，找到最后编号
 * 3.在原有编号后加一，返回
 */
public class LabelCreater {
    private static DatabaseConnect databaseConnect = new DatabaseConnect();

    /**
     * 生成书标
     *
     * @param ISBN
     * @param category
     * @return
     */
    static String createLabel(String ISBN, Category category) throws SQLException {
        String label = exist(ISBN, category);

        if (label != null) {
            return label;
        }

        return createLabel(category);
    }

    private static String createLabel(Category category) throws SQLException {
        //TODO 编号
        return getLast(category);
    }

    private static String exist(String ISBN, Category category) throws SQLException {
        String sql = "select label from book where ISBN = ? and label like ?";

        ResultSet result = databaseConnect.getResultSet(sql, ISBN, category.toString() + "%");

        databaseConnect.closeConnection();
        return result.getString(1);
    }

    private static String getLast(Category category) throws SQLException {
        String result = "";

        for (String temp : getAll(category)) {
            if (temp.compareTo(result) > 0) {
                result = temp;
            }
        }

        return result;
    }

    private static List<String> getAll(Category category) throws SQLException {
        String sql = "select label from book where label like ?";
        List<String> list = new ArrayList<String>();
        ResultSet result = databaseConnect.getResultSet(sql, category.toString() + "%");

        while (result.next()) {
            list.add(result.getString(1));
        }

        databaseConnect.closeConnection();

        return list;
    }
}
