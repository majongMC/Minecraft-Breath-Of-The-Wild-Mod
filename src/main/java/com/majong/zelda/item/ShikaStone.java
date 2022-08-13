package com.majong.zelda.item;

import java.util.List;

import javax.annotation.Nullable;

import com.majong.zelda.Utils;
import com.majong.zelda.gui.OpenShikaStoneGui;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShikaStone extends Item{
	private int soundremaintime=0;
	public static double delta=180;
	public static Component name=Component.translatable("tooltip.zelda.temple");
	private boolean twice=false;
	public ShikaStone() {
		super(new Properties().tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO �Զ����ɵĹ��캯�����
	}
	@OnlyIn(Dist.CLIENT)
	@Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if(isSelected&&world.isClientSide&&entity instanceof Player) {
			CompoundTag camera =itemStack.getOrCreateTagElement("camera");
			if(camera.contains("target_block")) {
				Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
				name=block.getName();
			}else {
				name=Component.translatable("tooltip.zelda.temple");
			}
			float yaw=((Player)entity).yHeadRot;
			float pitch=((Player)entity).xRotO;
			//entity.sendMessage(new TranslationTextComponent("pitch"+pitch), UUID.randomUUID());
			while(yaw<0)
				yaw+=360;
			while(yaw>360)
				yaw-=360;
			CompoundTag nbt = itemStack.getOrCreateTagElement("target_location");
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
			else {
				double deltay=entity.getY()+1.5-nbt.getInt("posY");
				double targetpitch=Math.atan(deltay/Math.sqrt(rx*rx+rz*rz))*180/Math.PI;
				double vertical=Math.tan(Math.PI*Math.abs(targetpitch-pitch)/180);
				if(Math.cos(Math.PI*Math.abs(yaw-targetyaw)/180)>0) {
					double horizontal=Math.tan(Math.PI*Math.abs(yaw-targetyaw)/180);
					delta=Math.atan(Math.sqrt(vertical*vertical+horizontal*horizontal))*180/Math.PI;
				}else if(Math.cos(Math.PI*Math.abs(yaw-targetyaw)/180)<0) {
					double horizontal=Math.tan(Math.PI*Math.abs(yaw-targetyaw)/180);
					delta=180-Math.atan(Math.sqrt(vertical*vertical+horizontal*horizontal))*180/Math.PI;
				}else {
					delta=90;
				}
			}
			if(delta<90||delta>270) {
				if(delta<5||delta>355) {
					if(soundremaintime<=0) {
						world.playSound((Player)entity, entity.blockPosition(), SoundLoader.RADAR.get(), SoundSource.AMBIENT, 10f, 1f);
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
						world.playSound((Player)entity, entity.blockPosition(), SoundLoader.RADAR.get(), SoundSource.AMBIENT, 10f, 1f);
						soundremaintime=(int) (20+delta/2);
					}
				}
				soundremaintime--;
			}
		}
	}
	@Override
	public InteractionResult useOn(UseOnContext p_195939_1_) {
		Level world = p_195939_1_.getLevel();
	      if (world.isClientSide) {
	         return super.useOn(p_195939_1_);
	      } else {
	    	  ItemStack itemStack = p_195939_1_.getItemInHand();
	          CompoundTag camera =itemStack.getOrCreateTagElement("camera");
	          if(camera.contains("activated")&&camera.getBoolean("activated")) {
	        	  BlockPos blockpos = p_195939_1_.getClickedPos();
	        	  Block block=world.getBlockState(blockpos).getBlock();
	        	  if(block!=null) {
	        		camera.putString("target_block", Registry.BLOCK.getKey(block).toString());
	        	  	p_195939_1_.getPlayer().sendSystemMessage(Component.translatable("msg.zelda.blocksaved"));
	        	  }else {
	        		  p_195939_1_.getPlayer().sendSystemMessage(Component.translatable("msg.zelda.blocksavedfailed"));
	        	  }
	        	  camera.putBoolean("activated", false);
	        	  return InteractionResult.SUCCESS;
			}
	    	  return super.useOn(p_195939_1_);
	      }
	}
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(!worldIn.isClientSide()){
            ItemStack itemStack = playerIn.getItemInHand(handIn);
            CompoundTag camera =itemStack.getOrCreateTagElement("camera");
            if(playerIn.isShiftKeyDown()) {
            	camera.remove("target_block");
            }
            camera.putBoolean("activated", false);
            if(camera.contains("target_block")) {
            	Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
            	BlockPos pos=LocateTargetBlock(worldIn,playerIn.blockPosition(),block);
            	if(pos!=null) {
            		playerIn.sendSystemMessage(Component.translatable("msg.zelda.blockfound"));
            		CompoundTag nbt = itemStack.getOrCreateTagElement("target_location");
            		nbt.putInt("posX", pos.getX());
                    nbt.putInt("posY", pos.getY());
                    nbt.putInt("posZ", pos.getZ());
            	}else {
            		playerIn.sendSystemMessage(Component.translatable("msg.zelda.blockfoundfailed"));
            	}
            	return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
            }
            CompoundTag nbt = itemStack.getOrCreateTagElement("target_location");
                BlockPos blockpos = ((ServerLevel) worldIn).findNearestMapStructure(ModStructures.ZELDA_TEMPLE, playerIn.blockPosition(), 100, false);
                if(blockpos!=null){
                    nbt.putInt("posX", blockpos.getX());
                    nbt.putInt("posY", -1);
                    nbt.putInt("posZ", blockpos.getZ());
            }
        }
		if(worldIn.isClientSide)
			new OpenShikaStoneGui();
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
	}
	@OnlyIn(Dist.CLIENT)
	@Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, worldIn, tooltip, flag);
		CompoundTag camera =stack.getOrCreateTagElement("camera");
		if(camera.contains("target_block")) {
			tooltip.add(Component.translatable("tooltip.shikastone.researchtarget"));
			Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
			tooltip.add(block.getName());
			tooltip.add(Component.translatable("tooltip.shikastone.clear"));
		}
	}
	private BlockPos LocateTargetBlock(Level worldIn,BlockPos basepos,Block targetblock) {
		int basex=basepos.getX();
		int basez=basepos.getZ();
		for(int x=basex-8;x<basex+8;x++)
			for(int z=basez-8;z<basez+8;z++)
				for(int y=-64;y<319;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					Block block=worldIn.getBlockState(pos).getBlock();
					if(block==targetblock)
						return pos;
				}
		return null;
	}
}
