package com.kingrealzyt.swords.swordeffects;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.*;
import net.minecraft.tags.ITag;
import net.minecraft.util.*;
import net.minecraft.world.World;;

public class EnderSword extends SwordItem {



    public EnderSword(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.inventory.contains(new ItemStack(Items.ENDER_PEARL))) {
            if (!playerIn.getCooldowns().isOnCooldown(this)) {
                ThrowableEntity enderPearl = new EnderPearlEntity(worldIn, 0, 0, 0);
                enderPearl.setPos(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
                enderPearl.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1F);
                playerIn.level.addFreshEntity(enderPearl);
                enderPearl.setOwner(playerIn);
                PlayerInventory inv = playerIn.inventory;
                if (!playerIn.isCreative()) {
                    for (int i = 0; i < inv.getContainerSize(); i++) {
                        if (inv.getItem(i).getItem().equals(Items.ENDER_PEARL)) {
                            inv.removeItem(i, 1);
                        }
                    }
                }
                playerIn.getCooldowns().addCooldown(this, 60);
                return ActionResult.success(playerIn.getItemInHand(handIn));
            }
        }
        return ActionResult.fail(playerIn.getItemInHand(handIn));
    }
}
