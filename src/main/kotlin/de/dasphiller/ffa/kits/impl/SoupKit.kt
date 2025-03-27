package de.dasphiller.ffa.kits.impl

import de.dasphiller.kitapi.kit.Kit
import de.dasphiller.kitapi.kit.kit
import de.dasphiller.ffa.mm
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.realMaxHealth
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.min

class SoupKit(
    override var cooldown: Double? = 0.0,
    override val description: Component = mm.deserialize("<#d45454>Suppen"),
    override val icon: Material = Material.MUSHROOM_STEW,
    override val name: String = "Soup"
) : Kit() {

    private val soupItems = arrayListOf(
        Material.MUSHROOM_STEW,
        Material.SUSPICIOUS_STEW
    )

    private val soups = 5


    override fun enterArena(player: Player) {
        repeat(soups) {
            player.inventory.addItem(ItemStack(Material.MUSHROOM_STEW))
        }
    }

    private val interactSoup = listen<PlayerInteractEvent> {
        if (it.item == null) return@listen
        if (it.player.kit != this) return@listen
        if (soupItems.contains(it.material) && it.hasItem()) {
            if (it.action == Action.LEFT_CLICK_AIR || it.action == Action.LEFT_CLICK_BLOCK) return@listen
            val heal = 5
            if (it.player.health < it.player.realMaxHealth) {
                it.player.health = min(it.player.health + heal, it.player.realMaxHealth);
                it.player.inventory.setItemInMainHand(ItemStack(Material.BOWL));
            }
        }
    }

    private val killPlayer = listen<PlayerDeathEvent> {
        if (it.player.killer?.kit != this) return@listen
        val player = it.player.killer ?: return@listen
        repeat(soups) { _ ->
            player.inventory.addItem(ItemStack(Material.MUSHROOM_STEW))
        }
    }

    override fun registerKit() {
        interactSoup
        killPlayer
    }

    override fun unregisterKit() {
        interactSoup.unregister()
        killPlayer.unregister()
    }

}