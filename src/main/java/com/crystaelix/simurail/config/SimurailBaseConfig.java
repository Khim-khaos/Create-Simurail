package com.crystaelix.simurail.config;

import java.util.function.Supplier;

import net.createmod.catnip.config.ConfigBase;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;

public abstract class SimurailBaseConfig extends ConfigBase {

	protected <T extends SimurailBaseConfig> T nested(int depth, Supplier<T> constructor, String comment) {
		T config = constructor.get();
		new ConfigGroup(config.getName(), depth, comment);
		new CValue<Boolean, BooleanValue>(config.getName(), builder -> {
			config.depth = depth;
			config.registerAll(builder);
			if(config.depth > this.depth) {
				builder.pop(config.depth - this.depth);
			}
			return null;
		});
		children.add(config);
		return config;
	}
}
