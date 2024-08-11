package com.github.palomox.gotele;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.SimpleTier;

/**
 * The almighty gotelé stick. It hurts, but it cannot break blocks.
 */
public class GoteleStick extends SwordItem{
    public static final Tier GOTELE = new SimpleTier(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 3000, 0f, 1000F, 15, () -> Ingredient.of(Items.STICK));

	public GoteleStick() {
		super(GOTELE, 
	    		new Item.Properties()
	    		.setNoRepair()
	    		.attributes(SwordItem.createAttributes(GOTELE, 3, -2.4f)));
	}
	
	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		if (entity instanceof Player player) {
			final double reach = GoteleConfig.stickRange; 
	        Vec3 viewVec = entity.getViewVector(1.0F);
	        Vec3 eyeVec = entity.getEyePosition(1.0F);
	        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);
	        
	        AABB entityBB = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(reach, reach, reach);
	        
	        
	        EntityHitResult result = ProjectileUtil.getEntityHitResult(entity, eyeVec, targetVec, entityBB, ent -> { 
	        	if(ent instanceof LivingEntity le) {
	        		if(le instanceof Player pe) {
	        			if (pe.isCreative() || pe.isSpectator()) {
	        				return false; 
	        			}
	        		}
	        		if (le instanceof EnderDragon dragon ) {
	        			if(dragon.getHealth() == 0) {
	        				return false;
	        			}
	        		}
	        	return true;
	        	}
	        	return false;
	        }, reach);
	        
	        if (result == null) {return false;} 
	        LivingEntity target = (LivingEntity) result.getEntity(); 

	        target.hurt(entity.damageSources().playerAttack(player), (float) entity.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
		}
		return false; 
	}

}
