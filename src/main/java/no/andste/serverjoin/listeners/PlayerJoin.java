package no.andste.serverjoin.listeners;

import no.andste.serverjoin.ServerJoin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class PlayerJoin implements Listener {
    private final ServerJoin plugin;
    public PlayerJoin(ServerJoin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EmbedBuilder embedJoin = new EmbedBuilder()
                .setAuthor(player.getName() + " joined the server", "", "https://minotar.net/helm/" + player.getName())
                .setColor(Color.GREEN);
        plugin.getChannel().sendMessage(embedJoin);
    }
}
