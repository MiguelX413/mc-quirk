package me.miguelcr.mcquirk

import me.miguelcr.mcquirk.commands.AddRegex
import org.bukkit.plugin.java.JavaPlugin

class MCQuirk : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        config.options().copyDefaults()
        saveDefaultConfig()
        val regexData = RegexData(this);
        getCommand("addregex")?.setExecutor(AddRegex(regexData))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
