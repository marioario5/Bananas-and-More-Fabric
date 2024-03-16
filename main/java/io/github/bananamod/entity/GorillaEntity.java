package io.github.bananamod.entity;

import io.github.bananamod.entity.ai.GorillaAttackGoal;
import io.github.bananamod.init.SoundInit;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class GorillaEntity extends HostileEntity{

	private static final TrackedData<Boolean> ATTACKING =DataTracker.registerData(GorillaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> POUNDING =DataTracker.registerData(GorillaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;
	
	public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    
    public final AnimationState poundAnimationState = new AnimationState();
    public int poundAnimationTimeout = 0;
	
	public GorillaEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }
	
	private void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = this.random.nextInt(40) + 80;
	        this.idleAnimationState.start(this.age);
	   } else {
	       --this.idleAnimationTimeout;
	   }
		
		if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
        
        if(this.isPounding() && poundAnimationTimeout<= 0) {
        	poundAnimationTimeout = 40;
        	poundAnimationState.start(this.age);
        }
        if(!this.isPounding()) {
        	poundAnimationState.stop();
        }

	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new GorillaAttackGoal(this, 1.25f, true));
		this.goalSelector.add(2, new WanderAroundFarGoal(this, 1D));
		this.goalSelector.add(3, new LookAroundGoal(this));
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

	}
	
	public static DefaultAttributeContainer.Builder createGorillaAttributes(){
		
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 30D)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.35F)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 40D)
			.add(EntityAttributes.GENERIC_ATTACK_SPEED, 35F)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35D)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D)
			.add(EntityAttributes.GENERIC_ARMOR, 4.0D);
		
	}
	
	    public void setAttacking(boolean attacking) {
	        this.dataTracker.set(ATTACKING, attacking);
	    }

	    @Override
	    public boolean isAttacking() {
	        return this.dataTracker.get(ATTACKING);
	    }
	    
	    public void setPounding(boolean pounding) {
	    	this.dataTracker.set(POUNDING, pounding);
	    }

	    public boolean isPounding() {
	    	return this.dataTracker.get(POUNDING);
	    }
	    
	    @Override
	    protected void initDataTracker() {
	        super.initDataTracker();
	        this.dataTracker.startTracking(ATTACKING, false);
	        this.dataTracker.startTracking(POUNDING, false);
	    }

	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundInit.GORILLA_IDLE;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundInit.GORILLA_HURTS;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundInit.GORILLA_DIES;
	}

}
