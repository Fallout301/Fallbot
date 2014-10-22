public class FallbotMain
{
    public static void main(String[] args)
            throws Exception
    {
        String Server = "";
        String Password = "";
        Integer Port = 6667;
        Fallbot bot = new Fallbot();

        bot.setVerbose(true);

        if (Password != "") {
            bot.connect(Server, Port, Password);
        }
        if (Password == "") {
            bot.connect(Server, Port);
        }

        bot.joinChannel("#fallbot301");
    }
}