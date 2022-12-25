package me.miguelcr.mcquirk.commands

import me.miguelcr.mcquirk.RegexData
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddRegex(private val regexData: RegexData) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            return false
        }
        if (sender is Player) {
            regexData.addUserRegex(sender, args[0])
        } else {
            return false
        }
        return true
    }
}
