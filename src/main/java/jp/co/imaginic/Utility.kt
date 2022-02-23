package jp.co.imaginic

import com.mojang.authlib.properties.Property
import jp.co.imaginic.Main.Companion.instance
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.entity.Player

object Utility {
    fun changeSkin(player: Player, skinName: String): Boolean {
        val profile = (player as CraftPlayer).profile
        val skin = getSkin(skinName) ?: return false

        profile.properties.clear()
        profile.properties.put("textures", skin)
        Bukkit.getScheduler().scheduleSyncDelayedTask(instance!!) {
            player.spigot().respawn()

            Bukkit.getOnlinePlayers().forEach {
                it.hidePlayer(instance!!, player)
            }

            Bukkit.getOnlinePlayers().forEach {
                it.showPlayer(instance!!, player)
            }
        }

        return true
    }

    fun getSkin(skinName: String): Property? {
        val value = instance?.config?.getString("skins.${skinName}.value") ?: return null
        val signature = instance?.config?.getString("skins.${skinName}.signature") ?: return null

        return Property("textures", value, signature)
    }
}