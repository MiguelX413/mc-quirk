package me.miguelcr.mcquirk

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class RegexData(private val plugin: JavaPlugin) {
    private var data: Map<String, Pair<Regex, String>> = emptyMap()

    private fun getUsersSection(): ConfigurationSection {
        return plugin.config.getConfigurationSection("users") ?: run {
            plugin.config.createSection("users")
            plugin.saveConfig()
            plugin.config.getConfigurationSection("users")!!
        }
    }

    private fun getUserSection(name: String): ConfigurationSection {
        val usersSection = getUsersSection()
        return usersSection.getConfigurationSection(name) ?: run {
            usersSection.createSection(name)
            plugin.saveConfig()
            usersSection.getConfigurationSection(name)!!
        }
    }

    fun addUserRegex(player: Player, str: String) {
        val id = player.uniqueId.toString()
        val userSection = getUserSection(id)
        val user = userSection.getStringList(id)
        if (!user.contains(str)) {
            user.add(str)
        }
        userSection.set(id, user)
        plugin.saveConfig()
    }
}
