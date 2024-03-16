package io.github.bananamod.init;

import io.github.bananamod.BananaMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundInit {
	public static SoundEvent BANANA_ARMOR_EQUIP = registerSoundEvent("banana_armor_equip");
	public static final SoundEvent GORILLA_IDLE = registerSoundEvent("gorilla_idle");
	public static final SoundEvent GORILLA_DIES = registerSoundEvent("gorilla_dies");
	public static final SoundEvent GORILLA_HURTS = registerSoundEvent("gorilla_hurts");
	
	 private static SoundEvent registerSoundEvent(String name) {
	        Identifier id = new Identifier(BananaMod.MOD_ID, name);
	        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	    }
	 
	 public static void registerModSounds() {
	        BananaMod.LOGGER.info("Registering Mod Sounds for " + BananaMod.MOD_ID);
	 }
}
