package com.crystaelix.simurail.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import net.createmod.catnip.config.ConfigBase;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class SimurailConfig {

	public static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

	//private static SimurailCommonConfig common = new SimurailCommonConfig();
	private static SimurailServerConfig server = new SimurailServerConfig();
	private static SimurailClientConfig client = new SimurailClientConfig();

	public static SimurailServerConfig server() {
		return server;
	}

	public static SimurailClientConfig client() {
		return client;
	}

	public static ConfigBase byType(ModConfig.Type type) {
		return CONFIGS.get(type);
	}

	private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
		Pair<T, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(builder -> {
			T config = factory.get();
			config.registerAll(builder);
			return config;
		});
		T config = specPair.getLeft();
		config.specification = specPair.getRight();
		CONFIGS.put(side, config);
		return config;
	}

	public static void register(ModContainer container) {
		server = register(SimurailServerConfig::new, ModConfig.Type.SERVER);
		client = register(SimurailClientConfig::new, ModConfig.Type.CLIENT);

		for(Map.Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet()) {
			container.registerConfig(pair.getKey(), pair.getValue().specification);
		}
	}
}
