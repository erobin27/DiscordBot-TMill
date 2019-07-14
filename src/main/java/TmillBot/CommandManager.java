package TmillBot;

import TmillBot.commands.*;
import TmillBot.commands.music.JoinCommand;
import TmillBot.commands.music.LeaveCommand;
import TmillBot.commands.music.PlayCommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import TmillBot.objects.ICommands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {

    private final Map<String, ICommands> commands = new HashMap<>();

    CommandManager() {
        addCommand(new PingCommand());
        addCommand(new JoinCommand());
        addCommand(new LeaveCommand());
        addCommand(new PlayCommand());
        addCommand(new TalkCommand());
    }

    private void addCommand(ICommands command) {

       if (!commands.containsKey(command.getInvoke())) {
            commands.put(command.getInvoke(),command);
        }

    }

    void handleCommand(GuildMessageReceivedEvent event) {
        final String[] split = event.getMessage().getContentRaw().replaceFirst(
                "(?i)" + Pattern.quote(Constants.PREFIX), "").split("\\s+");
        final String invoke = split[0].toLowerCase();

        if(commands.containsKey(invoke)) {
            final List<String> args = Arrays.asList(split).subList(1, split.length);

            try {
                commands.get(invoke).handle(args, event);
            }
            catch (Exception e)
            {
                System.out.println("fuck");
            }
        }
    }
}