package no.andste.serverjoin.listeners;

import no.andste.serverjoin.ServerJoin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class PlayerQuit implements Listener {
    private final ServerJoin plugin;
    public PlayerQuit(ServerJoin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        EmbedBuilder embedJoin = new EmbedBuilder()
                .setAuthor(player.getName() + " left the server", "", "https://minotar.net/helm/" + player.getName())
                .setColor(Color.RED);
        plugin.getChannel().sendMessage(embedJoin);
    }
}
