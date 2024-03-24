package io.github.bananamod.init;

import io.github.bananamod.BananaMod;
import io.github.bananamod.armor.BasisArmorMaterials;
import io.github.bananamod.armor.ItemArmor;
import io.github.bananamod.tools.BasisToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.AxeItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;



public class ItemInit{
	
	public static final Item PERFECT_BANANA = registerItem("perfect_banana", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).food(new FoodComponent.Builder()
			.hunger(10).saturationModifier(2.5F).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 6000, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 6000, 2), 1.0F).build())));
	public static final Item BANANA = registerItem("banana", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).food(new FoodComponent.Builder()
			.hunger(3).saturationModifier(7F).build())));
	
	public static final Item PERFECT_BANANA_PEEL = registerItem("perfect_banana_peel", new Item(new FabricItemSettings().rarity(Rarity.RARE)));
	public static final Item HEART_OF_BANANA = registerItem("heart_of_banana", new Item(new FabricItemSettings().rarity(Rarity.RARE)));
	
	public static final Item BANANA_SWORD = registerItem("banana_sword", new SwordItem(BasisToolMaterial.BANANA, 2, -1.5F, new FabricItemSettings()));
	public static final Item BANANA_PICKAXE = registerItem("banana_pickaxe", new PickaxeItem(BasisToolMaterial.BANANA, -2, -2.4F, new FabricItemSettings()));
	public static final Item BANANA_AXE = registerItem("banana_axe", new AxeItem(BasisToolMaterial.BANANA, 2, -1.5F, new FabricItemSettings()));
	public static final Item BANANA_SHOVEL = registerItem("banana_shovel", new ShovelItem(BasisToolMaterial.BANANA, 4, -2.9F, new FabricItemSettings()));
	public static final Item BANANA_HOE = registerItem("banana_hoe", new HoeItem(BasisToolMaterial.BANANA, -2, -2.5F, new FabricItemSettings()));
	
	 public static final Item BANANA_HELMET = registerItem("banana_helmet",
	            new ItemArmor(BasisArmorMaterials.BANANA, Type.HELMET, new FabricItemSettings()));
	 
	 public static final Item BANANA_CHESTPLATE = registerItem("banana_chestplate",
	            new ItemArmor(BasisArmorMaterials.BANANA, Type.CHESTPLATE, new FabricItemSettings()));
	 
	 public static final Item BANANA_LEGGINGS = registerItem("banana_leggings",
	            new ItemArmor(BasisArmorMaterials.BANANA, Type.LEGGINGS, new FabricItemSettings()));
	 
	 public static final Item BANANA_BOOTS = registerItem("banana_boots",
	            new ItemArmor(BasisArmorMaterials.BANANA, Type.BOOTS, new FabricItemSettings()));
	
	 private static Item registerItem(String name, Item item) {
	        return Registry.register(Registries.ITEM, new Identifier(BananaMod.MOD_ID, name), item);
	  }
	 
	 public static void registerModItems() {
	        BananaMod.LOGGER.info("Registering Mod Items for " + BananaMod.MOD_ID);
	 }
}
