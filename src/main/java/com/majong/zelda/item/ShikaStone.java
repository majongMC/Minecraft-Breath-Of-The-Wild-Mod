package com.majong.zelda.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.majong.zelda.Utils;
import com.majong.zelda.gui.OpenShikaStoneGui;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShikaStone extends Item{
	private int soundremaintime=0;
	public static double delta=180;
	public static ITextComponent name=new TranslationTextComponent("tooltip.zelda.temple");
	private boolean twice=false;
	public ShikaStone() {
		super(new Properties().tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO 自动生成的构造函数存根
	}
	@OnlyIn(Dist.CLIENT)
	@Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(isSelected&&world.isClientSide&&entity instanceof PlayerEntity) {
			CompoundNBT camera =itemStack.getOrCreateTagElement("camera");
			if(camera.contains("target_block")) {
				Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
				name=block.getName();
			}else {
				name=new TranslationTextComponent("tooltip.zelda.temple");
			}
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
			else {
				double deltay=entity.getY()+1.5-nbt.getInt("posY");
				double targetpitch=Math.atan(deltay/Math.sqrt(rx*rx+rz*rz))*180/Math.PI;
				double vertical=Math.tan(Math.PI*Math.abs(targetpitch-pitch)/180);
				double horizontal=Math.tan(Math.PI*Math.abs(yaw-targetyaw)/180);
				delta=Math.atan(Math.sqrt(vertical*vertical+horizontal*horizontal))*180/Math.PI;
			}
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
	public ActionResultType useOn(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getLevel();
	      if (!(world instanceof ServerWorld)) {
	         return super.useOn(p_195939_1_);
	      } else {
	    	  ItemStack itemStack = p_195939_1_.getItemInHand();
	          CompoundNBT camera =itemStack.getOrCreateTagElement("camera");
	          if(camera.contains("activated")&&camera.getBoolean("activated")) {
	        	  BlockPos blockpos = p_195939_1_.getClickedPos();
	        	  Block block=world.getBlockState(blockpos).getBlock();
	        	  if(block!=null) {
	        		camera.putString("target_block", block.getRegistryName().toString());
	        	  	p_195939_1_.getPlayer().sendMessage(new TranslationTextComponent("msg.zelda.blocksaved"), UUID.randomUUID());
	        	  }else {
	        		  p_195939_1_.getPlayer().sendMessage(new TranslationTextComponent("msg.zelda.blocksavedfailed"), UUID.randomUUID());
	        	  }
	        	  camera.putBoolean("activated", false);
	        	  return ActionResultType.SUCCESS;
			}
	    	  return super.useOn(p_195939_1_);
	      }
	}
	@Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!worldIn.isClientSide()){
            ItemStack itemStack = playerIn.getItemInHand(handIn);
            CompoundNBT camera =itemStack.getOrCreateTagElement("camera");
            if(playerIn.isShiftKeyDown()) {
            	camera.remove("target_block");
            }
            camera.putBoolean("activated", false);
            if(camera.contains("target_block")) {
            	Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
            	BlockPos pos=LocateTargetBlock(worldIn,playerIn.blockPosition(),block);
            	if(pos!=null) {
            		playerIn.sendMessage(new TranslationTextComponent("msg.zelda.blockfound"), UUID.randomUUID());
            		CompoundNBT nbt = itemStack.getOrCreateTagElement("target_location");
            		nbt.putInt("posX", pos.getX());
                    nbt.putInt("posY", pos.getY());
                    nbt.putInt("posZ", pos.getZ());
            	}else {
            		playerIn.sendMessage(new TranslationTextComponent("msg.zelda.blockfoundfailed"), UUID.randomUUID());
            	}
            	return ActionResult.pass(playerIn.getItemInHand(handIn));
            }
            CompoundNBT nbt = itemStack.getOrCreateTagElement("target_location");
                BlockPos blockpos = ((ServerWorld) worldIn).findNearestMapFeature(ModStructures.ZELDA_TEMPLE.get(), playerIn.blockPosition(), 100, false);
                if(blockpos!=null){
                    nbt.putInt("posX", blockpos.getX());
                    nbt.putInt("posY", -1);
                    nbt.putInt("posZ", blockpos.getZ());
            }
        }
		if(worldIn.isClientSide)
			new OpenShikaStoneGui();
		return ActionResult.pass(playerIn.getItemInHand(handIn));
	}
	@OnlyIn(Dist.CLIENT)
	@Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, worldIn, tooltip, flag);
		CompoundNBT camera =stack.getOrCreateTagElement("camera");
		if(camera.contains("target_block")) {
			tooltip.add(new TranslationTextComponent("tooltip.shikastone.researchtarget"));
			Block block=Registry.BLOCK.get(new ResourceLocation(camera.getString("target_block")));
			tooltip.add(block.getName());
			tooltip.add(new TranslationTextComponent("tooltip.shikastone.clear"));
		}
	}
	private BlockPos LocateTargetBlock(World worldIn,BlockPos basepos,Block targetblock) {
		int basex=basepos.getX();
		int basez=basepos.getZ();
		for(int x=basex-8;x<basex+8;x++)
			for(int z=basez-8;z<basez+8;z++)
				for(int y=0;y<255;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					Block block=worldIn.getBlockState(pos).getBlock();
					if(block==targetblock)
						return pos;
				}
		return null;
	}
}
