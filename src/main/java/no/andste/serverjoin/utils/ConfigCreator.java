package no.andste.serverjoin.utils;

import no.andste.serverjoin.ServerJoin;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigCreator {
        private final ServerJoin plugin;
        public ConfigCreator(ServerJoin plugin) {
            this.plugin = plugin;
        }
        public void init() {
            FileConfiguration config = plugin.getConfig();
            config.addDefault("discord-token", "");
            config.addDefault("discord-channel", "");
            config.options().copyDefaults(true);
            plugin.saveConfig();
        }
}
