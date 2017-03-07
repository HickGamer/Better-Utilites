package BetterUtilities.Blocks.Container;

import BetterUtilities.Blocks.TileEntities.TileEnergyCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerEnergyCube extends Container{
	
	private TileEnergyCube te;
	private IItemHandler handler;
	
	public ContainerEnergyCube(IInventory playerInv, TileEnergyCube te){
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUseableByPlayer(playerIn);
	}

	

}
