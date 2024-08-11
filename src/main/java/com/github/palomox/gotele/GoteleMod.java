package com.github.palomox.gotele;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(GoteleMod.MODID)
public class GoteleMod {
    public static final String MODID = "gotele";
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<SwordItem> GOTELE_STICK = ITEMS.register("gotele_stick", () -> new GoteleStick());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEF_WEAPON_TAB = CREATIVE_MODE_TABS.register("definitive_weapon_main", () -> CreativeModeTab.builder()
            .title(Component.translatable(MODID+".tab")) 
            .icon(() -> GOTELE_STICK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GOTELE_STICK.get()); 
            }).build());

    public GoteleMod(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        modContainer.registerConfig(ModConfig.Type.SERVER, GoteleConfig.PAIR.getValue());
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

}
