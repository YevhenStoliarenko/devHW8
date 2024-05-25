import com.mysql.cj.exceptions.DataReadException;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInit {
    


    public static void initDB(){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/init_db.sql"));) {
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            System.out.println(stringBuilder.toString());

            String [] lines = stringBuilder.toString().split(";");
            for (String sqlQuery : lines){
                Connection connection = Database.getInstance().getConnection();
                Statement statement = connection.createStatement();
                statement.execute(sqlQuery);
            }

        }catch (IOException ex){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertDB(){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/populate_db.sql"));) {
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            System.out.println(stringBuilder.toString());

            String [] lines = stringBuilder.toString().split(";");
            for (String sqlQuery : lines){
                Connection connection = Database.getInstance().getConnection();
                Statement statement = connection.createStatement();
                statement.execute(sqlQuery);
            }

        }catch (IOException ex){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
