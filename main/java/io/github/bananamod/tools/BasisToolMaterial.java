package io.github.bananamod.tools;

import com.google.common.base.Supplier;

import io.github.bananamod.init.ItemInit;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

@SuppressWarnings("deprecation")
public enum BasisToolMaterial implements ToolMaterial {

	BANANA("banana", 5, 4062, 35, 5, 35, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ItemInit.PERFECT_BANANA_PEEL});
    });
	
	@SuppressWarnings("unused")
	private final String Name;
	private final float AttackDamage;
	private final int Durability;
	private final int Enchantability;
	private final int MiningLevel;
	private final float MiningSpeed;
	private final Lazy<Ingredient> repairIngredientSupplier;
	
	private BasisToolMaterial(String Name, float AttackDamage, int Durability, int Enchantability, int MiningLevel, float MiningSpeed, Supplier<Ingredient> repairIngredientSupplier) {
		this.Name = Name;
		this.AttackDamage = AttackDamage;
		this.Durability = Durability;
		this.Enchantability = Enchantability;
		this.MiningLevel = MiningLevel;
		this.MiningSpeed = MiningSpeed;
		this.repairIngredientSupplier = new Lazy<Ingredient>(repairIngredientSupplier);
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
		return this.repairIngredientSupplier.get();
	}

}
