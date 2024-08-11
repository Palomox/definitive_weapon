package com.github.palomox.gotele;

import org.apache.commons.lang3.tuple.Pair;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GoteleConfig {
   
    public static final Pair<GoteleConfig, ModConfigSpec> PAIR = new ModConfigSpec.Builder().configure(GoteleConfig::new);

    ConfigValue<Double> stickRange;    
    
    
    public GoteleConfig(ModConfigSpec.Builder builder) {
        this.stickRange = builder
                .comment("The range of the gotelé stick")
                .defineInRange("stickRange", 1000D, 0D, 100000D);

    	
    }
}
