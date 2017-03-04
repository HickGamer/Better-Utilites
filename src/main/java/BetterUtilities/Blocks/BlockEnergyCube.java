package BetterUtilities.Blocks;

import BetterUtilities.Library.BULibrary;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnergyCube extends Block{

	public BlockEnergyCube(String name, String rname) {
		super(Material.IRON);{
			setUnlocalizedName(name);
			setRegistryName(new ResourceLocation(BULibrary.id, rname));
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
