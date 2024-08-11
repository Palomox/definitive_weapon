package com.github.palomox.gotele;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = GoteleMod.MODID, bus= Bus.MOD)
public class GoteleConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue STICK_RANGE = BUILDER
            .comment("The range of the gotelé stick")
            .defineInRange("stickRange", 1000D, 0D, 100000D);
    
    static final ModConfigSpec SPEC = BUILDER.build();
    
    public static double stickRange;
    
    @SubscribeEvent
    public static void loadConfig(ModConfigEvent evt) {
    	stickRange = STICK_RANGE.get();
    }
}
