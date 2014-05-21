/**
 * Created by Fallout301 on 3/14/14.
 */

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.cap.TLSCapHandler;
import org.pircbotx.dcc.ReceiveChat;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.IncomingChatRequestEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.managers.ListenerManager;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.pircbotx.hooks.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import com.google.common.*;
import org.pircbotx.Utils;
import org.pircbotx.output.OutputChannel;
import org.pircbotx.output.OutputIRC;

import org.mariadb.jdbc.*;

public class Fallbot extends ListenerAdapter {
    @Override
    public void onGenericMessage(final GenericMessageEvent event) throws Exception {
        //Hello world
        //This way to handle commands is useful for listeners that listen for multiple commands
        if (event.getMessage().startsWith("?hello")){
            event.respond("Hello World!");
        }
    }


    public static void main(String args[]){

    Configuration config = new Configuration.Builder()
         .setName("Fallbot301")
         .setLogin("fallbot301")
         .addListener(new Fallbot())
         //.setServer(<redacted login information, will cause compile problems until fixed>)
         .addAutoJoinChannel("#fallbot301")
         .buildConfiguration();
        PircBotX bot = new PircBotX(config);
        try{
        bot.startBot();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
