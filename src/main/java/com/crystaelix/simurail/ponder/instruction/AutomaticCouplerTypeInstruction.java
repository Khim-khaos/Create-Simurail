package com.crystaelix.simurail.ponder.instruction;

import com.crystaelix.simurail.content.automatic_coupler.AutomaticCouplerBlockEntity;

import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import net.minecraft.core.BlockPos;

public class AutomaticCouplerTypeInstruction extends PonderInstruction {

	protected final BlockPos pos;

	public AutomaticCouplerTypeInstruction(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		PonderLevel world = scene.getWorld();
		if(world.getBlockEntity(pos) instanceof AutomaticCouplerBlockEntity be) {
			be.cycleType();
		}
	}
}
