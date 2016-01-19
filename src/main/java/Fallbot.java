import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * Created by fallout301 on 10/21/14.
 */
public class Fallbot extends ListenerAdapter{
    SQLInteraction SQL = new SQLInteraction();
    public Channel Chan;
    @Override
    public void onJoin(JoinEvent event) {
        Chan = event.getChannel();
        System.out.println(Chan);

           // event.getBot().sendIRC().message(Chan.getName(),"HEY YOU!! YES YOU!!! MOD ME!!!!");
        //}
        //else{
        //    event.getBot().sendIRC().message(Chan.getName(),"REPORTING FOR DUTY, SIR!!");
        //}
    }
    @Override
    public void onGenericMessage(final GenericMessageEvent event) throws Exception{
    if(event.getMessage().equalsIgnoreCase("!test")){
        System.out.println("detected");
        event.getBot().sendIRC().message(Chan.getName(),"Wow, that actually worked.");

    }
    if(event.getMessage().equalsIgnoreCase("!join")){
        event.getBot().sendIRC().joinChannel("#"+event.getUser().getNick());
    }
        if(event.getMessage().equalsIgnoreCase("!createtable")){
            SQL.createTable(Chan.getName());
            System.out.println("THISISATEST");
        }
    else if ((event.getMessage().toLowerCase().contains("!join #"))){
            event.getBot().sendIRC().message(Chan.getName(),"On My Way!");
            String[] parts = event.getMessage().split(" ");
            event.getBot().sendIRC().joinChannel(parts[1].toLowerCase());
        }

    }
}