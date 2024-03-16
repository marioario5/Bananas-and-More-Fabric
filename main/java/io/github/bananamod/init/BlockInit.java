package io.github.bananamod.init;

import io.github.bananamod.BananaMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class BlockInit {
	
	  public static final Block BANANA_BLOCK = registerBlock("banana_block",
	            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)));
	  
	  public static final Block BANANA_ORE = registerBlock("banana_ore",
	            new ExperienceDroppingBlock(UniformIntProvider.create(2, 6),
	                    FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength(70, 1200).requiresTool()));
	  
	  public static final Block DEEPSLATE_BANANA_ORE = registerBlock("deepslate_banana_ore",
	            new ExperienceDroppingBlock(UniformIntProvider.create(2, 6),
	                    FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).strength(75, 1200).requiresTool()));
	  
	  
	  
	private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(BananaMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(BananaMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks() {
        BananaMod.LOGGER.info("Registering ModBlocks for " + BananaMod.MOD_ID);
    }
}
