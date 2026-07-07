package com.crystaelix.simurail.content.bogey;

import java.util.function.Supplier;

import com.simibubi.create.AllSoundEvents;

import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.companion.math.JOMLConversion;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.animation.LerpedFloat.Chaser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

public class PhysicsBogeySounds {

	protected final PhysicsBogeyBlockEntity bogey;

	protected LerpedFloat speedFactor = LerpedFloat.linear();
	protected LerpedFloat distanceFactor = LerpedFloat.linear();

	protected PhysicsBogeySoundInstance train1;
	//protected PhysicsBogeySoundInstance train2;

	protected PhysicsBogeySounds(PhysicsBogeyBlockEntity bogey) {
		this.bogey = bogey;
	}

	public void tick() {
		Minecraft mc = Minecraft.getInstance();
		float speed = bogey.movementSpeed;
		float distance = (float)Math.sqrt(Sable.HELPER.distanceSquaredWithSubLevels(bogey.getLevel(), bogey.localCenter, JOMLConversion.toJOML(mc.player.getEyePosition())));

		speedFactor.chase(Math.abs(speed) * 0.05, 0.25, Chaser.exp(0.05));
		distanceFactor.chase(Mth.clampedLerp(100, 0, (distance - 3) / 32), 0.25, Chaser.exp(50));
		speedFactor.tickChaser();
		distanceFactor.tickChaser();

		train1 = playIfMissing(mc, train1, AllSoundEvents.TRAIN::getMainEvent);
		//train2 = playIfMissing(mc, train2, bogey.options.type::soundEvent);

		float volume = Math.min(speedFactor.getValue(), distanceFactor.getValue() * 0.01F) * 0.5F;
		float pitch1 = Mth.clamp(speedFactor.getValue() + 0.25F, 0.5F, 1.25F);
		//float pitch2 = Mth.clamp(speedFactor.getValue(), 0.5F, 1.5F);

		train1.setPitch(pitch1);
		train1.setVolume(volume);
		//train2.setPitch(pitch2);
		//train2.setVolume(volume);
	}

	private PhysicsBogeySoundInstance playIfMissing(Minecraft mc, PhysicsBogeySoundInstance soundInstance, Supplier<SoundEvent> soundSupplier) {
		if(soundInstance != null && soundInstance.isStopped()) {
			mc.getSoundManager().stop(soundInstance);
		}
		if(soundInstance == null || soundInstance.isStopped()) {
			soundInstance = new PhysicsBogeySoundInstance(soundSupplier);
			mc.getSoundManager().play(soundInstance);
		}
		return soundInstance;
	}

	public void stop() {
		Minecraft mc = Minecraft.getInstance();
		if(train1 != null) {
			mc.getSoundManager().stop(train1);
		}
	}

	protected class PhysicsBogeySoundInstance extends AbstractTickableSoundInstance {

		protected final Supplier<SoundEvent> soundSupplier;
		protected final SoundEvent sound;

		protected PhysicsBogeySoundInstance(Supplier<SoundEvent> soundSupplier) {
			super(soundSupplier.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
			this.soundSupplier = soundSupplier;
			this.sound = soundSupplier.get();
			this.attenuation = Attenuation.LINEAR;
			this.looping = true;
			this.delay = 0;
			this.volume = 0;
			x = bogey.localCenter.x();
			y = bogey.localCenter.y();
			z = bogey.localCenter.z();
		}

		@Override
		public boolean canPlaySound() {
			return true;
		}

		@Override
		public boolean canStartSilent() {
			return true;
		}

		@Override
		public void tick() {
			if(soundSupplier.get() != sound || bogey.isRemoved()) {
				stop();
			}
		}

		public void setPitch(float pitch) {
			this.pitch = pitch;
		}

		public void setVolume(float volume) {
			this.volume = volume;
		}
	}
}
