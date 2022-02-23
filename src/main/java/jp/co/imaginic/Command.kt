package jp.co.imaginic

import jp.co.imaginic.Utility.changeSkin
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This Command can only be executed by Player.")
            return false
        }

        if (!sender.hasPermission("imaginic.skin.command")) {
            sender.sendMessage("§4You are not allowed to do this.")
            return false
        }

        when (args.size) {
            1 -> {
                val skinName = args[0]

                if (changeSkin(sender, skinName)) {
                    sender.sendMessage("§aSuccessfully changed.")
                }
                else {
                    sender.sendMessage("§4Something happened! Couldn't change skin!")
                    return false
                }
            }

            2 -> {
                val skinName = args[0]
                val target = Bukkit.getPlayer(args[1])

                if (target == null) {
                    sender.sendMessage("§4Unknown Player name.")
                    return false
                }

                if (changeSkin(target, skinName)) {
                    sender.sendMessage("§aSuccessfully changed.")
                }
                else {
                    sender.sendMessage("§4Something happened! Couldn't change skin!")
                    return false
                }
            }

            else -> {
                sender.sendMessage("§4Wrong usage.")
                return false
            }
        }

        return false
    }
}