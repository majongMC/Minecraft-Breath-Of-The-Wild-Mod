package com.majong.zelda.event;

import com.majong.zelda.entity.AncientArrowEntity;
import com.majong.zelda.entity.BombArrowEntity;
import com.majong.zelda.entity.ElectricityArrowEntity;
import com.majong.zelda.entity.FireArrowEntity;
import com.majong.zelda.entity.IceArrowEntity;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber()
public class ServerStarting {
	@SubscribeEvent
	public static void onServerAboutToStartStarting(ServerAboutToStartEvent event) {
		DispenserBlock.registerBehavior(ItemLoader.ANCIENT_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new AncientArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.ELECTRICITY_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new ElectricityArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.BOMB_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new BombArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.ICE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new IceArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.FIRE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new FireArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
	}
}
