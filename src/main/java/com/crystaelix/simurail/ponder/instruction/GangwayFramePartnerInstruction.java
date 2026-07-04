package com.crystaelix.simurail.ponder.instruction;

import com.crystaelix.simurail.content.gangway_frame.GangwayFrame;

import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import net.minecraft.core.BlockPos;

public class GangwayFramePartnerInstruction extends PonderInstruction {

	protected final BlockPos pos;
	protected final BlockPos partner;

	public GangwayFramePartnerInstruction(BlockPos pos, BlockPos partner) {
		this.pos = pos;
		this.partner = partner;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		PonderLevel world = scene.getWorld();
		if(world.getBlockEntity(pos) instanceof GangwayFrame be) {
			be.setGangwayPartnerReverse(partner);
		}
	}
}
