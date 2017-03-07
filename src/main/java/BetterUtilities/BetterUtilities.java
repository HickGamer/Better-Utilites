package BetterUtilities;

import BetterUtilities.Library.BULibrary;
import BetterUtilities.Loaders.InitLoader;
import BetterUtilities.Loaders.PostInitLoader;
import BetterUtilities.Loaders.PreInitLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BULibrary.id, name = BULibrary.name, version = "Alpha 1")
public class BetterUtilities {
	
	@Mod.Instance("betterutilites")
	public static BetterUtilities instance;
	
	
	@EventHandler
	public static void loadEvent(FMLPreInitializationEvent pre){
		PreInitLoader.init();
	}
	
	@EventHandler
	public static void loadEvent(FMLInitializationEvent pre){
		InitLoader.init();
	}
	
	@EventHandler
	public static void loadEvent(FMLPostInitializationEvent pre){
		PostInitLoader.init();
	}

}
