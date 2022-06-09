package de.clemens.util.database;

import java.sql.Connection;
import java.util.HashMap;

public class DatabaseConnectionUtil {

    public static final HashMap<String, Connection> connections = new HashMap<>();
}
