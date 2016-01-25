package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 图书持久化对象
 * 存储图书     基本信息(书名,作者,出版社,ISBN编号,图书类型)
 * 和     借阅信息(借阅者,预约者)
 */
public final class BookPO implements Serializable {

    /**
     * 书名
     */
    private final String name;

    /**
     * 作者---可能有多个
     * TODO 不知道数据库能否存StringTokenizer,待检验
     */
    private final StringTokenizer author;

    /**
     * 出版社
     */
    private final String publisher;

    /**
     * ISBN编号
     */
    private final String ISBN;

    /**
     * 图书类型
     */
    private final BookType type;

    /**
     * 当前借阅用户
     */
    private UserPO borrower;

    /**
     * 当前预约用户
     */
    private UserPO renewer;

    public BookPO(String name, List<String> authors, String publisher, String ISBN, BookType type) {
        this.name = name;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.type = type;

        //初始化图书作者
        {
            StringBuffer buffer = new StringBuffer();
            for (String temp : authors) {
                buffer.append(temp);
                buffer.append(" ");
            }
            buffer.deleteCharAt(buffer.length() - 1);

            this.author = new StringTokenizer(buffer.toString());
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public List<String> getAuthor() {
        List<String> authors = new ArrayList<String>(author.countTokens());

        while (author.hasMoreTokens()) {
            authors.add(author.nextToken());
        }

        return authors;
    }

    public BookType getType() {
        return type;
    }

    public UserPO getBorrower() {
        return borrower;
    }

    public UserPO getRenewer() {
        return renewer;
    }

    public void setBorrower(UserPO borrower) {
        this.borrower = borrower;
    }

    public void setRenewer(UserPO renewer) {
        this.renewer = renewer;
    }
}
