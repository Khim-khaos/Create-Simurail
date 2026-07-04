package com.crystaelix.simurail.ponder.instruction;

import dev.simulated_team.simulated.content.blocks.portable_engine.PortableEngineBlockEntity;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import net.minecraft.core.BlockPos;

public class PortableEngineEnableInstruction extends PonderInstruction {

	protected final BlockPos pos;

	public PortableEngineEnableInstruction(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		PonderLevel world = scene.getWorld();
		if(world.getBlockEntity(pos) instanceof PortableEngineBlockEntity be) {
			be.setCurrentBurnTime(10);
			be.openHatchOverride = false;
		}
	}
}
