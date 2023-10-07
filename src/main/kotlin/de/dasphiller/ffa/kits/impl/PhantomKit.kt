package de.dasphiller.ffa.kits.impl

import de.dasphiller.ffa.utils.chestplate
import de.dasphiller.kitapi.kit.*
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.runnables.task
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.util.Vector

class PhantomKit(
    override val description: Component = mm.deserialize("<color:#138eff>You can fly around"),
    override val icon: Material = Material.PHANTOM_MEMBRANE,
    override val name: String = "Phantom",
    override var cooldown: Double? = 40.0
) : Kit() {

    override fun enterArena(player: Player) {
        player.inventory.addItem(itemStack(Material.PHANTOM_MEMBRANE) {
            meta {
                displayName(mm.deserialize("<#138eff>Phantom"))
            }
        })
    }

    private val interact = listen<PlayerInteractEvent> { event ->
        val player = event.player
        if (event.item?.type == Material.AIR) return@listen
        if (event.player.kit == this && event.item?.type == Material.PHANTOM_MEMBRANE) {
            if (!event.player.canUseKit()) return@listen
            event.player.velocity = Vector(0.0, 2.0, 0.0)
            event.player.inventory.setItem(EquipmentSlot.CHEST, ItemStack(Material.ELYTRA))
            event.player.nextKitUse = cooldown?.toLong()!!
            task(false, 20, 20) {
                if (player.location.block.getRelative(BlockFace.DOWN).type != Material.AIR) {
                    player.inventory.setItem(EquipmentSlot.CHEST, chestplate)
                    it.cancel()
                }
            }
        }
    }

    private val click = listen<InventoryClickEvent> {
        if (it.whoClicked !is Player) return@listen
        val player = it.whoClicked as Player
        if (it.currentItem?.type != Material.ELYTRA) return@listen
        if (player.kit == this) {
            it.isCancelled = true
        }
    }

    override fun registerKit() {
        interact
        click
    }

    override fun unregisterKit() {
        interact.unregister()
        click.unregister()
    }
}