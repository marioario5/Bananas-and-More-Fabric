package io.github.bananamod.init;

import io.github.bananamod.BananaMod;
import io.github.bananamod.entity.GorillaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityInit {

	public static final EntityType<GorillaEntity> GORILLA = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(BananaMod.MOD_ID, "gorilla"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, GorillaEntity::new)
			.dimensions(EntityDimensions.fixed(1.4F, 1.4F)).build());
	
}
