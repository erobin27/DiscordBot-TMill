package TmillBot.commands.music;

import TmillBot.music.PlayerManager;
import TmillBot.objects.ICommands;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class PlayCommand implements ICommands {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlay(event.getChannel(), "https://www.youtube.com/watch?v=ye5BuYf8q4o");
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(200);
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getInvoke() {
        return "play";
    }
}
