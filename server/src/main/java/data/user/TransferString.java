package data.user;

import po.UserPO;
import utility.ResultMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-1-26.
 *
 * TODO wanquan buheshi
 */
public final class TransferString {
    private TransferString() {}



    public static ResultMessage addBorrow(UserPO user, String list) {
        List<String> l = transfer(list);

        for (String temp : l) {
            user.setBorrowList(temp);
        }

        return ResultMessage.SUCCESS;
    }

    private static List<String> transfer(String list) {
        StringTokenizer tokenizer = new StringTokenizer(list);
        List<String> temp = new ArrayList<String>(tokenizer.countTokens());

        while (tokenizer.hasMoreTokens()) {
            temp.add(tokenizer.nextToken());
        }

        return temp;
    }
}
