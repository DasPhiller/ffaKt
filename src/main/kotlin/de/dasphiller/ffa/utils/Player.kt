package de.dasphiller.ffa.utils

import de.dasphiller.ffa.kits.Revive
import de.dasphiller.kitapi.kit.kit
import net.axay.kspigot.items.itemStack
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot

fun Player.giveKitSelector() {
    inventory.addItem(kitSelectorItem)
}

fun Player.teleportArena() {
    teleport(Location(world, 0.50, 67.00, -2.56))
}

fun Player.giveKit() {
    inventory.setItem(EquipmentSlot.HEAD, helmet)
    inventory.setItem(EquipmentSlot.LEGS, leggins)
    inventory.setItem(EquipmentSlot.CHEST, chestplate)
    inventory.setItem(EquipmentSlot.FEET, boots)
    inventory.setItem(0, sword)
    inventory.setItem(1, axe)
    inventory.setItem(2, crossbow)
    inventory.setItem(6, stone)
    inventory.addItem(itemStack(Material.ARROW) {
        amount = 16
    })
    if (kit != Revive()) inventory.setItem(EquipmentSlot.OFF_HAND, shield)
    inventory.addItem(shield)
    inventory.addItem(itemStack(Material.COOKED_BEEF) {
        amount = 32
    })
}