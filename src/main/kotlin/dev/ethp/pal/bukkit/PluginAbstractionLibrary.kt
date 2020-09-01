package dev.ethp.pal.bukkit

import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class PluginAbstractionLibrary : JavaPlugin() {

	override fun onEnable() {
		getCommand("plugin-abstraction-library")!!.setExecutor { sender, _, _, _ ->
			if (!sender.isOp) {
				return@setExecutor false
			}

			sender.sendMessage(ChatColor.YELLOW.toString() + "Plugin Abstraction Lib")
			sender.sendMessage(ChatColor.YELLOW.toString() + "Version " + this.description.version)
			return@setExecutor true
		}
	}
	
}
