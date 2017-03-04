package com.teambrmodding.neotech.managers;

import com.teambrmodding.neotech.common.blocks.machines.BlockInventoryCharger;
import com.teambrmodding.neotech.common.blocks.machines.BlockMachine;
import com.teambrmodding.neotech.common.blocks.machines.BlockSolarPanel;
import com.teambrmodding.neotech.common.blocks.machines.BlockTreeFarm;
import com.teambrmodding.neotech.common.blocks.storage.BlockEnergyStorage;
import com.teambrmodding.neotech.common.blocks.storage.BlockFluidStorage;
import com.teambrmodding.neotech.common.blocks.storage.ItemBlockEnergyStorage;
import com.teambrmodding.neotech.common.blocks.storage.ItemBlockFluidStorage;
import com.teambrmodding.neotech.common.tiles.machines.generators.TileFluidGenerator;
import com.teambrmodding.neotech.common.tiles.machines.generators.TileFurnaceGenerator;
import com.teambrmodding.neotech.common.tiles.machines.generators.TileSolarPanel;
import com.teambrmodding.neotech.common.tiles.machines.operators.TileInventoryCharger;
import com.teambrmodding.neotech.common.tiles.machines.operators.TileTreeFarm;
import com.teambrmodding.neotech.common.tiles.machines.processors.*;
import com.teambrmodding.neotech.common.tiles.storage.TileEnergyStorage;
import com.teambrmodding.neotech.common.tiles.storage.tanks.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

/**
 * This file was created for NeoTech
 *
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 2/11/2017
 */
public class BlockManager {
    // Machines
    public static BlockMachine electricFurnace    = new BlockMachine("electricFurnace", TileElectricFurnace.class);
    public static BlockMachine electricCrusher    = new BlockMachine("electricCrusher", TileElectricCrusher.class);
    public static BlockMachine furnaceGenerator   = new BlockMachine("furnaceGenerator", TileFurnaceGenerator.class);
    public static BlockMachine fluidGenerator     = new BlockMachine("fluidGenerator", TileFluidGenerator.class);
    public static BlockMachine electricCrucible   = new BlockMachine("electricCrucible", TileCrucible.class);
    public static BlockMachine electricSolidifier = new BlockMachine("electricSolidifier", TileSolidifier.class);
    public static BlockMachine electricAlloyer    = new BlockMachine("alloyer", TileAlloyer.class);
    public static BlockMachine electricCentrifuge = new BlockMachine("centrifuge", TileCentrifuge.class);

    // Operators
    public static BlockMachine treeFarm                  = new BlockTreeFarm("treeFarm");
    public static BlockInventoryCharger inventoryCharger = new BlockInventoryCharger();

    // RF Storage
    public static BlockEnergyStorage basicEnergyStorage    = new BlockEnergyStorage("basicEnergyStorage", 1);
    public static BlockEnergyStorage advancedEnergyStorage = new BlockEnergyStorage("advancedEnergyStorage", 2);
    public static BlockEnergyStorage eliteEnergyStorage    = new BlockEnergyStorage("eliteEnergyStorage", 3);
    public static BlockEnergyStorage creativeEnergyStorage = new BlockEnergyStorage("creativeEnergyStorage", 4);

    // Tanks
    public static BlockFluidStorage basicTank    = new BlockFluidStorage("basicTank", TileBasicTank.class);
    public static BlockFluidStorage advancedTank = new BlockFluidStorage("advancedTank", TileAdvancedTank.class);
    public static BlockFluidStorage eliteTank    = new BlockFluidStorage("eliteTank", TileEliteTank.class);
    public static BlockFluidStorage creativeTank = new BlockFluidStorage("creativeTank", TileCreativeTank.class);
    public static BlockFluidStorage voidTank     = new BlockFluidStorage("voidTank", TileVoidTank.class);

    // Solar Panels
    public static BlockSolarPanel solarPanelT1 = new BlockSolarPanel("solarPanelT1", 1);
    public static BlockSolarPanel solarPanelT2 = new BlockSolarPanel("solarPanelT2", 2);
    public static BlockSolarPanel solarPanelT3 = new BlockSolarPanel("solarPanelT3", 3);

    public static void preInit() {
        // Machines
        registerBlock(electricFurnace, TileElectricFurnace.class);
        registerBlock(electricCrusher, TileElectricCrusher.class);
        registerBlock(furnaceGenerator, TileFurnaceGenerator.class);
        registerBlock(fluidGenerator, TileFluidGenerator.class);
        registerBlock(electricCrucible, TileCrucible.class);
        registerBlock(electricSolidifier, TileSolidifier.class);
        registerBlock(electricAlloyer, TileAlloyer.class);
        registerBlock(electricCentrifuge, TileCentrifuge.class);

        // Operators
        registerBlock(treeFarm, TileTreeFarm.class);
        registerBlock(inventoryCharger, TileInventoryCharger.class);

        // RF Storage
        registerBlock(basicEnergyStorage, new ItemBlockEnergyStorage(basicEnergyStorage), TileEnergyStorage.class, null);
        registerBlock(advancedEnergyStorage, new ItemBlockEnergyStorage(advancedEnergyStorage), null, null);
        registerBlock(eliteEnergyStorage, new ItemBlockEnergyStorage(eliteEnergyStorage), null, null);
        registerBlock(creativeEnergyStorage, new ItemBlockEnergyStorage(creativeEnergyStorage), null, null);

        // Tanks
        registerBlock(basicTank, new ItemBlockFluidStorage(basicTank), TileBasicTank.class, null);
        registerBlock(advancedTank, new ItemBlockFluidStorage(advancedTank), TileAdvancedTank.class, null);
        registerBlock(eliteTank, new ItemBlockFluidStorage(eliteTank), TileEliteTank.class, null);
        registerBlock(creativeTank, new ItemBlockFluidStorage(creativeTank), TileCreativeTank.class, null);
        registerBlock(voidTank, new ItemBlockFluidStorage(voidTank), TileVoidTank.class, null);

        // Solar Panels
        registerBlock(solarPanelT1, TileSolarPanel.class);
        registerBlock(solarPanelT2);
        registerBlock(solarPanelT3);
    }

    /**
     * Helper Method to register a block
     * @param block The block to register
     * @param itemBlock The item block for this block
     * @param tileEntity The tile class
     * @param oreDict The ore dict tag
     * @return The block registered
     */
    public static <T extends Block> T registerBlock(T block, ItemBlock itemBlock,
                                                    @Nullable Class<? extends TileEntity> tileEntity,
                                                    @Nullable String oreDict) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if(tileEntity != null)
            GameRegistry.registerTileEntity(tileEntity, block.getRegistryName().toString());
        if(oreDict != null)
            OreDictionary.registerOre(oreDict, block);

        return block;
    }

    /**
     * Short hand to register a block
     */
    public static <T extends Block> T registerBlock(T block, @Nullable Class<? extends TileEntity> tileEntity) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return registerBlock(block, itemBlock, tileEntity, null);
    }

    /**
     * Short hand to register a block
     */
    public static <T extends Block> T registerBlock(T block, String oreDict) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return registerBlock(block, itemBlock, null, oreDict);    }

    /**
     * Short hand to register a block
     */
    public static <T extends Block> T registerBlock(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return registerBlock(block, itemBlock, null, null);    }
}
