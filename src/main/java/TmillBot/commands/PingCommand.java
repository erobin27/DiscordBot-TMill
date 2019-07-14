package TmillBot.commands;

import TmillBot.Constants;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import TmillBot.objects.ICommands;

import java.util.List;

public class PingCommand implements ICommands {

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong!").queue((message) ->
                message.editMessageFormat("ping is %sms", event.getJDA().getPing()).queue()
        );
    }

    @Override
    public String getHelp() {
        return "Pong!\n" + "Usage: " + Constants.PREFIX + getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }
}
