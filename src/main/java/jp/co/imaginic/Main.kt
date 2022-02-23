package jp.co.imaginic

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        getCommand("skin")!!.setExecutor(Command())
    }

    override fun onDisable() {}

    companion object {
        var instance: Main? = null
    }
}