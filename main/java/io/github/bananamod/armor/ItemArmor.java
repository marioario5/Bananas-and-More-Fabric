package io.github.bananamod.armor;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArmor extends ArmorItem{

	private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(BasisArmorMaterials.BANANA,
                            new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 1)).build();
	
	public ItemArmor(ArmorMaterial material, Type type, Settings settings) {
		super(material, type, settings);
	}
	
	@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entity;

                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
	
	private void evaluateArmorEffects(PlayerEntity player) {
        for (@SuppressWarnings("unused") Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = BasisArmorMaterials.BANANA;
            StatusEffectInstance mapStatusEffect = new StatusEffectInstance(StatusEffects.RESISTANCE,
                    400, 1, false, false, false);

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
                    400, 1, false, false, false));
        }

        // effect repeat issue fix
        if (player.getActiveStatusEffects().containsKey(StatusEffects.RESISTANCE)) {
            if (player.getActiveStatusEffects().get(mapStatusEffect.getEffectType()).getDuration() < 221) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
                        400, 1, false, false, false));
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean doesArmorHaveMaterial(ArmorMaterial material, PlayerEntity player) {
        for (int i=0; i<4; i++) {
            try{
                ((ArmorItem)player.getInventory().getArmorStack(i).getItem()).getMaterial();
            }catch(Exception e){
                return false;
            }
        }

        return true;
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        if (!doesArmorHaveMaterial(material, player)) {
            return false;
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

}
