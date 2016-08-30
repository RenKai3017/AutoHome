package com.xu;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args) {
        //创建数据库
        Schema schema = new Schema(1, "com.lanou3g.autohome");
        addData(schema);
        //参数1：schema文件
        //参数2：自动生成代码的路径
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addData(Schema schema) {
        //建表 表名
        Entity collection = schema.addEntity("collection");
        //id字段自增
        collection.addStringProperty("name");
        collection.addIdProperty().autoincrement().primaryKey();
        collection.addStringProperty("title");
        collection.addStringProperty("url");
        collection.addStringProperty("imageUrl");
    }
}
