package com.gkoliver.bannerunlimited;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BannerUnlimitedConfigs {
    public static class Configuration {
        public final ForgeConfigSpec.ConfigValue<Integer> MAX_AMOUNT;
        public Configuration(ForgeConfigSpec.Builder builderIn) {
            builderIn.comment("Banner Unlimited Configuration")
                    .push("main");
            MAX_AMOUNT = builderIn.comment("Having it be fully infinity is hard, so instead it's an arbitrarily high value.")
                    .define("amount", 65536);
            builderIn.pop(1);
        }
    }
    public static final ForgeConfigSpec CONFIGSPEC;
    public static final Configuration CONFIG;
    static {
        final Pair<Configuration, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Configuration::new);
        CONFIGSPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
}
