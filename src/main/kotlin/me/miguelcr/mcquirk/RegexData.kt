package me.miguelcr.mcquirk

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class RegexData(private val plugin: JavaPlugin) {
    private var data: MutableMap<String, MutableList<Pair<Regex, String>>> = mutableMapOf()

    private fun getFromData(str: String): MutableList<Pair<Regex, String>> {
        return data.getOrPut(str) {
            val userConfigSection = getOrPutUserConfigSection(str)
            userConfigSection.getMapList()
            mutableListOf()
        }
    }

    private fun getOrPutUsersConfigSection(): ConfigurationSection {
        return plugin.config.getConfigurationSection("users") ?: run {
            plugin.config.createSection("users")
            plugin.saveConfig()
            plugin.config.getConfigurationSection("users")!!
        }
    }

    private fun getOrPutUserConfigSection(name: String): ConfigurationSection {
        val usersSection = getOrPutUsersConfigSection()
        return usersSection.getConfigurationSection(name) ?: run {
            usersSection.createSection(name)
            plugin.saveConfig()
            usersSection.getConfigurationSection(name)!!
        }
    }

    private fun getUser(name: String): List<Pair<String, String>> {
        val userConfigSection = getOrPutUserConfigSection(name)
        return userConfigSection.getKeys(false).map {
            Pair(it!!, userConfigSection.getString(it)!!)
        }
    }

    fun addUserRegex(player: Player, str: String) {
        val id = player.uniqueId.toString()



        val userSection = getOrPutUserConfigSection(id)
        val user = userSection.getStringList(id)
        if (!user.contains(str)) {
            user.add(str)
        }
        userSection.set(id, user)
        plugin.saveConfig()
    }
}
