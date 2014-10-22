import java.util.Date;
import org.jibble.pircbot.PircBot;

public class Fallbot extends PircBot
{
    String Owner = "fallout301";
    String Name = "Fallbot301";
    public static String Channel = null;
    public String Message = null;
    public String Sender = null;

    Integer UserLevel = null;
    String Comm = null;
    String Resp = null;

    public Fallbot()
    {
        setName(this.Name);
    }

    protected void onJoin(String channel, String sender, String login, String hostname)
    {
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        Channel = channel;
        this.Message = message;
        this.Sender = sender;
        if (message.equalsIgnoreCase("!table")) {
            SQLInteraction.createTable();
            sendMessage(Channel, SQLInteraction.TMessage);
        }
        if (message.equalsIgnoreCase("!addcom")) {
            String Chan = Channel.replace("#", "").toUpperCase();

            SQLInteraction.addCom(Chan, this.Comm, this.Resp, this.UserLevel);
        }

        if (message.equalsIgnoreCase("!time")) {
            String time = new Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
        if (((message.equalsIgnoreCase("!dismiss") & channel.contains(sender))) || ((message.equalsIgnoreCase("!dismiss") & sender.equalsIgnoreCase(this.Owner)))) {
            sendMessage(channel, "Understood, peace out!");
            partChannel(channel);
        }
        if ((message.equalsIgnoreCase("!join") & channel.contains(getNick().toLowerCase()))) {
            sendMessage(channel, "I shall arrive shortly!");
            joinChannel("#" + sender.toLowerCase());
            sendMessage("#" + sender.toLowerCase(), "Hi I'm Fallbot! Add me as a mod so I can do the modded things (not implemented yet)");
        }

        if ((message.contains("!join #") & channel.contains(getNick().toLowerCase()))) {
            sendMessage(channel, "Affirmative");
            String[] parts = message.split(" ");
            joinChannel(parts[1]);
            sendMessage(parts[1], "Hi I'm Fallbot! add me as a mod so I can do the  modded things (not implemented yet)");
        }
    }
}