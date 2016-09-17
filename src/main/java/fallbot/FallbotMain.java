package fallbot; /**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Created by Fallout301 on 10/22/14.
 **/

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;


public class FallbotMain {
    public static void main(String args[]) throws Exception{

        Integer Port = 6667;
        String Password = "oauth:ol6pqomzkle3o8gmbz7uxgxmgavxrt";
        String Server = "irc.twitch.tv";
        Configuration config = new Configuration.Builder()
                .setAutoNickChange(false)
                .setCapEnabled(true)
                .addCapHandler(new EnableCapHandler("twitch.tv/membership"))
                .addCapHandler(new EnableCapHandler("twitch.tv/tags"))
                .addCapHandler(new EnableCapHandler("twitch.tv/commands"))
                .addServer(Server, Port)
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