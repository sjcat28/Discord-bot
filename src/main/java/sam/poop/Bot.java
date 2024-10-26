package sam.poop;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import sam.poop.listeners.EventListener;

import javax.security.auth.login.LoginException;

public class Bot {
    //for getting key pair value from env file
    private Dotenv config;

    //field for shard manager
    private ShardManager manager;


    /** main method */
    public static void main(String[] args) {
        Bot bot = new Bot();
    }

    /**
     * loads environment variables upon instantiating a bot
     */
    public Bot(){
        config = Dotenv.configure().load();
        //the token for the usage of the bot
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.watching("Anime"));
        this.manager = builder.build();

        //register listener
        manager.addEventListener(new EventListener());
    }

    /** getter methods **/
    public ShardManager getShardManager() {
        return manager;
    }
     public Dotenv getConfig(){
        return config;
     }
}