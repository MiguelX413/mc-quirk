package me.miguelcr.mcquirk.commands

import me.miguelcr.mcquirk.MCQuirk
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player

class AddRegex(private val plugin: MCQuirk) : CommandExecutor {
    private fun getUserSection(): ConfigurationSection {
        return plugin.config.getConfigurationSection("users") ?: run {
            plugin.config.createSection("users")
            plugin.saveConfig()
            return plugin.config.getConfigurationSection("users")!!
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            return false
        }
        if (sender is Player) {
            val id = sender.uniqueId.toString()
            val userSection = getUserSection()
            val user = userSection.getStringList(id)
            if (!user.contains(args[0])) {
                user.add(args[0])
            }
            userSection.set(id, user)
            plugin.saveConfig()
        } else {
            return false
        }
        return true
    }
}
