/**
 * Created by Fallout301 on 6/5/14.
 */
public class FallbotMain {
//This Class is basically the init junk
    public static void main(String[] args) throws Exception {
        String Server = "";
        String Password = "";
        Integer Port = 6667;
        Fallbot bot = new Fallbot();

        // Enable debugging output.
        bot.setVerbose(true);

        // Connect to the IRC server.
        if(Password!=""){
        bot.connect(Server,Port,Password);
        }
        if(Password == ""){
            bot.connect(Server,Port);
        }
        // Join the #fallbot301 channel.
        bot.joinChannel("#fallbot301");

    }
}