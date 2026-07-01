package com.crystaelix.simurail.content.bogey;

import net.createmod.catnip.gui.AbstractSimiScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class PhysicsBogeyBaseScreen extends AbstractSimiScreen implements MenuAccess<PhysicsBogeyMenu> {

	protected PhysicsBogeyMenu menu;

	public PhysicsBogeyBaseScreen(PhysicsBogeyMenu menu, Component title) {
		super(title);
		this.menu = menu;
	}

	@Override
	public PhysicsBogeyMenu getMenu() {
		return menu;
	}

	public static PhysicsBogeyBaseScreen create(PhysicsBogeyMenu menu, Inventory inv, Component title) {
		if(menu.secondary) {
			return new PhysicsBogeyMenuScreen(menu, title);
		}
		else {
			return new PhysicsBogeyOptionsScreen(menu, title);
		}
	}
}
