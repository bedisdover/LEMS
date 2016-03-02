package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 图书持久化对象
 * <p>
 * 存储图书基本信息(书名,作者,出版社,ISBN编号,图书类型)
 * 自定义条码,书标及借阅信息(借阅者ID,预约者ID)
 * <p>
 * 自定义条码由 "入库日期 + 入库序号" 组成,共13位,如"2016012300001"
 * 根据以往不同图书馆的建库经验，觉得用ISBN有很多弊端,因此给图书贴上自定义条码，条码编号是随意定义的，
 * 只要保证每一本图书上贴的条码编号不重复就可以了。
 * 书标（又称索取号、索书号或者书架位置）一般由分类号和书次号构成，分类号是以图书所属学科门类为依据而得到的号码，
 * 书次号是为使同类书个别化而编制的号码,是分类索引号的组成部分之一,用以确定同一类中各种不同图书的排列次序。
 * <p>
 * <p>
 * 新建<code>BookPO</code>时,使用List<String>表示author符合常理,操作也较为简便
 * 数据库中存取<code>BookPO</code>时,使用String表示author无疑更加方便
 * 所以<code>BookPO</code>对外提供两类构造函数,分别对应以上两种情况
 */
public final class BookPO implements Serializable {

    /**
     * 书名
     */
    private final String name;

    /**
     * 作者---可能有多个
     */
    private StringBuffer author;

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
     * 自定义条形码
     */
    private String barCode;

    /**
     * 书标
     */
    private String label;

    /**
     * 当前借阅用户ID
     */
    private String borrower;

    /**
     * 当前预约用户
     */
    private String renewer;

    public BookPO(String name, List<String> authors, String publisher,
                  String ISBN, BookType type) {
        this(name, authors, publisher, ISBN, type, null);
    }

    public BookPO(String name, List<String> authors, String publisher,
                  String ISBN, BookType type, String label) {
        this(name, publisher, ISBN, type, label);
        //初始化图书作者
        {
            this.author = new StringBuffer();

            for (String temp : authors) {
                this.author.append(temp);
                this.author.append(" ");
            }
            this.author.deleteCharAt(this.author.length() - 1);
        }
    }

    public BookPO(String name, String author, String publisher,
                  String ISBN, BookType type, String label) {
        this(name, publisher, ISBN, type, label);
        this.author = new StringBuffer(author);
    }

    private BookPO(String name, String publisher, String ISBN, BookType type, String label) {
        this.name = name;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.type = type;
        this.label = label;
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

    public String getAuthor() {
        return author.toString();
    }

    public BookType getType() {
        return type;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getLabel() {
        return label;
    }

    public String getBorrower() {
        return borrower;
    }

    public String getRenewer() {
        return renewer;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setRenewer(String renewer) {
        this.renewer = renewer;
    }
}