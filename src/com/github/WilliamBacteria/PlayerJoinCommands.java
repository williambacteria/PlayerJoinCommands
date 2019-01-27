package com.github.WilliamBacteria;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;

public class PlayerJoinCommands extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getDataFolder().mkdirs();
		CreateConfig();
	}

	public void CreateConfig() {
		File f = new File(getDataFolder(), "config.yml");
		if (!f.exists())
			saveDefaultConfig();
		reloadConfig();

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.dispatchCommand(evt.getPlayer(), getConfig().getString("command"));
			}
		}.runTaskLater(this, 200L);
	}
}
