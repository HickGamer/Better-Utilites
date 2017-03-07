package BetterUtilities.Loaders;

import BetterUtilities.Registry.BUBlocks;
import BetterUtilities.Registry.BUItems;

public class PreInitLoader {

	public static void init(){
		BUBlocks.addBlocks();
		BUBlocks.renderBlocks();
		BUItems.addItems();
		BUItems.renderItems();
	}

}
