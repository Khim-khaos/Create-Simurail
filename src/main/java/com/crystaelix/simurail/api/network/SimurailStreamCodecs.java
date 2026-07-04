package com.crystaelix.simurail.api.network;

import org.joml.Quaterniond;
import org.joml.Quaterniondc;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3f;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class SimurailStreamCodecs {

	public static final StreamCodec<ByteBuf, Vector3dc> VECTOR3D_F = ByteBufCodecs.VECTOR3F.map(
			Vector3d::new, new Vector3f()::set);
	public static final StreamCodec<ByteBuf, Quaterniondc> QUATERNIOND_F = ByteBufCodecs.QUATERNIONF.map(
			Quaterniond::new, new Quaternionf()::set);

	public static final StreamCodec<ByteBuf, BogeyStyle> BOGEY_STYLE = ResourceLocation.STREAM_CODEC.map(
			AllBogeyStyles.BOGEY_STYLES::get, s -> s.id);
	public static final StreamCodec<ByteBuf, BogeySize> BOGEY_SIZE = ResourceLocation.STREAM_CODEC.map(
			BogeySizes.all()::get, BogeySize::id);
}
