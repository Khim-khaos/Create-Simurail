package com.crystaelix.simurail.content.automatic_coupler;

import net.createmod.catnip.gui.AbstractSimiScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class AutomaticCouplerBaseScreen extends AbstractSimiScreen implements MenuAccess<AutomaticCouplerMenu> {

	protected AutomaticCouplerMenu menu;

	public AutomaticCouplerBaseScreen(AutomaticCouplerMenu menu, Component title) {
		super(title);
		this.menu = menu;
	}

	@Override
	public AutomaticCouplerMenu getMenu() {
		return menu;
	}

	public static AutomaticCouplerBaseScreen create(AutomaticCouplerMenu menu, Inventory inv, Component title) {
		//if(menu.gangway && menu.gangwayShape != GangwayFrameShape.NONE) {
		//	return new AutomaticCouplerGangwayScreen(menu, title);
		//}
		//else {
		//	return new AutomaticCouplerScreen(menu, title);
		//}
		return new AutomaticCouplerGangwayScreen(menu, title);
	}
}
