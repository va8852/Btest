package com.example.sweater;
import java.sql.*;

/**
 *  Класс-обертка для работы с базой данных
 */
public class DBManager {

    private Connection connection;
    private String tableName;

    DBManager(){
        tableName = "numbers";

    }

    /**
     * Подключение к базе данных
     */
    public void connectToDataBase() {
        try {
            String url = "jdbc:sqlite:database.db";

            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, создает таблицу numbers с 15 колонками INTEGER'ов
     * @throws SQLException
     */
    public void createTable() throws SQLException {

        if (connection == null || connection.isClosed()) {
            return;
        }

        Statement stmt = connection.createStatement();

        String s = "";
        tableName = "numbers";
        for(int i = 0; i < 15; i++)
        {
            s += "x" + String.valueOf(i) + " INTEGER " + "NOT NULL";
            if (i < 14)
                s += ",";
        }

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + " (" + s + "); ");
    }

    /**
     * Метод, вставляет модель таблицы в базу данных
     * @param model - модель таблицы
     * @throws SQLException
     */
    public void insert(TableModel model) throws SQLException
    {
        String query = "INSERT INTO " + tableName + " ";
        String temp = "(";
        for (int i = 0; i < 15; ++i)
        {
            temp += "x" + String.valueOf(i);
            if (i < 14)
                temp += ",";
        }
        temp += ")";
        query += temp + " VALUES ( ";
        for (int i = 0; i < 15; ++i)
        {
            query += String.valueOf(model.array.get(i));
            if (i < 14)
                query += ",";
        }
        query += ");";

        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(query);

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Очищает таблицу
     * @throws SQLException
     */
    public void removeData() throws SQLException
    {
        if (connection == null || connection.isClosed()) {
            return;
        }

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM " + tableName + ";");
    }

    /**
     * Метод возвращает текущие записи из таблицы
     * @return Строка из первой записи всех колонок
     * @throws SQLException
     */
    public String select() throws SQLException
    {

        Statement stmt = connection.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM " + tableName + ";");///РёР·

        String resString = "";

        while (result.next())
        {
            for (int i = 0; i < 15; ++i)
            {
                resString +=  result.getString("x" + String.valueOf(i)) + ", ";
            }
        }

        return resString;
    }


}
