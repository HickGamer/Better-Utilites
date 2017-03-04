package BetterUtilities.Blocks;

import BetterUtilities.Library.BULibrary;
import BetterUtilities.Library.EnumLibrary.MachineType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockMachineCore extends Block{
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", MachineType.class);
	
	public BlockMachineCore(String name, String rname) {
		super(Material.IRON);
		{
			setUnlocalizedName(name);
			setRegistryName(new ResourceLocation(BULibrary.id, rname));	
			setDefaultState(blockState.getBaseState().withProperty(TYPE, MachineType.basic));
		}
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list){
		for(int i = 0; i < MachineType.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
	}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		MachineType type = (MachineType) state.getValue(TYPE);
		return type.getID();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, MachineType.values()[meta]);
	}
	
	
	public String getSpecialName(ItemStack stack) {
		return MachineType.values()[stack.getItemDamage()].getName();
	}
	
	

}
