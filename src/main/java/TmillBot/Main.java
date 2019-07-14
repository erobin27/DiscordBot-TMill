package TmillBot;

import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {

    private Main() {
        try {
            new JDABuilder(AccountType.BOT)
                    .setToken("NTk5NTY4NDY1NzA3NDAxMjE3.XSprMQ.StbSx2o6Vet0lAyBcXA_jGUxoVI")
                    .addEventListener(new Listener())
                    .build()
                    .getPresence().setGame(Game.playing("with his dick while driving."));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
