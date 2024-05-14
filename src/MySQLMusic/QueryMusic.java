package MySQLMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class QueryMusic {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(properties.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
        dataSource.setDatabaseName(properties.getProperty("databaseName"));
        String query = "Select * from music.albumview where album_name='Tapestry'";

        try(Connection connection = dataSource.getConnection(properties.getProperty("user"), System.getenv("MY_SQL_PASS"));
            Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            var resultSetMetaData = resultSet.getMetaData();
            for (int i =1; i<=resultSetMetaData.getColumnCount();i++) {
                System.out.printf("%d %s %s%n", i, resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnTypeName(i));
            }
            System.out.println("============");
            for (int i =1; i<=resultSetMetaData.getColumnCount();i++) {
                System.out.printf("%-15s",resultSetMetaData.getColumnName(i).toUpperCase());
            }
            System.out.println();

            while(resultSet.next()) {
                for (int i =1; i<=resultSetMetaData.getColumnCount();i++) {
                    System.out.printf("%-15s", resultSet.getString(i));

                }
                System.out.println();

            }
            System.out.println("Success");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
