package io.github.bananamod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bananamod.init.BlockInit;
import io.github.bananamod.init.ItemInit;
import io.github.bananamod.init.SoundInit;

public class BananaMod implements ModInitializer {
	public static final String MOD_ID = "bananamod";
    public static final Logger LOGGER = LoggerFactory.getLogger("bananamod");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Mumbo for Mayor!");
		
		ItemInit.registerModItems();
		BlockInit.registerModBlocks();
		SoundInit.registerModSounds();
		
	}
}