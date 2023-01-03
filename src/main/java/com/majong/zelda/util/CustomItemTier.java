package com.majong.zelda.util;

import java.util.function.Supplier;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum CustomItemTier implements Tier{
	BEASTGOD(15, 1561, 8.0F, 3F, 12, () -> {
      return Ingredient.EMPTY;
   });
   private final int level;
   private final int uses;
   private final float speed;
   private final float damage;
   private final int enchantmentValue;
   private final LazyLoadedValue<Ingredient> repairIngredient;
   private CustomItemTier(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
      this.level = p_43332_;
      this.uses = p_43333_;
      this.speed = p_43334_;
      this.damage = p_43335_;
      this.enchantmentValue = p_43336_;
      this.repairIngredient = new LazyLoadedValue<>(p_43337_);
   }

   public int getUses() {
      return this.uses;
   }

   public float getSpeed() {
      return this.speed;
   }

   public float getAttackDamageBonus() {
      return this.damage;
   }

   public int getLevel() {
      return this.level;
   }

   public int getEnchantmentValue() {
      return this.enchantmentValue;
   }

   public Ingredient getRepairIngredient() {
      return this.repairIngredient.get();
   }
}
