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

public class MusicDML {
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
        try(Connection connection = dataSource.getConnection(properties.getProperty("user"), System.getenv("MY_SQL_PASS"));
            Statement statement = connection.createStatement();
        ) {
            String query = "Select * from music.artists";
            ResultSet resultSet = statement.executeQuery(query);
            selectAndPrintAllArtists(statement, resultSet);
            String artistName = "kjhgiug";
            boolean found = getRowDataFromTable(statement, artistName);
            if(found) {
                System.out.println("Found the artist");
                getRowDataFromTable(statement, artistName);
            } else {
                System.out.println("Inserting "+artistName);
                int insertedRowCount = insertingNewRow(statement, artistName);
                if(insertedRowCount>0) {
                    System.out.println("Inserted "+artistName);
                } else {
                    System.out.println("Unable to insert artist "+artistName);
                }
                getRowDataFromTable(statement, artistName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int insertingNewRow(Statement statement, String artistName) throws SQLException {
        String insertQuery = "Insert into artists (artist_name) values ('%s')".formatted(artistName);
        boolean r = statement.execute(insertQuery);
        return statement.getUpdateCount();
    }

    private static boolean getRowDataFromTable(Statement statement, String artistName) throws SQLException {
        boolean found = false;
        String searchQuery = String.format("Select * from artists where artist_name='%s'", artistName);
        System.out.println("searchQuery "+ searchQuery);
        // execute always return true on Select query
        ResultSet resultSet = statement.executeQuery(searchQuery);
        if (resultSet!= null && resultSet.next()) {
            selectAndPrintAllArtists(statement, resultSet);
            found = true;
        }
        return found;
    }

    private static void selectAndPrintAllArtists(Statement statement, ResultSet resultSet) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
