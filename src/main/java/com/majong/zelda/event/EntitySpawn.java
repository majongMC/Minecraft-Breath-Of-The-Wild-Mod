package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;
import com.majong.zelda.entity.WalkingGuardianEntity;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EntitySpawn {
	@SubscribeEvent
	public static void onLivingSpawn(SpecialSpawn event) {
		if(!event.getWorld().isClientSide()) {
			if(Math.random()<0.03*ZeldaConfig.GUARDIAN.get()&&event.getWorld().getDifficulty()!=Difficulty.PEACEFUL&&!(event.getEntity() instanceof GuardianEntity)&&((World) event.getWorld()).dimension().location().equals(DimensionType.OVERWORLD_EFFECTS)) {
				GuardianEntity entity=new GuardianEntity(EntityLoader.GUARDIAN.get(),(World) event.getWorld());
				BlockPos pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
				int count=0;
				while(!(event.getWorld().getBlockState(pos).getBlock()==Blocks.AIR&&!isabovewater(pos,(World) event.getWorld()))) {
					pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
					count++;
					if(count>10) {
						break;
					}
				}
				entity.setPos(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
				event.getWorld().addFreshEntity(entity);
			}
			if(Math.random()<0.03*ZeldaConfig.POKBRIN.get()&&event.getWorld().getDifficulty()!=Difficulty.PEACEFUL&&!(event.getEntity() instanceof PokBrinEntity)) {
				PokBrinEntity entity=new PokBrinEntity(EntityLoader.POK_BRIN.get(),(World) event.getWorld());
				BlockPos pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
				int count=0;
				while(!(event.getWorld().getBlockState(pos).getBlock()==Blocks.AIR&&!isabovewater(pos,(World) event.getWorld()))) {
					pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
					count++;
					if(count>10) {
						break;
					}
				}
				entity.setPos(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
				event.getWorld().addFreshEntity(entity);
			}
			if(Math.random()<0.015*ZeldaConfig.WALKING_GUARDIAN.get()&&event.getWorld().getDifficulty()!=Difficulty.PEACEFUL&&!(event.getEntity() instanceof GuardianEntity&&((World) event.getWorld()).dimension().location().equals(DimensionType.OVERWORLD_EFFECTS))) {
				GuardianEntity entity=new WalkingGuardianEntity(EntityLoader.WALKING_GUARDIAN.get(),(World) event.getWorld());
				BlockPos pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
				int count=0;
				while(!(event.getWorld().getBlockState(pos).getBlock()==Blocks.AIR&&!isabovewater(pos,(World) event.getWorld()))) {
					pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
					count++;
					if(count>10) {
						break;
					}
				}
				entity.setPos(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
				event.getWorld().addFreshEntity(entity);
			}
			if(Math.random()<0.03*ZeldaConfig.MOLLYBRIN.get()&&event.getWorld().getDifficulty()!=Difficulty.PEACEFUL&&!(event.getEntity() instanceof MollyBrinEntity&&((World) event.getWorld()).dimension().location().equals(DimensionType.OVERWORLD_EFFECTS))) {
				BlockPos pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
				int count=0;
				while(!(event.getWorld().getBlockState(pos).getBlock()==Blocks.AIR&&!isabovewater(pos,(World) event.getWorld()))) {
					pos=new BlockPos(event.getX()+Math.random()*16-8,event.getY()+1,event.getZ()+Math.random()*16-8);
					count++;
					if(count>10) {
						break;
					}
				}
				if(event.getWorld().getMaxLocalRawBrightness(pos)>7)
					return;
				MollyBrinEntity entity=new MollyBrinEntity(EntityLoader.MOLLY_BRIN.get(),(World) event.getWorld());
				entity.setItemInHand(Hand.MAIN_HAND,new ItemStack(Items.BOW,1));
				entity.setPos(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
				event.getWorld().addFreshEntity(entity);
			}
		}
	}
	private static boolean isabovewater(BlockPos pos,World world) {
		int i=0;
		while(true) {
			if(pos.offset(0, i, 0).getY()<=0) {
				return true;
			}
			if(world.getBlockState(pos.offset(0, i, 0)).getBlock()==Blocks.WATER)
				return true;
			else if(world.getBlockState(pos.offset(0, i, 0)).getBlock()!=Blocks.AIR)
				return false;
			i--;
		}
	}
}
