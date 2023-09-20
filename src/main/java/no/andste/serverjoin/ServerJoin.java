package no.andste.serverjoin;

import no.andste.serverjoin.listeners.PlayerJoin;
import no.andste.serverjoin.listeners.PlayerQuit;
import no.andste.serverjoin.listeners.ServerChannelDelete;
import no.andste.serverjoin.utils.ConfigCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.intent.Intent;

public class ServerJoin extends JavaPlugin {
    private DiscordApi discApi;
    private ServerTextChannel channel;
    @Override
    public void onEnable() {
        new ConfigCreator(this).init();

        String dToken = getConfig().getString("discord-token");
        if(dToken == null || dToken.isEmpty()) {
            throw new IllegalArgumentException("Discord token has not been set in config.yml! Disabling plugin..");
        }

        String dChannel = getConfig().getString("discord-channel");
        if(dChannel == null || dChannel.isEmpty()) {
            throw new IllegalArgumentException("Discord channel has not been set in config.yml! Disabling plugin..");
        }

        discApi = new DiscordApiBuilder()
                .setToken(dToken)
                .setAllNonPrivilegedIntentsAnd(Intent.MESSAGE_CONTENT, Intent.GUILD_MEMBERS)
                .login()
                .join();

        getLogger().info("Connected to Discord as " + discApi.getYourself().getDiscriminatedName());
        getLogger().info("Open the following url to invite the bot: " + discApi.createBotInvite());
        discApi.getServerTextChannelById(dChannel).ifPresent(serverTextChannel -> channel = serverTextChannel);
        if(channel == null) {
            throw new IllegalArgumentException("Discord channel could not be found! Disabling plugin..");
        }
        channel.sendMessage(":white_check_mark: **Server has started**");

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
        discApi.addListener(new ServerChannelDelete(this));
    }
    @Override
    public void onDisable() {
        if(discApi != null) {
            if(channel != null) {
                channel.sendMessage(":octagonal_sign: **Server has stopped**");
            }
            discApi.disconnect();
        }
    }
    public ServerTextChannel getChannel() {
        return channel;
    }
}
