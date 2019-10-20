package com.beyond.generator;

import java.lang.reflect.Field;

/**
 * @author beyondlov1
 * @date 2019/10/20
 */
public class CreateSqlGenerator {
    public static String generateSql(String className) throws ClassNotFoundException {
        Class<?> clz = Class.forName(className);
        className = clz.getSimpleName();
        Field[] fields = clz.getDeclaredFields();
        StringBuilder column = new StringBuilder();
        String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
        for (Field f : fields) {
            column.append(" \n `").append(f.getName()).append("`").append(varchar);
        }
        return "\n DROP TABLE IF EXISTS `" + className + "`; " + " \n CREATE TABLE `" + className + "`  (" +
                " \n `id` int(11) NOT NULL AUTO_INCREMENT," + " \n " + column +
                " \n PRIMARY KEY (`id`) USING BTREE," +
                "\n INDEX `id`(`id`) USING BTREE" +
                " \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(generateSql("com.beyond.generator.KlinePoint"));
    }
}
