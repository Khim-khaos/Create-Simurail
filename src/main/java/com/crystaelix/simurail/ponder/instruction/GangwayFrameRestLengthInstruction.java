package com.crystaelix.simurail.ponder.instruction;

import com.crystaelix.simurail.content.automatic_coupler.AutomaticCouplerBlockEntity;
import com.crystaelix.simurail.content.gangway_frame.GangwayFrameBlockEntity;

import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import net.minecraft.core.BlockPos;

public class GangwayFrameRestLengthInstruction extends PonderInstruction {

	protected final BlockPos pos;
	protected final float restLength;

	public GangwayFrameRestLengthInstruction(BlockPos pos, float restLength) {
		this.pos = pos;
		this.restLength = restLength;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		PonderLevel world = scene.getWorld();
		if(world.getBlockEntity(pos) instanceof GangwayFrameBlockEntity be) {
			be.restLength = restLength;
		}
		if(world.getBlockEntity(pos) instanceof AutomaticCouplerBlockEntity be) {
			be.gangwayRestLength = restLength;
		}
	}
}
