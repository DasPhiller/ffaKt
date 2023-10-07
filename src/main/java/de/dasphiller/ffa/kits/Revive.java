package de.dasphiller.ffa.kits;

import de.dasphiller.kitapi.kit.Kit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Revive extends Kit {

    @Nullable
    @Override
    public Double getCooldown() {
        return 0.0;
    }

    @Override
    public void setCooldown(@Nullable Double aDouble) {
        //
    }

    @NotNull
    @Override
    public Component getDescription() {
        return MiniMessage.miniMessage().deserialize(("<color:#ffa213>You can be revived"));
    }

    @NotNull
    @Override
    public Material getIcon() {
        return Material.TOTEM_OF_UNDYING;
    }

    @NotNull
    @Override
    public String getName() {
        return "Revive";
    }

    @Override
    public void enterArena(@NotNull Player player) {
        player.getInventory().setItem(EquipmentSlot.OFF_HAND, new ItemStack(Material.TOTEM_OF_UNDYING));
    }
}
