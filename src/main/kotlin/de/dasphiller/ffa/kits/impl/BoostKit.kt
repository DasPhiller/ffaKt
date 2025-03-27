package de.dasphiller.ffa.kits.impl

import de.dasphiller.ffa.mm
import de.dasphiller.kitapi.kit.Kit
import de.dasphiller.kitapi.kit.canUseKit
import de.dasphiller.kitapi.kit.kit
import de.dasphiller.kitapi.kit.nextKitUse
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector

class BoostKit : Kit() {
    override var cooldown: Double? = 3.0
    override val description: Component = mm.deserialize("<#586bf9>Rechtklicke um einen Schub zu bekommen")
    override val icon: Material = Material.DIAMOND_BOOTS
    override val name: String = "Boost"

    private val boostItem = itemStack(Material.FEATHER) {
        meta {
            displayName(mm.deserialize("<blue>Boost</blue>").decoration(TextDecoration.ITALIC, false))

        }
    }

    override fun enterArena(player: Player) {
        player.inventory.addItem(boostItem)
    }

    private val interact = listen<PlayerInteractEvent> {
        if (it.item == boostItem && it.player.kit == this) {
            if (!it.player.canUseKit()) return@listen
            if (cooldown == null) return@listen
            val player = it.player
            val direction = player.eyeLocation.direction.normalize()
            val velocity = direction.multiply(1.5)
            player.velocity = velocity
            player.sendMessage(Component.text("You have been boosted!", NamedTextColor.GRAY))
            player.nextKitUse = cooldown!!.toLong()
        }
    }

    override fun registerKit() {
        interact
    }

    override fun unregisterKit() {
        interact.unregister()
    }

}