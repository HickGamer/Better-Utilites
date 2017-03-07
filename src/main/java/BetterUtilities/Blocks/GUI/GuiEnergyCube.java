package BetterUtilities.Blocks.GUI;

import BetterUtilities.Blocks.TileEntities.TileEnergyCube;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiEnergyCube extends GuiContainer{
	
	private TileEnergyCube te;
	private IInventory playerInv;

	public GuiEnergyCube(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
