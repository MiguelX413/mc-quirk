package me.miguelcr.mcquirk

import me.miguelcr.mcquirk.commands.AddRegex
import org.bukkit.plugin.java.JavaPlugin

class MCQuirk : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        config.options().copyDefaults()
        saveDefaultConfig()
        getCommand("addregex")?.setExecutor(AddRegex(this))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
