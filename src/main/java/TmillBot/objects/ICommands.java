package TmillBot.objects;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public interface ICommands {

    void handle (List<String> args, GuildMessageReceivedEvent event) throws InterruptedException;
    String getHelp();
    String getInvoke();


}
