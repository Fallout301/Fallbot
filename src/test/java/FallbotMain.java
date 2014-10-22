import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * Created by fallout301 on 10/22/14.
 */
public class FallbotMain {
    public static void main(String args[]) throws Exception{
        Integer Port = 6667;
        String Password = "";
        Configuration<PircBotX> config = new Configuration.Builder()
                .setName("Fallbot301")
                .setLogin("fallbot301")
                .addListener(new Fallbot())
                .setServer("", Port, Password)
                .addAutoJoinChannel("#fallbot301")
                .buildConfiguration();
        PircBotX bot = new PircBotX(config);
        bot.startBot();
    }
}
