package com.github.palomox.definitive_weapon;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DefinitiveWeaponMod.MODID)
public class DefinitiveWeaponMod {
    public static final String MODID = "definitive_weapon";
    public static final Tier DEFINITIVE = new SimpleTier(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 3000, 0f, 1000F, 15, () -> Ingredient.of(Items.STICK));
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<SwordItem> GOTELE_STICK = ITEMS.register("gotele_stick", () -> new SwordItem(
    		DEFINITIVE, 
    		new Item.Properties()
    		.setNoRepair()
    		.attributes(
    				SwordItem.createAttributes(DEFINITIVE, 3, -2.4f))));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEF_WEAPON_TAB = CREATIVE_MODE_TABS.register("definitive_weapon_main", () -> CreativeModeTab.builder()
            .title(Component.translatable(MODID+".tab")) 
            .icon(() -> GOTELE_STICK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GOTELE_STICK.get()); 
            }).build());

    public DefinitiveWeaponMod(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

}
