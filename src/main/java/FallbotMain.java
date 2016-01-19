import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;

/**
 * Created by fallout301 on 10/22/14.
 */
public class FallbotMain {
    public static void main(String args[]) throws Exception{

        Integer Port = 6667;
        String Password = "";
        String Server = "irc.twitch.tv";
        Configuration config = new Configuration.Builder()
                .setAutoNickChange(false)
                .setCapEnabled(true)
                .addCapHandler(new EnableCapHandler("twitch.tv/membership"))
                //.addCapHandler(new EnableCapHandler("twitch.tv/tags"))
                //.addCapHandler(new EnableCapHandler("twitch.tv/commands"))
                .setServerHostname(Server)
                .setName("fallbot301")
                .setName("fallbot301")
                .setServerPassword(Password)
                .addAutoJoinChannel("#fallbot301")
                .addListener(new Fallbot())
                .buildConfiguration();
        PircBotX bot = new PircBotX(config);
        try{
            bot.startBot();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}