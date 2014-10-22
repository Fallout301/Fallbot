/**
 * Created by Fallout301 on 7/21/14.
 */
import java.sql.*;
public class SQLInteraction {
    public static Connection con = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps = null;
    public static String user = "Fallbot";
    public static String password="fallbot301";
    public static String url = "jdbc:mariadb://localhost:3306/fallbot301?user="+user+"&password="+password;
    public static String TMessage = null;

    public static String createTable(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String Chan = Fallbot.Channel.replace("#","").toUpperCase();
            con = DriverManager.getConnection(url);
            st = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null,null,Chan,null);
            if(!tables.next()){
                st.executeUpdate("CREATE TABLE " + Chan + "(\n" +
                        "ID TINYINT NOT NULL AUTO_INCREMENT,\n" +
                        "Command TEXT NULL,\n " +
                        "Response TEXT NULL,\n" +
                        "UL TINYINT NULL DEFAULT 0,\n" +
                        "Ran INT UNSIGNED NULL DEFAULT 0,\n" +
                        "PRIMARY KEY (ID))"
                );
                con.close();
                TMessage = "Success!";
                System.out.println("Successfully created.");
                return(TMessage);
            }
            else{con.close();
            System.out.println(tables);
            System.out.println("Table already exists hoss!");
                TMessage = "Table already exists hoss! Go bug Fallout if there's issues.";
                return(TMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ohhhh Fuck something went wrong!");
            TMessage = "Ohhhhh Fuck, Something went wrong....";
            return(TMessage);
        }
    }
    public static void addCom(String Channel, String Command, String Response, Integer UserLevel){
        try {
            st.executeUpdate("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeCom(String Channel, String Command){

    }
    public static void editCom(){

    }
}