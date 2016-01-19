/**
 * Created by fallout301 on 10/21/14.
 */

import java.io.PrintStream;
 import java.nio.channels.Channel;
 import java.sql.Connection;
 import java.sql.DatabaseMetaData;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;

 public class SQLInteraction
 {
 public  Connection con = null;
 public  Statement st = null;
 public  ResultSet rs = null;
 public  PreparedStatement ps = null;
 public  String user = "Fallbot";
 public  String password = "fallbot301";
 public  String url = "jdbc:mariadb://localhost:3306/fallbot301?user=" + user + "&password=" + password;
 public  String TMessage = null;
 public void initConnection() throws SQLException{
 try{
 Class.forName("org.mariadb.jdbc.Driver");
 con = DriverManager.getConnection(url);
 st = con.createStatement();
 }catch (ClassNotFoundException e){
 e.printStackTrace();
 }
 }
 public String createTable(String Channel) {
 try {
 initConnection();
 String Chans = Channel.replace("#", "").toUpperCase();
 DatabaseMetaData dbm = con.getMetaData();
 ResultSet tables = dbm.getTables(null, null, Chans, null);
 if (!tables.next()) {
 st.executeUpdate("CREATE TABLE IF NOT EXISTS " + Chans + "(\n" + "ID TINYINT NOT NULL AUTO_INCREMENT,\n" + "Command TEXT NULL,\n " + "Response TEXT NULL,\n" + "UL TINYINT NULL DEFAULT 0,\n" + "Ran INT UNSIGNED NULL DEFAULT 0,\n" + "PRIMARY KEY (ID))");
 st.close();
 con.close();
 TMessage = "Success!";
 System.out.println("Successfully created.");
 return TMessage;
 }
 con.close();
 System.out.println(tables);
 System.out.println("Table already exists!");
 TMessage = "Table already exists! Go bug Fallout if there's issues.";
 return TMessage;
 }
 catch (Exception e) {
 e.printStackTrace();
 System.out.println("Ohhhh Fuck something went wrong!");
 TMessage = "Ohhhhh Crap, Something went wrong....";
 }return TMessage;
 }

 public void addCom(String Channel, String Command, String Response, String UserLevel) {
 try {
 initConnection();
 int UL = Integer.parseInt(UserLevel);
 st.executeUpdate("INSERT INTO " + Channel + " VALUES (default, \""+ Command + "\",\"" + Response + "\",\"" + UL +"\",'0');");
 st.close();
 con.close();
 }catch (SQLException e) {
 e.printStackTrace();
 }
 }

 public void removeCom(String Channel, String Command)
 {
 }

 public void editCom()
 {

 }
 public String returnCom(String MCom, String Channel){
 try{
 int id = 0;
 String com = "";
 String resp = "";
 int ul = 0;
 int ran = 0;
 initConnection();
 rs = st.executeQuery("SELECT * FROM " + Channel + " WHERE Command = '"+MCom + "';");
 while (rs.next()){
 id = rs.getInt("ID");
 com = rs.getString("Command");
 resp = rs.getString("Response");
 ul = rs.getInt("UL");
 ran = rs.getInt("Ran");
 TMessage = resp;
 }
 st.executeUpdate("UPDATE " + Channel + " SET Ran = Ran + 1 WHERE Command = '" +MCom + "';");
 st.close();
 con.close();

 }catch (SQLException e) {
 e.printStackTrace();
 }
 return TMessage;
 }
 }