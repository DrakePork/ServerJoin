package no.andste.serverjoin.listeners;

import no.andste.serverjoin.ServerJoin;
import org.javacord.api.event.channel.server.ServerChannelDeleteEvent;
import org.javacord.api.listener.channel.server.ServerChannelDeleteListener;

import java.util.logging.Level;

public class ServerChannelDelete implements ServerChannelDeleteListener {
    private final ServerJoin plugin;
    public ServerChannelDelete(ServerJoin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void onServerChannelDelete(ServerChannelDeleteEvent event) {
        if (event.getChannel().getIdAsString().equals(plugin.getChannel().getIdAsString())) {
            plugin.getLogger().log(Level.SEVERE, "The channel you have set in the config has been deleted. Disabling plugin...");
            plugin.onDisable();
        }
    }
}
