package BetterUtilities.Registry;

import BetterUtilities.Items.ItemIngot;
import BetterUtilities.Library.BULibrary;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BUItems {

	public static Item ingot;
	public static Item gear;
	
	public static void addItems(){
		ingot = new ItemIngot("ingot");
		register(ingot);
	}
	
	public static void renderItems(){
		render(ingot);
	}
	
	public static void register(Item item){
		GameRegistry.register(item);
	}
	
	public static void render(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(BULibrary.id, fileName), "inventory"));
	}
	
	public static void render(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(BULibrary.id, item.getUnlocalizedName().substring(5)), "inventory"));
	}

	

}
