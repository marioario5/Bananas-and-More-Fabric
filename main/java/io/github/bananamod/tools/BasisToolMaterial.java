package io.github.bananamod.tools;

import com.google.common.base.Supplier;

import io.github.bananamod.init.ItemInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum BasisToolMaterial implements ToolMaterial {
	
	BANANA(5, 4062, 35, 5, 35,
            () -> Ingredient.ofItems(ItemInit.PERFECT_BANANA));
	
	private final float AttackDamage;
	private final int Durability;
	private final int Enchantability;
	private final int MiningLevel;
	private final float MiningSpeed;
	private final Supplier<Ingredient> repairIngredient;
	
	private BasisToolMaterial(float AttackDamage, int Durability, int Enchantability, int MiningLevel, float MiningSpeed, Supplier<Ingredient> repairIngredient) {
		this.AttackDamage = AttackDamage;
		this.Durability = Durability;
		this.Enchantability = Enchantability;
		this.MiningLevel = MiningLevel;
		this.MiningSpeed = MiningSpeed;
		this.repairIngredient = repairIngredient;
	}
	
	@Override
	public float getAttackDamage() {
		return this.AttackDamage;
	}

	@Override
	public int getDurability() {
		return this.Durability;
	}

	@Override
	public int getEnchantability() {
		return this.Enchantability;
	}

	@Override
	public int getMiningLevel() {
		return this.MiningLevel;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.MiningSpeed;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

}
