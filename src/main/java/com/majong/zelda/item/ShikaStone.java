package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.gui.ShikaStoneGui;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ShikaStone extends Item{
	int soundremaintime=0;
	public static double delta=180;
	boolean twice=false;
	public ShikaStone() {
		super(new Properties().tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO 自动生成的构造函数存根
	}
	@Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(isSelected&&world.isClientSide&&entity instanceof PlayerEntity) {
			float yaw=((PlayerEntity)entity).yHeadRot;
			float pitch=((PlayerEntity)entity).xRot;
			//entity.sendMessage(new TranslationTextComponent("pitch"+pitch), UUID.randomUUID());
			while(yaw<0)
				yaw+=360;
			while(yaw>360)
				yaw-=360;
			CompoundNBT nbt = itemStack.getOrCreateTagElement("target_location");
			double rx,rz,targetyaw;
			if(!nbt.contains("posX"))
				return;
			rx=nbt.getInt("posX")-entity.getX();
			rz=nbt.getInt("posZ")-entity.getZ();
			if(rx<0)
				targetyaw=Math.atan(rz/rx)*180/Math.PI+90;
			 else
				targetyaw=(Math.atan(rz/rx)*180/Math.PI+180+90);
			if(nbt.getInt("posY")==-1)
				delta=Math.abs(yaw-targetyaw);
			if(delta<90||delta>270) {
				if(delta<5||delta>355) {
					if(soundremaintime<=0) {
						world.playSound((PlayerEntity)entity, entity.blockPosition(), SoundLoader.RADAR.get(), SoundCategory.AMBIENT, 10f, 1f);
						if(twice) {
							soundremaintime=20;
							twice=false;
						}else {
							soundremaintime=7;
							twice=true;
						}
					}
				}else {
					if(soundremaintime<=0) {
						world.playSound((PlayerEntity)entity, entity.blockPosition(), SoundLoader.RADAR.get(), SoundCategory.AMBIENT, 10f, 1f);
						soundremaintime=(int) (20+delta/2);
					}
				}
				soundremaintime--;
			}
		}
	}
	@Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!worldIn.isClientSide()){
            ItemStack itemStack = playerIn.getItemInHand(handIn);
            CompoundNBT nbt = itemStack.getOrCreateTagElement("target_location");
                BlockPos blockpos = ((ServerWorld) worldIn).findNearestMapFeature(ModStructures.ZELDA_TEMPLE.get(), playerIn.blockPosition(), 100, false);
                if(blockpos!=null){
                    nbt.putInt("posX", blockpos.getX());
                    nbt.putInt("posY", -1);
                    nbt.putInt("posZ", blockpos.getZ());
            }
        }
		if(worldIn.isClientSide)
			Minecraft.getInstance().setScreen(new ShikaStoneGui());
		return ActionResult.pass(playerIn.getItemInHand(handIn));
	}
}
