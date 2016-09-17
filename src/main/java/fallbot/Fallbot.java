package fallbot; /**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Created by Fallout301 on 10/21/14.
 */

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;


public class Fallbot extends ListenerAdapter{
    public String Owner = "Fallout301";
    TTVJSONHandler JSON = new TTVJSONHandler();
    SQLInteraction SQLConnection = new SQLInteraction();
    public Channel Chan;

    /** I guess this should be obvious, but basically this fires when bot joins a channel
     * Sets the Chan variable initially in case of.. reasons, IDK? WHAT DO I LOOK LIKE TO YOU?!
     * I'M JUST TEXT!!!
     **/
    @Override
    public void onJoin(JoinEvent event) {
        Chan = event.getChannel();
        System.out.println(Chan);
        System.out.println(Chan.getMode());

    }
    @Override
    public void onMessage(MessageEvent event) throws Exception {
        Chan = event.getChannel();
        if (event.getMessage().equalsIgnoreCase("!test")) {

            event.getChannel().send().message("Running C++ saying hello world! Just kidding, running Java baby!! Keepo");

        }
        else if (event.getMessage().equalsIgnoreCase("!join")) {

            event.getBot().sendIRC().joinChannel("#" + event.getUser().getNick().toLowerCase());

        }
        else if (event.getMessage().equalsIgnoreCase("!createtable")) {

            SQLConnection.createTable(Chan.getName());


        } else if (event.getMessage().toLowerCase().startsWith("!join #")) {

            event.respond("On My Way!");
            String[] parts = event.getMessage().split(" ");
            event.getBot().sendIRC().joinChannel(parts[1].toLowerCase());

        } else if (event.getMessage().toLowerCase().startsWith("!rank")) {

            String Rank = "";
            //if(event.getUser().toString().toLowerCase() ==)
            if(event.getUser().toString().toLowerCase().equals(Owner.toLowerCase())){
                event.respond("Your rank is: " + Rank + ". You are also my creator.");
            }

        } /**else if (event.getMessage().toLowerCase().startsWith("!uptime")) {
                event.getChannel().send().message(JSON.getUptime(Chan.getName()));

        }**/ else if (event.getMessage().toLowerCase().startsWith("!dismiss")) {
            if(event.getUser().toString().toLowerCase().equals(event.getChannel().getOwners().toString()));

        } else if (event.getMessage().toLowerCase().startsWith("!")) {
            String reply = SQLConnection.returnCom(Chan.getName().toLowerCase().replace("#",""), event.getMessage().toLowerCase() );
                if(reply!=null) {
                    event.getChannel().send().message(reply);
                }

        }

    }
   /** @Override
    //Attempt to get the badges of the user from each userstate event
    public void onUnknown(UnknownEvent event) throws Exception{
       String badges =
        event.getBot().sendIRC().message(Chan.getName(),badges);
        System.out.println("Dingy: " + badges);
        if(badges.startsWith("@badges")){}
    }**/
}