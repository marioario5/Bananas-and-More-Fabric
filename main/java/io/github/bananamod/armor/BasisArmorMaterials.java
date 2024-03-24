package io.github.bananamod.armor;

import com.google.common.base.Supplier;

import io.github.bananamod.init.ItemInit;
import io.github.bananamod.init.SoundInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;

@SuppressWarnings("deprecation")
public enum BasisArmorMaterials implements ArmorMaterial {

	BANANA("banana", 1, new int[]{4, 9, 7, 4}, 15, SoundInit.BANANA_ARMOR_EQUIP, 3.0F, 1.0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ItemInit.PERFECT_BANANA_PEEL});
    });
	
	
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    
	private final Lazy<Ingredient> repairIngredientSupplier;

	private BasisArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<Ingredient>(repairIngredientSupplier);
    }

    
    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }


	public int getDurability(Type type) {
		if(type.getEquipmentSlot() == EquipmentSlot.HEAD) {
			return BASE_DURABILITY[0] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.CHEST) {
			return BASE_DURABILITY[1] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.LEGS) {
			return BASE_DURABILITY[2] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.FEET) {
			return BASE_DURABILITY[3] * this.durabilityMultiplier;
		}else {
			return 0;
		}
	}

	public int getProtection(Type type) {
		if(type.getEquipmentSlot() == EquipmentSlot.HEAD) {
			return protectionAmounts[0] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.CHEST) {
			return protectionAmounts[1] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.LEGS) {
			return protectionAmounts[2] * this.durabilityMultiplier;
		} else if(type.getEquipmentSlot() == EquipmentSlot.FEET) {
			return protectionAmounts[3] * this.durabilityMultiplier;
		}else {
			return 0;
		}
	}
}


