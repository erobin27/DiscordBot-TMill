package TmillBot.commands;

import TmillBot.music.PlayerManager;
import TmillBot.objects.ICommands;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TalkCommand implements ICommands {
    String[] phrases =  {
            "https://youtu.be/VQl5sqt0WVE", //I FUCKING LOVE CAROWINDS
            "https://youtu.be/sqExxwX8hO0", //Yeah that's gonna happen
            "https://youtu.be/qFO66NNt8d8", //wooo woooo woooo
            "https://youtu.be/gYpk_u3hjuY", //i am a man of constant sorrow
            "https://youtu.be/2qFI6fNLq8s", //We're goin to carowinds
            "https://youtu.be/RSqO4V5xJqU", //Garrett has a 12 inch cock
            "https://youtu.be/HHbDSAx-kBo", //wooooooooooooo
            "https://youtu.be/Exf4FUGlxIU" //I LOVE ROLLER COASTERS

    };
    public static GuildMessageReceivedEvent eventPass;

    private void join(List<String> args, GuildMessageReceivedEvent event)
    {
        TextChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("Please join a voice channel first").queue();
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = event.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT))
        {
            channel.sendMessageFormat("I am missing permission to join %s", voiceChannel).queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        System.out.println("3");
    }

    public void leave(GuildMessageReceivedEvent event)
    {

        TextChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();


        audioManager.closeAudioConnection();
        channel.sendMessage("Disconnected from your channel").queue();
    }

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) throws InterruptedException {
        PlayerManager manager = PlayerManager.getInstance();
        Random rnd = new Random();
        eventPass = event;

        System.out.println("1");
        join(args,event);
        System.out.println("3");
        manager.loadAndPlay(event.getChannel(), phrases[    //PLAY TMILL PHRASE
                rnd.nextInt(phrases.length)
                ]);

        AudioPlayer player = manager.getGuildMusicManager(event.getGuild()).player;
        AudioTrack track = manager.getGuildMusicManager(event.getGuild()).player.getPlayingTrack();

        manager.getGuildMusicManager(event.getGuild()).player.setVolume(200);
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getInvoke() {
        return "talk";
    }
}
