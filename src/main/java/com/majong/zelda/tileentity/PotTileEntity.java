package com.majong.zelda.tileentity;

import javax.annotation.Nullable;

import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.GuiMessagePack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.network.PacketDistributor;

public class PotTileEntity extends TileEntity implements ITickableTileEntity{
	private ItemStack slot[]=new ItemStack[5];
	private PlayerEntity user;
	private boolean using=false;
	private int process=0;
	public PotTileEntity() {
		super(TileEntityLoader.POT_TILE_ENTITY.get());
		for(int i=0;i<5;i++) {
			slot[i]=ItemStack.EMPTY;
		}
		// TODO 自动生成的构造函数存根
	}
	public ItemStack tryacceptitem(ItemStack itemstackIn) {
			for(int i=0;i<5;i++) {
				if(slot[i].isEmpty()) {
					slot[i]=itemstackIn.split(1);
					return itemstackIn;
				}
			}
			return itemstackIn;
	}
	public ItemStack tryextractitem() {
		for(int i=4;i>=0;i--) {
			if(!slot[i].isEmpty()) {
				ItemStack back=slot[i].copy();
				slot[i]=ItemStack.EMPTY;
				return back;
			}
		}
		return ItemStack.EMPTY;
	}
	public void start(PlayerEntity user) {
		if(!this.slot[0].isEmpty()&&this.canuse()&&!this.using) {
			this.user=user;
			this.using=true;
			this.process=0;
			//int type=5;
			boolean failed=false;
			for(int i=0;i<5;i++) {
				if(this.slot[i].isEmpty())
					break;
				if(!this.slot[i].isEdible()) {
					//type+=2;
					failed=true;
					break;
				}
			}
			if(failed) 
				level.playSound(null,worldPosition, SoundLoader.COOKING_FAILED.get(), SoundCategory.BLOCKS, 10f, 1f);
			else
				level.playSound(null,worldPosition, SoundLoader.COOKING.get(), SoundCategory.BLOCKS, 10f, 1f);
			this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getLevel().getBlockState(this.getBlockPos()),this.getLevel().getBlockState(this.getBlockPos()),1);
			/*List<PlayerEntity> playerlist= world.getEntitiesWithinAABB(PlayerEntity.class,user.getBoundingBox().grow(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO 自动生成的方法存根
					if(t instanceof PlayerEntity) 
						return true;
					else
						return false;
				}});
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new SoundPack(type,this.getPos()));
    		}*/
		}
	}
	private void finish() {
		this.process=0;
		this.using=false;
		this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getLevel().getBlockState(this.getBlockPos()),this.getLevel().getBlockState(this.getBlockPos()),1);
		if(user==null)
			return;
		for(int i=0;i<5;i++) {
			if(this.slot[i].isEmpty())
				break;
			if(!this.slot[i].isEdible()) {
				for(int j=0;j<5;j++) {
					this.slot[j]=ItemStack.EMPTY;
				}
				ItemStack food=new ItemStack(ItemLoader.HARD_FOOD.get());
				Entity itemdrops=new ItemEntity(this.level,this.user.getX(),this.user.getY(),this.user.getZ(),food);
				this.level.addFreshEntity(itemdrops);
				Networking.FOODMESSAGEPACK.send(
		                PacketDistributor.PLAYER.with(
		                        () -> (ServerPlayerEntity) user
		                ),
		                new GuiMessagePack(1,0,0));
				return;
			}
		}
		float heal=0;
		int hunger=0;
		for(int i=0;i<5;i++) {
			if(this.slot[i].isEmpty())
				break;
			hunger+=this.slot[i].getItem().getFoodProperties().getNutrition();
			heal+=this.slot[i].getItem().getFoodProperties().getSaturationModifier()*2;
			this.slot[i]=ItemStack.EMPTY;
		}
		CompoundNBT tag=new CompoundNBT();
		tag.putFloat("heal", heal);
		tag.putInt("hunger", hunger);
		ItemStack food=new ItemStack(ItemLoader.FOOD.get());
		food.setTag(tag);
		Entity itemdrops=new ItemEntity(this.level,this.user.getX(),this.user.getY(),this.user.getZ(),food);
		this.level.addFreshEntity(itemdrops);
		Networking.SOUND.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayerEntity) user
                ),
                new SoundPack(6,this.getBlockPos()));
		Networking.FOODMESSAGEPACK.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayerEntity) user
                ),
                new GuiMessagePack(0,(int) heal,hunger));
	}
	@Override
    public void load(BlockState state, CompoundNBT nbt) {
		super.load(state,nbt);
		this.slot[0].deserializeNBT(nbt.getCompound("slot0"));
		this.slot[1].deserializeNBT(nbt.getCompound("slot1"));
		this.slot[2].deserializeNBT(nbt.getCompound("slot2"));
		this.slot[3].deserializeNBT(nbt.getCompound("slot3"));
		this.slot[4].deserializeNBT(nbt.getCompound("slot4"));
		this.using=nbt.getBoolean("using");
	}
	@Override
    public CompoundNBT save(CompoundNBT tag) {
		tag.put("slot0", slot[0].serializeNBT());
		tag.put("slot1", slot[1].serializeNBT());
		tag.put("slot2", slot[2].serializeNBT());
		tag.put("slot3", slot[3].serializeNBT());
		tag.put("slot4", slot[4].serializeNBT());
		tag.putBoolean("using", using);
		return super.save(tag);
	}
	@Override
	public void tick() {
		// TODO 自动生成的方法存根
		if(!this.level.isClientSide) {
			if(this.using&&this.canuse()) {
				if(this.process>=120) {
					this.finish();
				}
				else
					process++;
			}
		}
		if(this.level.isClientSide&&this.using) {
			if(this.level.getGameTime()%5==0)
				level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,this.getBlockPos().getX()+0.5,this.getBlockPos().getY()+1,this.getBlockPos().getZ()+0.5,Math.random()/50,0.07,Math.random()/50);
		}
	}
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tag=new CompoundNBT();
		tag.putBoolean("using", using);
		return new SUpdateTileEntityPacket(this.getBlockPos(),1,tag);
	}
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getTag();
		this.using=tag.getBoolean("using");
	}
	public ItemStack getStack(int index) {
		return this.slot[index];
	}
	private boolean canuse() {
		return this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.FIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.CAMPFIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.SOUL_FIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.SOUL_CAMPFIRE;
	}
}
