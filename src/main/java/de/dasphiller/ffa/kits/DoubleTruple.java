package de.dasphiller.ffa.kits;

import de.dasphiller.kitapi.kit.Kit;
import de.dasphiller.kitapi.kit.PlayerKt;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.axay.kspigot.event.ListenersKt.unregister;
import static net.axay.kspigot.main.KSpigotKt.getKSpigotMainInstance;

public class DoubleTruple extends Kit implements Listener {

    @Nullable
    @Override
    public Double getCooldown() {
        return 15.0;
    }

    @Override
    public void setCooldown(@Nullable Double aDouble) {

    }

    @NotNull
    @Override
    public Component getDescription() {
        return MiniMessage.miniMessage().deserialize(("<#586bf9>Rechtklicke um einen Schub zu bekommen"));
    }

    @NotNull
    @Override
    public Material getIcon() {
        return Material.DIAMOND_BOOTS;
    }

    @NotNull
    @Override
    public String getName() {
        return "Schub";
    }

    @Override
    public void registerKit() {
        Bukkit.getPluginManager().registerEvents(this, getKSpigotMainInstance());
    }

    @Override
    public void unregisterKit() {
        unregister(this);
    }

    public ItemStack boostItem() {
        ItemStack itemStack = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(MiniMessage.miniMessage().deserialize("<#586bf9>Schub"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void enterArena(@NotNull Player player) {
        player.getInventory().addItem(boostItem());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.FEATHER && PlayerKt.getKit(event.getPlayer()) == this) {
            if (!PlayerKt.canUseKit(event.getPlayer())) return;
            if (this.getCooldown() == null) return;
            BlockFace face = getBlockFacing(event.getPlayer());
            switch (face) {

                case EAST -> event.getPlayer().setVelocity(new Vector(1.7, 0.4, 0));
                case NORTH -> event.getPlayer().setVelocity(new Vector(0, 0.4, -1.7));
                case WEST -> event.getPlayer().setVelocity(new Vector(-1.7, 0.4, 0));
                case SOUTH -> event.getPlayer().setVelocity(new Vector(0, 0.4, 1.7));
                case UP -> event.getPlayer().setVelocity(new Vector(0, 1, 0));
            }
            PlayerKt.setNextKitUse(event.getPlayer(), this.getCooldown().longValue());
        }
    }

    public static BlockFace getBlockFacing(Player p) {
        float yaw = p.getLocation().getYaw();
        float pitch = p.getLocation().getPitch();
        if (pitch >= 45) {
            return BlockFace.DOWN;
        } else if (pitch <= -45) {
            return BlockFace.UP;
        } else if (yaw > 45 && yaw < 135) {
            return BlockFace.WEST;
        } else if (yaw >= 135 || yaw < -135) {
            return BlockFace.NORTH;
        } else if (yaw >= -135 && yaw < -45) {
            return BlockFace.EAST;
        } else {
            return BlockFace.SOUTH;
        }
    }

}
