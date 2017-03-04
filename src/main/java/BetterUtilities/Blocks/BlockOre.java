package BetterUtilities.Blocks;

import BetterUtilities.Library.BULibrary;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockOre extends Block {

	public BlockOre(String name, String rname) {
		super(Material.ROCK);
		{
			setUnlocalizedName(name);
			setRegistryName(new ResourceLocation(BULibrary.id, rname));
		}
	}

}
