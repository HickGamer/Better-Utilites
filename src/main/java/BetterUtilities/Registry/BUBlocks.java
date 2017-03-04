package BetterUtilities.Registry;

import BetterUtilities.Blocks.BlockEnergyCube;
import BetterUtilities.Library.BULibrary;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BUBlocks {
	
	public static Block ore;
	public static Block machineCore;
	public static Block energyCube;
	
	
	public static void addBlocks(){
		energyCube = new BlockEnergyCube("energycube", "energycube");
		register(energyCube);
	}
	
	public static void renderBlocks(){
		render(energyCube);
	}
	
	public static void register(Block block){
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	public static void register(Block block, ItemBlock itemBlock){
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	
	public static void render(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(BULibrary.id, block.getUnlocalizedName().substring(5)), "inventory"));
	}
	
	public static void render(Block block, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(BULibrary.id, fileName), "inventory"));
	}
	
//	private static void registerRender(Block block)
//	{
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(BULibrary.id + ":" + block.getUnlocalizedName().substring(5), "inventory"));
//	}

}
