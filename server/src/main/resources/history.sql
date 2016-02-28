--mysql数据库基础操作(bedisdover)

--mysql数据库中所有操作的关键字都不区分大小写,
--数据库名称和表名称区分大小写

--数据库操作(结尾有无分号均可)

    --登录(默认密码为空)
    mysql -u root -p

    --显示数据库列表
    show databases;

    --创建数据库
    create database db_test;

    --设置编码格式
    create database db_name character set utf8;

    --使用数据库
    use db_test;

    --切换数据库
    use test;

    --删除数据库
    drop database db_test;

    --退出数据库
    quit;


--表操作(必须以分号结束)

    --显示所有表
    show tables;
    --显示其它数据库中的表
    show tables from test;

    --创建表
    create table person(
        id          int(3)          auto_increment primary key not null,
        name        varchar(50)     not null,
        birthday    date            not null
    );
    --上例中:
    --id,name,birthday为表person的列名称,称为Field(字段)
    --auto_increment 表明id自增长
    --primary key 表明id不能重复


    --添加Field
    alter table person add salary double character set utf8 collate utf8_unicode_ci not null;

    --删除Field
    alter table person drop salary;

    --修改Field
    alter table person change id newID int(1) not null;

    --查看数据表结构
    describe person;
    --或简写为
    desc person;
    --或
    show full columns from person;

    --重命名表
    alter table person rename to temp;
    --或
    rename table temp to person;

    --插入信息
    insert into person(id, name, birthday) values (1, '张三', '1996-03-21');
    --或简写为
    insert into person values (1, '张三', '1996-04-23');

    --删除信息
    delete from person where id = 1;
    --删除全部
    delete from person;

    --查询信息
    select * from person where id = 1;
    --查询全部
    select * from person;
    --模糊查找
    select * from person where id like %1%;

    --删除表
    drop table person;


--mysql数值类型

    --数值类型

        --整数类型
        tinyint(1字节), smallint(2字节), mediumint(3字节), int(4字节), bigint(8字节)
        --规定整型位数
        int(6)
        --规定整型位数(用0补全不足位)
        int(6) zerofill

        --浮点类型
        float(4字节), double(8字节), decimal(十进制)
        --设置精度(数字最多5位,小数点后2位,小数超出部分四舍五入)
        float(5, 2)

        --布尔类型
        bool, boolean
        --二者没有区别


    --字符类型(不区分大小写)
    --tiny,small,big等修饰词均适用

        --较短字符串(不区分大小写)
            char, varchar
            --区分大小写(后加 binary 关键字)
            char binary, varchar binary
        --大段文字
            text(非二进制), blob(二进制)

        --复杂字符类型
            enum(枚举类型字符串)
                --如 enum('M', 'N')
                --插入非法字符时,存储为空
            set(enum加强版,可插入一个或多个字符,字符间用逗号隔开,不带空格)


    --日期类型

        date(yyyy-MM-dd)
            --输入20151221, '20151221', '2015-12-21'均可
            --其它相同

        time(hh:mm:ss)

        year(yyyy 1901-2155)

        datetime
            --相当于date+time

        timestamp(时间戳)
            --与datetime类似
            --默认当前时间