package de.clemens.util.database;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseUtil {

    private Connection connection;

    public DatabaseUtil(String database) {
        if (!DatabaseConnectionUtil.connections.containsKey(database)) {
            try {

                Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "");
                properties.setProperty("useSSL", "false");
                properties.setProperty("autoReconnect", "true");

                connection =
                        DriverManager.getConnection("jdbc:mysql://45.142.114.182:3306/" + database, properties);

                DatabaseConnectionUtil.connections.put(database, connection);
            } catch (SQLException sqlException) {

                sqlException.printStackTrace();
            }
        } else
            connection = DatabaseConnectionUtil.connections.get(database);
    }

    public void executeUpdate(String query) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {

        ResultSet resultSet;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return null;
    }

    //---//

    public boolean exists(String table,
                          String whereColumn1, @NotNull String value1) {
        try {

            ResultSet resultSet = executeQuery("SELECT * FROM `" + table +
                    "` WHERE `" + whereColumn1 + "` = '" + value1.replace("'", "''") + "'");

            assert resultSet != null;
            return (resultSet.next() &&
                    resultSet.getString(whereColumn1) != null);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean exists(String table,
                          String whereColumn1, String whereValue1,
                          String whereColumn2, String whereValue2) {
        try {

            ResultSet resultSet = executeQuery("SELECT * FROM `" + table +
                    "` WHERE `" + whereColumn1 + "` = '" + whereValue1 +
                    "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

            assert resultSet != null;
            return (resultSet.next() &&
                    resultSet.getString(whereColumn1) != null &&
                    resultSet.getString(whereColumn2) != null
            );
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean exists(String table,
                          String whereColumn1, String whereValue1,
                          String whereColumn2, String whereValue2,
                          String whereColumn3, String whereValue3) {
        try {

            ResultSet resultSet = executeQuery("SELECT * FROM `" + table +
                    "` WHERE `" + whereColumn1 + "` = '" + whereValue1 +
                    "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                    "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

            assert resultSet != null;
            return (resultSet.next() &&
                    resultSet.getString(whereColumn1) != null &&
                    resultSet.getString(whereColumn2) != null &&
                    resultSet.getString(whereColumn3) != null
            );
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;
        }
    }

    public void create(String table,
                       String column1, @NotNull String value1) {

        executeUpdate("INSERT INTO " + table +
                " (" + column1 + ") " +
                "VALUES ('" + value1.replace("'", "''") + "');");
    }

    public void create(String table,
                       String column1, @NotNull String value1,
                       String column2, @NotNull String value2) {

        executeUpdate("INSERT INTO " + table +
                " (" + column1 + ", " + column2 + ") " +
                "VALUES ('" + value1.replace("'", "''") + "', + '" + value2.replace("'", "''") + "');");
    }

    public void create(String table,
                       String column1, @NotNull String value1,
                       String column2, @NotNull String value2,
                       String column3, @NotNull String value3) {

        executeUpdate("INSERT INTO " + table +
                " (" + column1 + ", " + column2 + ", " + column3 + ") " +
                "VALUES ('" + value1.replace("'", "''") + "', + '" + value2.
                replace("'", "''") + "', + '" + value3.replace("'", "''") + "');");
    }

    public void delete(String table,
                       String whereColumn1, String whereValue1) {

        executeUpdate("DELETE FROM " + table +
                " WHERE " + whereColumn1 + "='" + whereValue1 + "';");
    }

    public void delete(String table,
                       String whereColumn1, String whereValue1,
                       String whereColumn2, String whereValue2) {

        executeUpdate("DELETE FROM " + table +
                " WHERE " + whereColumn1 + "='" + whereValue1 +
                "' AND " + whereColumn2 + "='" + whereValue2 + "';");
    }

    public void delete(String table,
                       String whereColumn1, String whereValue1,
                       String whereColumn2, String whereValue2,
                       String whereColumn3, String whereValue3) {

        executeUpdate("DELETE FROM " + table +
                " WHERE " + whereColumn1 + "='" + whereValue1 +
                "' AND " + whereColumn2 + "='" + whereValue2 +
                "' AND " + whereColumn3 + "='" + whereValue3 + "';");
    }

    public void setString(String table, String column, @NotNull String value,
                          String whereColumn1, String whereValue1) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value.replace("'", "''") +
                "' WHERE " + whereColumn1 + " ='" + whereValue1 + "';");
    }

    public void setString(String table, String column, @NotNull String value,
                          String whereColumn1, String whereValue1,
                          String whereColumn2, String whereValue2) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value.replace("'", "''") +
                "' WHERE " + whereColumn1 + " ='" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 + "';");
    }

    public void setString(String table, String column, @NotNull String value,
                          String whereColumn1, String whereValue1,
                          String whereColumn2, String whereValue2,
                          String whereColumn3, String whereValue3) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value.replace("'", "''") +
                "' WHERE " + whereColumn1 + " ='" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 +
                "' AND " + whereColumn3 + " ='" + whereValue3 + "';");
    }

    public void setInteger(String table, String column, Integer value,
                           String whereColumn1, String whereValue1) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " = '" + whereValue1 + "';");
    }

    public void setInteger(String table, String column, Integer value,
                           String whereColumn1, String whereValue1,
                           String whereColumn2, String whereValue2) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " ='" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 + "';");
    }

    public void setInteger(String table, String column, Integer value,
                           String whereColumn1, String whereValue1,
                           String whereColumn2, String whereValue2,
                           String whereColumn3, String whereValue3) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " ='" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 +
                "' AND " + whereColumn3 + " ='" + whereValue3 + "';");
    }

    public void setLong(String table, String column, Long value,
                        String whereColumn1, String whereValue1) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " = '" + whereValue1 + "';");
    }

    public void setLong(String table, String column, Long value,
                        String whereColumn1, String whereValue1,
                        String whereColumn2, String whereValue2) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 + "';");
    }

    public void setLong(String table, String column, Long value,
                        String whereColumn1, String whereValue1,
                        String whereColumn2, String whereValue2,
                        String whereColumn3, String whereValue3) {

        executeUpdate("UPDATE " + table + " SET " + column + " = '" + value +
                "' WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND " + whereColumn2 + " ='" + whereValue2 +
                "' AND " + whereColumn3 + " ='" + whereValue3 + "';");
    }

    public String getString(String table, String column,
                            String whereColumn1, String whereValue1) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getString(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return "null";
    }

    public String getString(String table, String column,
                            String whereColumn1, String whereValue1,
                            String whereColumn2, String whereValue2) {

        ResultSet resultSet = executeQuery("SELECT * FROM `" + table +
                "` WHERE `" + whereColumn1 + "` = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getString(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return "null";
    }

    public String getString(String table, String column,
                            String whereColumn1, String whereValue1,
                            String whereColumn2, String whereValue2,
                            String whereColumn3, String whereValue3) {

        ResultSet resultSet = executeQuery("SELECT * FROM `" + table +
                "` WHERE `" + whereColumn1 + "` = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getString(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return "null";
    }

    public ArrayList<String> getStrings(String table, String column) {

        ArrayList<String> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table);

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getString(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<String> getStrings(String table, String column,
                                        String whereColumn1, String whereValue1) {

        ArrayList<String> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getString(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<String> getStrings(String table, String column,
                                        String whereColumn1, String whereValue1,
                                        String whereColumn2, String whereValue2) {

        ArrayList<String> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getString(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<String> getStrings(String table, String column,
                                        String whereColumn1, String whereValue1,
                                        String whereColumn2, String whereValue2,
                                        String whereColumn3, String whereValue3) {

        ArrayList<String> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getString(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public Integer getInteger(String table, String column,
                              String whereColumn1, String whereValue1) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getInt(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0;
    }

    public Integer getInteger(String table, String column,
                              String whereColumn1, String whereValue1,
                              String whereColumn2, String whereValue2) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getInt(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0;
    }

    public Integer getInteger(String table, String column,
                              String whereColumn1, String whereValue1,
                              String whereColumn2, String whereValue2,
                              String whereColumn3, String whereValue3) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getInt(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0;
    }

    public ArrayList<Integer> getIntegers(String table, String column) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table);

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getInt(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Integer> getIntegers(String table, String column,
                                          String whereColumn1, String whereValue1) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getInt(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Integer> getIntegers(String table, String column,
                                          String whereColumn1, String whereValue1,
                                          String whereColumn2, String whereValue2) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getInt(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Integer> getIntegers(String table, String column,
                                          String whereColumn1, String whereValue1,
                                          String whereColumn2, String whereValue2,
                                          String whereColumn3, String whereValue3) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getInt(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public Long getLong(String table, String column,
                        String whereColumn1, String whereValue1) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getLong(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0L;
    }

    public Long getLong(String table, String column,
                        String whereColumn1, String whereValue1,
                        String whereColumn2, String whereValue2) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getLong(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0L;
    }

    public Long getLong(String table, String column,
                        String whereColumn1, String whereValue1,
                        String whereColumn2, String whereValue2,
                        String whereColumn3, String whereValue3) {

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {

            if (resultSet != null && resultSet.next())
                return resultSet.getLong(column);
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return 0L;
    }

    public ArrayList<Long> getLongs(String table, String column) {

        ArrayList<Long> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table);

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getLong(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Long> getLongs(String table, String column,
                                    String whereColumn1, String whereValue1) {

        ArrayList<Long> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getLong(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Long> getLongs(String table, String column,
                                    String whereColumn1, String whereValue1,
                                    String whereColumn2, String whereValue2) {

        ArrayList<Long> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getLong(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Long> getLongs(String table, String column,
                                    String whereColumn1, String whereValue1,
                                    String whereColumn2, String whereValue2,
                                    String whereColumn3, String whereValue3) {

        ArrayList<Long> arrayList = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT * FROM " + table +
                " WHERE " + whereColumn1 + " = '" + whereValue1 +
                "' AND `" + whereColumn2 + "` = '" + whereValue2 +
                "' AND `" + whereColumn3 + "` = '" + whereValue3 + "'");

        try {
            while (true) {

                assert resultSet != null;
                if (!resultSet.next()) break;
                arrayList.add(resultSet.getLong(column));
            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<String> getTop(String table, String orderColumn, String where, Integer topType) {

        ArrayList<String> top = new ArrayList<>();

        ResultSet resultSet = executeQuery("SELECT " + where + " FROM " + table +
                " ORDER BY " + orderColumn + " DESC LIMIT " + topType + "");

        while (true) {
            try {

                assert resultSet != null;
                if (!resultSet.next()) break;
                top.add(resultSet.getString(1));
            } catch (SQLException sqlException) {

                sqlException.printStackTrace();
            }
        }

        return top;
    }

    public Integer getRanking(String table, String column,
                              String whereColumn1, String whereValue1) {

        int counter = 0;
        try {

            ResultSet resultSet = executeQuery("SELECT " + whereColumn1 + " FROM " + table +
                    " ORDER BY " + column + " DESC");

            do {

                assert resultSet != null;
                if (!resultSet.next())
                    break;
                counter++;
            } while (!resultSet.getString(whereColumn1).equalsIgnoreCase(whereValue1));
        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return counter;
    }
}