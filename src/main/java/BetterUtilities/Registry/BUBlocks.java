package BetterUtilities.Registry;

import BetterUtilities.Blocks.BlockEnergyCube;
import BetterUtilities.Blocks.BlockOre;
import BetterUtilities.Library.BULibrary;
import BetterUtilities.Library.EnumLibrary;
import net.minecraft.block.Block;
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
		ore = new BlockOre("ore", "ore");
		register(ore);
		register(energyCube);
	}
	
	public static void renderBlocks(){
		render(energyCube);
		
		for(int i = 0; i < EnumLibrary.Ore.values().length; i++){
			render(ore, i, "ore_" + EnumLibrary.Ore.values()[i].getName());
		}
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
	

}
