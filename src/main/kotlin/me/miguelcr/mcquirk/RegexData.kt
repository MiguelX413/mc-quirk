package me.miguelcr.mcquirk

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class RegexData(private val plugin: JavaPlugin) {
    private var data = emptyMap<String, Map<Regex, String>>()

    private fun getUsersSection(): ConfigurationSection {
        return plugin.config.getConfigurationSection("users") ?: run {
            plugin.config.createSection("users")
            plugin.saveConfig()
            return plugin.config.getConfigurationSection("users")!!
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

    private fun getData(name: String): Map<Regex, String> {
        return data[name] ?: run {
            val userSection = getUsersSection()
            userSection.getMapList(name)
            emptyMap()
        }
    }

    fun getPlayerData(user: Player): Map<Regex, String> {
        return getData(user.uniqueId.toString())
    }

    fun getConsoleData(): Map<Regex, String> {
        return getData("console")
    }
}
