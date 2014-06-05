import org.jibble.pircbot.PircBot;
import org.mariadb.jdbc.*;

/**
 * Created by Fallout301 on 3/14/14.
 */
public class Fallbot extends PircBot {

    String Name = "Fallbot301";

    public Fallbot(){
        this.setName(Name);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {

    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        //Eh, why not.
        if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
        if (message.equalsIgnoreCase("!dismiss")& channel.contains(sender)){
            sendMessage(channel,"Understood, peace out!");
            try{
            wait(1,1);
            }catch(Exception e){
                partChannel(channel);
            }
        }
        if(message.equalsIgnoreCase("!join")& channel.contains(getNick().toLowerCase())){
            sendMessage(channel, "I shall arrive shortly!");
            try{
                wait(1,1);
                }catch(Exception e){
                    joinChannel("#"+sender.toLowerCase());
                    }

                }

            }

        }