package jp.co.imaginic

import com.mojang.authlib.properties.Property
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import net.minecraft.server.v1_16_R3.PlayerConnection
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        instance = this
        getCommand("skin")!!.setExecutor(Command())
    }

    override fun onDisable() {}
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        changeSkin(e.player)
    }

    companion object {
        var instance: Main? = null
        fun changeSkin(player: Player) {
            val profile = (player as CraftPlayer).handle.profile
            val connection = player.handle.playerConnection
            connection.sendPacket(
                PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
                    player.handle
                )
            )
            profile.properties.removeAll("textures")
            profile.properties.put("textures", skin)
            connection.sendPacket(
                PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
                    player.handle
                )
            )
        }

        private val skin: Property
            get() {
                val r = Random()
                val num = r.nextInt(4)
                return when (num) {
                    0 -> Property(
                        "textures",
                        "eyJ0aW1lc3RhbXAiOjE1ODcyMjU2MTc5NTcsInByb2ZpbGVJZCI6ImY3Yzc3ZDk5OWYxNTRhNjZhODdkYzRhNTFlZjMwZDE5IiwicHJvZmlsZU5hbWUiOiJoeXBpeGVsIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yNDdjMDU4ODQ1ZjNiNmQwMzQ4YWFlNTZiNmFhMWE2MWM0NjlkODRjYmFiYTA0ODE2NDA1MGExZDMzNWQ2ZWEwIn19fQ==",
                        "ll34a/xBZd5jX9qxHns+A00RwUAbyrapBd4JcfSqfHfeWWp1Ko1FzYi42I6iLihJDn5v3pCoV0/pDG++ih4rmhBrT47H7LmrN7ZHCUfrU2kMTqMNJo9NJAWtm90lrGfi8D5uMBDO83wxXi/BJBgIAj8uNFSmNis03TBxz6ORmkkNj8P7sec56AzW+4Cir86VyHyn5w7ZN/hbBAYvZBwYzoUS2b+8FraRfrDz5D1znl5Cosy78q8q/wPVWJh2ZbW4fgFalqxdx6yiSUfmV+BoN2/0z5WJM2F5QDe1zzB8JgaBHq74CqQvcacmQxJpl/hqet9ShDJdGVhj1u4T4LOMxfV7PykDoF/5/utgeyNhzIYnJQUn/FwKgbSLszfqBkMqsxrqsXQeofqRfelx/CFFtsuG9U7g9Gn+uUgCSJI53jP9qbosZKevEvSzVd7Ons59rvwoQQAeVR0hxal87ABTlmU17z98hJ8zB08gIw/jzMxERT32x7L6cM21+DKBINA/ELmdkxAymHyW8rFtvHt71PuyQykzEbE/OUKWpjMg85EbuT67QP9PSAPUMPvf+H49KJh6zVFEbWDfHFGqavNOdqPJEqZIM4xzRWYSYxjvWKoWpmaDWxQmJC+2iEzlmwaMf6eiIxviU2RviIbe2703iXVivQOd0jVPFnt/92kTIUU="
                    )
                    1 -> Property(
                        "textures",
                        "eyJ0aW1lc3RhbXAiOjE0Njg0OTA2MDk3MTIsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJJbnZlbnRpdmVHYW1lcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZmUwZTAzNjIyMTdjMDM3MTBjYjc3Njk1YTJlN2UyMTM5YWVhMTVkNGQ3YjFhNzA5NTk5ZWNhMjM3MjcxNCJ9fX0=",
                        "CyszA9ioNWwew2VH9FNIpXy5wK2BgrURuNR8o0gTZIsaVj/uljz7aOLZ7ILgnz4MbKbDvD1//ved0k14/MnknvVuzyRb4LBDp+3vFvuIsgmvN4nsuoQhBeHl6amBNfcnAEeS6RXE5fIYcY8sI53I0wQdTBrry9+Sle4wRTmtCpJnxjRzyrl5jTrTznih3tm76Ix1PeYXZ8oYypFGfMd5FTM6IivvU9+UT70OUXAHsT1MsQeGE0G7tkwQuUAg/9TX76tbmeP+l8k9oL+pUvPdbZgTbYwtxwjFFpj/1fpVfu6oLcqcEnrxlxz/ju/lnmTbQjqnTE7DtoBhrYyEigMkIkOm45i6kYynDJnlBK0ttkwGtKN2S+7BnijHKPxCSU0ztx1zIxtR8Ygx5L+IvfLd/0B0ekKCvKXy0fTs1uIv8CJqccIAsBwASH1ukId7xtFr4bB0Oe5MnT4NSYD7nSr6Gjy7SOiLGna8XN468zJx7F3X8gF8Tvgt/BnaWiuEO8rAONw1CsIX3Rj29uLKxfUc9u9mA+WuiGeN2vTrzqPjBmy62qaDlV9Lyu3CIlhhxTjiv9CEm1ciQf4b5a/azxPhSOLNnW3WuFqRPnmfRTuVMlJCcYY26qNzWV2UuTmOX8JKGjWfbvC9clOB+weVY3pxOfp2cWZET732oJJPYS55ubY="
                    )
                    2 -> Property(
                        "textures",
                        "eyJ0aW1lc3RhbXAiOjE1MTcyNzM4NzMzMzQsInByb2ZpbGVJZCI6ImFkOGZlZmFhODM1MTQ1NGJiNzM5YTRlYWE4NzIxNzNmIiwicHJvZmlsZU5hbWUiOiJDb25ub3JMaW5mb290Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NGQyNzViZDYwNjg0MTljOGI3NWJjOTllYTYxMGYzZjljYzhkODE2N2E1ZjYyNzJmZDZjZDQ2Zjg3ZWNiNyJ9fX0=",
                        "Z1UkmJVJ+uUw+FhPp3PZ4v3EZHfVkobWTdAK/Z8ARq+MfstKGRHYIrtTBUQN51lAMCweMi7Iw6qmVWf/bHJLG6prK4bv7D/zXuZoMzk6Yl9hdKgJ8Bd3Dy0vWEWHJkclmZgJ4HEO1DfKIkOP+WMhilEr6nn87nCVxchFhRBng4uajNT4ALq3E6X+E+xJJoCMlR5JV0OFSq6ESD3Pyd7YAw2TkEAq3dizKiNnTdt5nhyXfRjhE9wl/fP2EUuTEETM8bCy3V6rmJy9O0TqNSuz2I4W1cYGX668gvd7KLwV/VQIh/6v3AuXsvogzLD1031cBvk23xqejwmCV8zM5XliGcHJkWu2O0GdNcQOgrpjbsEprO20y8W0qOVW8eRfdpCdq3atsx6SSs+vdck9APN7oVmkNAFjrYoWTZYmyeTTDZBhX3n0dD1u64UgtnVkau0micjCw3KZ/Fr56Bku4mTvPjJJ/ERphI9IHC2HOeSEMkVE9YaQobPMaz+FxHiXjTw1EDbYf4/DMviItFwrAifi+/zma5h7Rr5sHRE+TEnkKJCl91373XW3FuFeJTf0nrrWq2NJ0cfmGo9/ru4Nr0f6gD0Etv6YfrA0q+pLDQbVCSISZrXCtDpCWH0cxs3QP6H4R4L32MtoHbiC8UYmYRAqJymmcL01ir8r0e8SjjBupXA="
                    )
                    else -> Property(
                        "textures",
                        "ewogICJ0aW1lc3RhbXAiIDogMTY0NDMzMjIzMjE1NCwKICAicHJvZmlsZUlkIiA6ICI4N2Q0M2U1NmVjNDA0ZWRhOTkyZmJlZjgyNGU0YTQzYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJDaGlMeW5uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdlZWI4NTlkYTBmZjRjYjYyNDVjM2Y4ODQ1Yjc0M2Q5NTdhOTA5ZDJjOTQ5MzBhZDZhNjM4ZjhmNDYxOTVmMmMiCiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U3ZGZlYTE2ZGM4M2M5N2RmMDFhMTJmYWJiZDEyMTYzNTljMGNkMGVhNDJmOTk5OWI2ZTk3YzU4NDk2M2U5ODAiCiAgICB9CiAgfQp9",
                        "MldwvuwS7fev+4itTXNU4gVgo2T5ZOOqzuBE+JvbS+veFTxosIQaKEOTDEunq7Ej4zvSuB4Nt6CHqPm6cFF0T8mLGZI7WBFyL/r2xrqh8+To5Y14KSjv2W+Sf1TvCOFJV6gxQiVkjI6XzYPbq+fkx+1ACkohNY194Z317giMzCu6u8+CKHIukQyD4jmkI7VxEZ4HEVUYOdjszogK9Kf8xWqVuU7UmIzdB7bZLy1Xk4rUIhTCFkk5+aJHg6shodvB2r6wvCZVfIma5f1bLFMdL0Z37uD41qNAj7WTS770zrTu7XtWmUp4Sozman49zX05PoQe3u0fMghKj9uS68ZpBsSMflud/taKXBt1mRybe/ib0ojtEEg26Dr7a59k7kCZIpl8MBw+Fh7sfv20bEEgmweExtFIBS4hqAVCsotXgJcjsDDClbrbSoQN68CUZoAoHwHjltzfEsV7mVu8AE3ckHtjx0PzEuc46AeALFUKIaJFBZnNKszzdKM6d8x85z015zjHqca5CHZPqZtQQLxFRo6PXbwN4+OKxR/5aIMjtGWIByHAOcSEuTbKWBoh5r+QfJ2b+Ciym17XMk1W9wLh22yvbAYxFysjGGGzwcWJS/9AMBCWgeVImLWoJW2AH/IZJetN4KhjM/iBlOUnn9hTxd1HaV0PFnX08ApSuW6vfuQ="
                    )
                }
            }
    }
}