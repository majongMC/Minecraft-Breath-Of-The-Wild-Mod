package com.majong.zelda.tileentity;

import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.GuiMessagePack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

public class PotTileEntity extends BlockEntity{
	private ItemStack slot[]=new ItemStack[5];
	private Player user;
	private boolean using=false;
	private int process=0;
	public PotTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(TileEntityLoader.POT_TILE_ENTITY.get(),pWorldPosition,pBlockState);
		for(int i=0;i<5;i++) {
			slot[i]=ItemStack.EMPTY;
		}
		// TODO �Զ����ɵĹ��캯�����
	}
	public ItemStack tryacceptitem(ItemStack itemstackIn) {
			for(int i=0;i<5;i++) {
				if(slot[i].isEmpty()) {
					slot[i]=itemstackIn.split(1);
					setChanged(this.level, this.worldPosition, this.getBlockState());
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
				setChanged(this.level, this.worldPosition, this.getBlockState());
				return back;
			}
		}
		return ItemStack.EMPTY;
	}
	public void start(Player user) {
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
				level.playSound(null,worldPosition, SoundLoader.COOKING_FAILED.get(), SoundSource.BLOCKS, 10f, 1f);
			else
				level.playSound(null,worldPosition, SoundLoader.COOKING.get(), SoundSource.BLOCKS, 10f, 1f);
			this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getLevel().getBlockState(this.getBlockPos()),this.getLevel().getBlockState(this.getBlockPos()),1);
			setChanged(this.level, this.worldPosition, this.getBlockState());
			/*List<Player> playerlist= Level.getEntitiesWithinAABB(Player.class,user.getBoundingBox().grow(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO �Զ����ɵķ������
					if(t instanceof Player) 
						return true;
					else
						return false;
				}});
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
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
		                        () -> (ServerPlayer) user
		                ),
		                new GuiMessagePack(1,0,0));
				setChanged(this.level, this.worldPosition, this.getBlockState());
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
		CompoundTag tag=new CompoundTag();
		tag.putFloat("heal", heal);
		tag.putInt("hunger", hunger);
		ItemStack food=new ItemStack(ItemLoader.FOOD.get());
		food.setTag(tag);
		Entity itemdrops=new ItemEntity(this.level,this.user.getX(),this.user.getY(),this.user.getZ(),food);
		this.level.addFreshEntity(itemdrops);
		Networking.SOUND.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayer) user
                ),
                new SoundPack(6,this.getBlockPos()));
		Networking.FOODMESSAGEPACK.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayer) user
                ),
                new GuiMessagePack(0,(int) heal,hunger));
		setChanged(this.level, this.worldPosition, this.getBlockState());
	}
	@Override
    public void load(CompoundTag nbt) {
		super.load(nbt);
		this.slot[0]=ItemStack.of(nbt.getCompound("slot0"));
		this.slot[1]=ItemStack.of(nbt.getCompound("slot1"));
		this.slot[2]=ItemStack.of(nbt.getCompound("slot2"));
		this.slot[3]=ItemStack.of(nbt.getCompound("slot3"));
		this.slot[4]=ItemStack.of(nbt.getCompound("slot4"));
		this.using=nbt.getBoolean("using");
	}
	@Override
    public void saveAdditional(CompoundTag tag) {
		tag.put("slot0", slot[0].serializeNBT());
		tag.put("slot1", slot[1].serializeNBT());
		tag.put("slot2", slot[2].serializeNBT());
		tag.put("slot3", slot[3].serializeNBT());
		tag.put("slot4", slot[4].serializeNBT());
		tag.putBoolean("using", using);
		super.saveAdditional(tag);
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, PotTileEntity tile) {
		// TODO �Զ����ɵķ������
		if(!pLevel.isClientSide) {
			if(tile.using&&tile.canuse()) {
				if(tile.process>=120) {
					tile.finish();
				}
				else
					tile.process++;
			}
		}
		if(pLevel.isClientSide&&tile.using) {
			if(tile.level.getGameTime()%5==0)
				pLevel.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,pPos.getX()+0.5,pPos.getY()+1,pPos.getZ()+0.5,Math.random()/50,0.07,Math.random()/50);
		}
	}
	@Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
	public CompoundTag getUpdateTag() {
        CompoundTag tag=this.saveWithoutMetadata();
        tag.putBoolean("using", using);
        return tag;
    }
	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		this.using=tag.getBoolean("using");
	}
	public ItemStack getStack(int index) {
		return this.slot[index];
	}
	private boolean canuse() {
		return this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.FIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.CAMPFIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.SOUL_FIRE||this.getLevel().getBlockState(this.worldPosition.offset(0, -1, 0)).getBlock()==Blocks.SOUL_CAMPFIRE;
	}
}
