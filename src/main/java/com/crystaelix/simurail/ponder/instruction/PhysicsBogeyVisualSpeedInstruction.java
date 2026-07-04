package com.crystaelix.simurail.ponder.instruction;

import com.crystaelix.simurail.content.bogey.PhysicsBogeyBlockEntity;

import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import net.minecraft.core.BlockPos;

public class PhysicsBogeyVisualSpeedInstruction extends PonderInstruction {

	protected final BlockPos pos;
	protected final double visualSpeed;

	public PhysicsBogeyVisualSpeedInstruction(BlockPos pos, double visualSpeed) {
		this.pos = pos;
		this.visualSpeed = visualSpeed;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		PonderLevel world = scene.getWorld();
		if(world.getBlockEntity(pos) instanceof PhysicsBogeyBlockEntity be) {
			be.visualSpeed = visualSpeed;
		}
	}
}
