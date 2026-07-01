package com.crystaelix.simurail.api.coupler;

import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class CouplerType {

	public static final StreamCodec<ByteBuf, CouplerType> STREAM_CODEC = ResourceLocation.STREAM_CODEC.map(CouplerTypeRegistry::get, CouplerType::id);

	private final ResourceLocation id;
	private final Component displayName;
	private final ResourceLocation modelId;

	public CouplerType(ResourceLocation id, Component displayName, ResourceLocation modelId) {
		this.id = id;
		this.displayName = displayName;
		this.modelId = modelId;
	}

	public CouplerType(ResourceLocation id, ResourceLocation modelId) {
		this(id, Component.translatable(Util.makeDescriptionId("simurail_coupler_type", id)), modelId);
	}

	public ResourceLocation id() {
		return id;
	}

	public Component displayName() {
		return displayName;
	}

	public ResourceLocation modelId() {
		return modelId;
	}

	public boolean canConnectTo(CouplerType other) {
		return this == other;
	}
}
