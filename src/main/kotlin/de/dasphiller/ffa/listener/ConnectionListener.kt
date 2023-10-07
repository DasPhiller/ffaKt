package de.dasphiller.ffa.listener

import de.dasphiller.kitapi.kit.*
import de.dasphiller.kitapi.kit.impl.NoneKit
import de.dasphiller.ffa.scoreboard.createScoreboard
import de.dasphiller.ffa.utils.giveKitSelector
import de.dasphiller.ffa.utils.kitGui
import de.dasphiller.ffa.utils.kitSelectorItem
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.heal
import net.axay.kspigot.extensions.bukkit.saturate
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.openGUI
import net.axay.kspigot.runnables.taskRunLater
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent

object ConnectionListener {

    private val join = listen<PlayerJoinEvent> {
        it.player.inventory.clear()
        it.player.heal()
        it.player.saturate()
        it.player.teleport(Location(Bukkit.getWorld("world")!!, 50.0, 141.0, 50.0))
        it.player.init()
        it.player.giveKitSelector()
        it.player.nextKitUse = 0
        it.joinMessage(Component.text("${it.player.name} hat das Spiel betreten"))
    }

    private val interact = listen<PlayerInteractEvent> {
        if (it.item == null) return@listen
        if (it.item == kitSelectorItem) {
            it.isCancelled = true
            it.player.openGUI(kitGui)
        }
    }

    private val damage = listen<EntityDamageByEntityEvent> {
        if (it.entity.location.y > 140) it.isCancelled = true
    }

    private val death = listen<PlayerDeathEvent> {
        it.player.inventory.clear()
        it.player.addToKit(NoneKit())
    }
    private val respawn = listen<PlayerRespawnEvent> {
        it.player.nextKitUse = 0
        it.respawnLocation = Location(Bukkit.getWorld("world")!!, 50.0, 141.0, 50.0)
        it.player.giveKitSelector()
    }

    private val spawn = listen<CreatureSpawnEvent> {
        it.isCancelled = it.entityType != EntityType.BLAZE
    }

    private val blockPlace = listen<BlockPlaceEvent> {
        taskRunLater(20*15, true) {
            it.block.type = Material.AIR
        }
    }

    private val blockBreak = listen<BlockBreakEvent> {
        it.isDropItems = false
        val type = it.block.type
        taskRunLater(20*15, true) {
            it.block.type = type
        }
    }

}
