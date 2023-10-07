package de.dasphiller.ffa.utils

import de.dasphiller.kitapi.kit.Kit
import de.dasphiller.kitapi.kit.addToKit
import de.dasphiller.kitapi.kit.kitList
import de.dasphiller.kitapi.kit.mm
import net.axay.kspigot.extensions.bukkit.heal
import net.axay.kspigot.extensions.bukkit.saturate
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.inventory.ItemStack

val kitGui = kSpigotGUI(GUIType.SIX_BY_NINE) {
    defaultPage = 2
    title = Component.text("Kits")

    page(2) {
        val compound = createRectCompound<Kit>(Slots.RowOneSlotOne, Slots.RowSixSlotNine, iconGenerator = {
            generateKitItem(it)
        }, onClick = { clickEvent, kit ->
            val player = clickEvent.player
            clickEvent.bukkitEvent.isCancelled = true
            player.addToKit(kit)
            player.inventory.clear()
            player.heal()
            player.saturate()
            player.closeInventory()
            player.teleportArena()
            player.giveKit()
            kit.enterArena(player)
            clickEvent.bukkitEvent.clickedInventory!!.setItem(
                clickEvent.bukkitEvent.slot, generateKitItem(kit)
            )
        })
        compound.addContent(kitList)
    }
}

private fun generateKitItem(kit: Kit): ItemStack {
    val itemStack = itemStack(kit.icon) {
        meta {
            displayName(mm.deserialize("<color:#5555FF>${kit.name}").decoration(TextDecoration.ITALIC, false))
            lore(
                listOf(
                    kit.description.decoration(TextDecoration.ITALIC, false),
                    mm.deserialize("<color:#FF5555>Cooldown: ${kit.cooldown?.toInt()}s")
                        .decoration(TextDecoration.ITALIC, false)
                )
            )
        }
    }
    return itemStack
}