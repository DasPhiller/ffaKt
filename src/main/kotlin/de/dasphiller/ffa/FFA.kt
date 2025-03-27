package de.dasphiller.ffa

import de.dasphiller.ffa.commands.suicide
import de.dasphiller.ffa.game.GameManager
import de.dasphiller.kitapi.KitApi
import de.dasphiller.ffa.kits.KitManager
import de.dasphiller.ffa.listener.ConnectionListener
import net.axay.kspigot.extensions.geometry.LocationArea
import net.axay.kspigot.main.KSpigot
import net.axay.kspigot.main.KSpigotMainInstance
import net.axay.kspigot.structures.fillBlocks
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.GameRule
import org.bukkit.Location
import org.bukkit.Material

class FFA : KSpigot() {


    override fun startup() {
        KitManager.addKits()
        kitApi.registerKits()
        val world = server.getWorld("world")!!
        createBarriers(
            Location(
                world,
                -100.0,
                140.0,
                116.0,
            ),
            Location(
                world,  100.0, 140.0, -84.0

            )
        )
        GameManager()
        world.setGameRule(GameRule.KEEP_INVENTORY, true)
        ConnectionListener
        world.worldBorder.setCenter(world.spawnLocation.x, world.spawnLocation.z)
        println("${world.spawnLocation.x} " + world.spawnLocation.y)
        world.worldBorder.size = 200.0
        KSpigotMainInstance
        suicide
    }

    override fun shutdown() {
        kitApi.unregisterKits()
    }

    private fun createBarriers(loc1: Location, loc2: Location) {
        val area = LocationArea(loc1, loc2)
        area.fillBlocks.forEach {
            it.type = Material.BARRIER
        }
    }
}

val kitApi get() = KitApi()

val mm = MiniMessage.miniMessage()