package me.MaurycyJaniak.GSSBot;

import io.github.cdimascio.dotenv.Dotenv;
import me.MaurycyJaniak.GSSBot.commands.CommandManager;
import me.MaurycyJaniak.GSSBot.listeners.EventListener;
import me.MaurycyJaniak.GSSBot.listeners.ModalListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class GSSBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public GSSBot() throws Exception {
        // Load environment variables
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        // Build shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.customStatus("Scheming"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);

        //Need to add flags to cache certain stuff about server members
        //builder.enableCache(CacheFlag.ACTIVITY);

        shardManager = builder.build();
        shardManager.addEventListener(new EventListener(), new CommandManager(), new ModalListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig(){
        return config;
    }

    public static void main(String[] args) {
        try {
            GSSBot bot = new GSSBot();
        } catch (Exception e) {
            System.out.println("Error: Provided bot token is invalid");
        }

    }

}
