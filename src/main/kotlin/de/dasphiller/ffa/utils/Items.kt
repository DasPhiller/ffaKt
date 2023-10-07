package de.dasphiller.ffa.utils

import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

val kitSelectorItem = itemStack(Material.CHEST) {
    meta {
        displayName(Component.text("Kits").decoration(TextDecoration.ITALIC, false))
    }
}

val sword = itemStack(Material.IRON_SWORD) {
    meta {
        addEnchant(Enchantment.DAMAGE_ALL, 1, true)
    }
}

val axe = itemStack(Material.DIAMOND_AXE) {
    meta {
        addEnchant(Enchantment.DURABILITY, 3, true)
    }
}

val helmet = itemStack(Material.TURTLE_HELMET) {
    meta {
        addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
    }
}

val chestplate = itemStack(Material.DIAMOND_CHESTPLATE) {
    meta {
        addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
    }
}

val leggins = itemStack(Material.DIAMOND_LEGGINGS) {
    meta {
        addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
    }
}

val boots = itemStack(Material.IRON_BOOTS) {
    meta {
        addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
    }
}

val crossbow = itemStack(Material.CROSSBOW) {
    meta {
        addEnchant(Enchantment.MULTISHOT, 1, true)
    }
}

val stone = itemStack(Material.STONE) {
    meta {
        amount = 64
    }
}

val shield = itemStack(Material.SHIELD) {
    addEnchantment(Enchantment.DURABILITY, 3)
}