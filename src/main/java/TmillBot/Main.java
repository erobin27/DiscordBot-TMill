package TmillBot;

import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {

    private Main() {
        try {
            new JDABuilder(AccountType.BOT)
                    .setToken("secret token")
                    .addEventListener(new Listener())
                    .build()
                    .getPresence().setGame(Game.playing("Tmill Bot."));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
