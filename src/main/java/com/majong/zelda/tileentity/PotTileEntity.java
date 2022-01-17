package com.majong.zelda.tileentity;

import javax.annotation.Nullable;

import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.FoodMessagePack;
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
				if(!this.slot[i].isFood()) {
					//type+=2;
					failed=true;
					break;
				}
			}
			if(failed) 
				world.playSound(null,pos, SoundLoader.COOKING_FAILED.get(), SoundCategory.BLOCKS, 10f, 1f);
			else
				world.playSound(null,pos, SoundLoader.COOKING.get(), SoundCategory.BLOCKS, 10f, 1f);
			this.getWorld().notifyBlockUpdate(this.getPos(),this.getWorld().getBlockState(this.getPos()),this.getWorld().getBlockState(this.getPos()),1);
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
		this.getWorld().notifyBlockUpdate(this.getPos(),this.getWorld().getBlockState(this.getPos()),this.getWorld().getBlockState(this.getPos()),1);
		if(user==null)
			return;
		for(int i=0;i<5;i++) {
			if(this.slot[i].isEmpty())
				break;
			if(!this.slot[i].isFood()) {
				for(int j=0;j<5;j++) {
					this.slot[j]=ItemStack.EMPTY;
				}
				ItemStack food=new ItemStack(ItemLoader.HARD_FOOD.get());
				Entity itemdrops=new ItemEntity(this.world,this.user.getPosX(),this.user.getPosY(),this.user.getPosZ(),food);
				this.world.addEntity(itemdrops);
				Networking.FOODMESSAGEPACK.send(
		                PacketDistributor.PLAYER.with(
		                        () -> (ServerPlayerEntity) user
		                ),
		                new FoodMessagePack(1,0,0));
				return;
			}
		}
		float heal=0;
		int hunger=0;
		for(int i=0;i<5;i++) {
			if(this.slot[i].isEmpty())
				break;
			hunger+=this.slot[i].getItem().getFood().getHealing();
			heal+=this.slot[i].getItem().getFood().getSaturation()*2;
			this.slot[i]=ItemStack.EMPTY;
		}
		CompoundNBT tag=new CompoundNBT();
		tag.putFloat("heal", heal);
		tag.putInt("hunger", hunger);
		ItemStack food=new ItemStack(ItemLoader.FOOD.get());
		food.setTag(tag);
		Entity itemdrops=new ItemEntity(this.world,this.user.getPosX(),this.user.getPosY(),this.user.getPosZ(),food);
		this.world.addEntity(itemdrops);
		Networking.SOUND.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayerEntity) user
                ),
                new SoundPack(6,this.getPos()));
		Networking.FOODMESSAGEPACK.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayerEntity) user
                ),
                new FoodMessagePack(0,(int) heal,hunger));
	}
	@Override
    public void read(BlockState state, CompoundNBT nbt) {
		super.read(state,nbt);
		this.slot[0].deserializeNBT(nbt.getCompound("slot0"));
		this.slot[1].deserializeNBT(nbt.getCompound("slot1"));
		this.slot[2].deserializeNBT(nbt.getCompound("slot2"));
		this.slot[3].deserializeNBT(nbt.getCompound("slot3"));
		this.slot[4].deserializeNBT(nbt.getCompound("slot4"));
		this.using=nbt.getBoolean("using");
	}
	@Override
    public CompoundNBT write(CompoundNBT tag) {
		tag.put("slot0", slot[0].serializeNBT());
		tag.put("slot1", slot[1].serializeNBT());
		tag.put("slot2", slot[2].serializeNBT());
		tag.put("slot3", slot[3].serializeNBT());
		tag.put("slot4", slot[4].serializeNBT());
		tag.putBoolean("using", using);
		return super.write(tag);
	}
	@Override
	public void tick() {
		// TODO 自动生成的方法存根
		if(!this.world.isRemote) {
			if(this.using&&this.canuse()) {
				if(this.process>=120) {
					this.finish();
				}
				else
					process++;
			}
		}
		if(this.world.isRemote&&this.using) {
			if(this.world.getGameTime()%5==0)
				world.addOptionalParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,this.getPos().getX()+0.5,this.getPos().getY()+1,this.getPos().getZ()+0.5,Math.random()/50,0.07,Math.random()/50);
		}
	}
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tag=new CompoundNBT();
		tag.putBoolean("using", using);
		return new SUpdateTileEntityPacket(this.getPos(),1,tag);
	}
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		this.using=tag.getBoolean("using");
	}
	public ItemStack getStack(int index) {
		return this.slot[index];
	}
	private boolean canuse() {
		return this.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock()==Blocks.FIRE||this.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock()==Blocks.CAMPFIRE||this.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock()==Blocks.SOUL_FIRE||this.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock()==Blocks.SOUL_CAMPFIRE;
	}
}
