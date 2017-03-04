package com.teambrmodding.neotech.common.blocks.storage;

import com.teambr.bookshelf.Bookshelf;
import com.teambr.bookshelf.common.IOpensGui;
import com.teambr.bookshelf.common.blocks.IToolable;
import com.teambr.bookshelf.util.WorldUtils;
import com.teambrmodding.neotech.client.gui.storage.GuiEnergyStorage;
import com.teambrmodding.neotech.common.blocks.BaseBlock;
import com.teambrmodding.neotech.common.container.storage.ContainerEnergyStorage;
import com.teambrmodding.neotech.common.tiles.storage.TileEnergyStorage;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * This file was created for NeoTech
 *
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 2/15/2017
 */
public class BlockEnergyStorage extends BaseBlock implements IOpensGui, IToolable {
    private int tier;

    /**
     * Creates the storage block
     */
    public BlockEnergyStorage(String name, int tier) {
        super(Material.IRON, name, TileEnergyStorage.class);

        this.tier = tier;
    }

    /**
     * Used to get what tier this storage block is
     */
    public int getTier() {
        return tier;
    }

    /*******************************************************************************************************************
     * BlockContainer                                                                                                  *
     *******************************************************************************************************************/

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEnergyStorage(tier);
    }

    /*******************************************************************************************************************
     * Block Methods                                                                                                   *
     *******************************************************************************************************************/

    /**
     * Called when the block is broken, allows us to drop items from inventory
     * @param worldIn The world
     * @param pos The pos
     * @param state The state
     */
    @Override
    public boolean removedByPlayer(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if(!worldIn.isRemote) {
            WorldUtils.breakBlockSavingNBT(worldIn, pos, this);
            return true;
        }
        return false;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    /**
     * Called when the block is clicked on
     * @return True to prevent future logic
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Make sure our machine is reachable
        if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEnergyStorage) {
            // Open a GUI
            if(!playerIn.isSneaking()) {
                playerIn.openGui(Bookshelf.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(stack.hasTagCompound() && !worldIn.isRemote) {
            if(!stack.getTagCompound().hasKey("Tier"))
                stack.getTagCompound().setInteger("Tier", tier);
            worldIn.getTileEntity(pos).readFromNBT(stack.getTagCompound());
            worldIn.getTileEntity(pos).setPos(pos);
            worldIn.setBlockState(pos, state, 3);
        }
    }

    /*******************************************************************************************************************
     * IOpensGui                                                                                                       *
     *******************************************************************************************************************/

    /**
     * Return the container for this tile
     *
     * @param id     Id, probably not needed but could be used for multiple guis
     * @param player The player that is opening the gui
     * @param world  The world
     * @param x      X Pos
     * @param y      Y Pos
     * @param z      Z Pos
     * @return The container to open
     */
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerEnergyStorage(player.inventory, (TileEnergyStorage) world.getTileEntity(new BlockPos(x, y, z)));
    }

    /**
     * Return the gui for this tile
     *
     * @param id     Id, probably not needed but could be used for multiple guis
     * @param player The player that is opening the gui
     * @param world  The world
     * @param x      X Pos
     * @param y      Y Pos
     * @param z      Z Pos
     * @return The gui to open
     */
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiEnergyStorage(player, (TileEnergyStorage) world.getTileEntity(new BlockPos(x, y, z)));
    }

    /*******************************************************************************************************************
     * IToolable                                                                                                       *
     *******************************************************************************************************************/

    /**
     * Called to get what stack should be dropped on wrench
     *
     * @param world The world
     * @param pos   The position of the block
     * @return The stack to drop in the world
     */
    @Override
    public ItemStack getStackDroppedByWrench(World world, BlockPos pos) {
        return new ItemStack(this);
    }
}
