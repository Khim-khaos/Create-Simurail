package com.crystaelix.simurail.ponder;

import com.crystaelix.simurail.Simurail;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class SimurailPonderPlugin implements PonderPlugin {

	@Override
	public String getModId() {
		return Simurail.MOD_ID;
	}

	@Override
	public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> registry) {
		SimurailPonderScenes.register(registry);
	}

	@Override
	public void registerTags(PonderTagRegistrationHelper<ResourceLocation> registry) {
		SimurailPonderTags.register(registry);
	}
}
