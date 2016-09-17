/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Created by Fallout301 on 10/21/14.
 **/
package fallbot;
import java.sql.*;

 public class SQLInteraction
 {
 private  Connection con = null;
 private  Statement st = null;
 private  ResultSet rs = null;
 private  PreparedStatement ps = null;
 private  String user = "fallbot.Fallbot";
 private  String password = "fallbot.Fallbot";
 private  String url = "jdbc:mariadb://localhost:3306/fallbot301?user=" + user + "&password=" + password;
 private  String TMessage = null;

        //This method initializes the connection to the database.
     public void initConnection() throws SQLException{
     try {
         con = DriverManager.getConnection(url);
         st = con.createStatement();
         TMessage = null;
        }
     catch (SQLException e){
            e.printStackTrace();
        }
 }

    //This method checks if there is a table for the channel in the database. If not, then create one. If there's an issue, throw an error.
 public String createTable(String Channel) {
    try {
        initConnection();
        String Chans = Channel.replace("#", "");
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
        System.out.println("Ohhhh something went wrong!");
        TMessage = "Ohhhhh Crap, Something went wrong....";
    }return TMessage;
 }

 //Adds entries to the database essentially. VERY exciting I know.
 public void addCom(String Channel, String MCom, String Response, String UserLevel) {
    try {
        initConnection();
        int UL = Integer.parseInt(UserLevel);
        st.executeUpdate("INSERT INTO " + Channel + " VALUES (default, \""+ MCom + "\",\"" + Response + "\",\"" + UL +"\",'0');");
        st.close();
        con.close();
    }catch (SQLException e) {
        e.printStackTrace();
    }
 }

 public void removeCom(String Channel, String MCom) {
     //This method will eventually remove entries from the database.
 }

 public void editCom(String Channel, String MCom) {
     try{
         int id = 0;
         String com = "";
         String resp = "";
         int ul = 0;
         int ran = 0;
         initConnection();
         rs = st.executeQuery("SELECT * FROM " + Channel + " WHERE Command = '"+ MCom + "';");
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
 }

 //Returns the data from an entry in the database if it exists.
 public String returnCom(String Channel, String MCom){
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