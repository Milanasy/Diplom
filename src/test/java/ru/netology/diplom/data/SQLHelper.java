package ru.netology.diplom.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    @SneakyThrows
    public static Connection getConn() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        return DriverManager.getConnection (dbUrl, login, password);
    }

    @SneakyThrows
    public static DataGenerator.StatusPay getStatusPay() {
        String statusSQL = "SELECT status FROM payment_entity order by created DESC LIMIT 1";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataGenerator.StatusPay.class));
    }

    @SneakyThrows
    public static DataGenerator.StatusCredit getStatusCredit() {
        String statusSQL = "SELECT status FROM credit_request_entity order by created DESC LIMIT 1";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataGenerator.StatusCredit.class));
    }

}
