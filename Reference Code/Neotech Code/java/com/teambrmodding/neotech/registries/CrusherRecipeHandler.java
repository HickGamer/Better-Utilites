package com.teambrmodding.neotech.registries;

import com.google.gson.reflect.TypeToken;
import com.teambr.bookshelf.helper.LogHelper;
import com.teambrmodding.neotech.Neotech;
import com.teambrmodding.neotech.registries.recipes.CrusherRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.command.CommandBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created for NeoTech
 *
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 2/14/2017
 */
public class CrusherRecipeHandler extends AbstractRecipeHandler<CrusherRecipe, ItemStack, Pair<Pair<ItemStack, ItemStack>, Integer>> {

    /**
     * Used to get the base name of the files
     *
     * @return The base name for the files
     */
    @Override
    public String getBaseName() {
        return "crusher";
    }

    /**
     * This is the current version of the registry, if you update this it will cause the registry to be redone
     *
     * @return Current version number
     */
    @Override
    public int getVersion() {
        return 0;
    }


    /**
     * Used to get the default folder location
     *
     * @return The folder location
     */
    @Override
    public String getBaseFolderLocation() {
        return Neotech.configFolderLocation;
    }

    /**
     * Used to get what type token to read from file (Generics don't handle well)
     *
     * @return A defined type token
     */
    @Override
    public TypeToken<ArrayList<CrusherRecipe>> getTypeToken() {
        return new TypeToken<ArrayList<CrusherRecipe>>() {
        };
    }

    @Override
    public CommandBase getCommand() {
        //TODO: Rethink this
        return null;
    }

    @Override
    protected void generateDefaultRecipes() {
        LogHelper.logger.info("[Neotech] Creating Crusher Recipe List...");

        addRecipe(new CrusherRecipe("oreRedstone",
                "dustRedstone:12", "dustRedstone:4",
                20));
        addRecipe(new CrusherRecipe("oreLapis",
                "gemLapis:8", "gemLapis:2",
                20));
        addRecipe(new CrusherRecipe(getItemStackString(new ItemStack(Items.BLAZE_ROD)),
                getItemStackString(new ItemStack(Items.BLAZE_POWDER, 4)), getItemStackString(new ItemStack(Items.BLAZE_POWDER, 2)),
                15));
        addRecipe(new CrusherRecipe("cobblestone",
                "sand:2", "gravel:1",
                10));
        addRecipe(new CrusherRecipe("sand", "gravel:2", "", 0));
        addRecipe(new CrusherRecipe("bone",
                getItemStackString(new ItemStack(Items.DYE, 8, EnumDyeColor.WHITE.getDyeDamage())),
                getItemStackString(new ItemStack(Items.DYE, 2, EnumDyeColor.WHITE.getDyeDamage())),
                10));
        addRecipe(new CrusherRecipe("oreQuartz", "gemQuartz:4", "gemQuartz:2",
                50));
        addRecipe(new CrusherRecipe(getItemStackString(new ItemStack(Blocks.CLAY)),
                getItemStackString(new ItemStack(Items.CLAY_BALL, 4)), getItemStackString(new ItemStack(Items.CLAY_BALL, 2)),
                25));
        addRecipe(new CrusherRecipe(getItemStackString(new ItemStack(Blocks.HARDENED_CLAY)),
                getItemStackString(new ItemStack(Items.BRICK, 4)), "", 0));
        addRecipe(new CrusherRecipe("oreDiamond", "gemDiamond:2", "gemDiamond:1",
                10));
        addRecipe(new CrusherRecipe("oreEmerald", "gemEmerald:2", "gemEmerald:1",
                10));
        addRecipe(new CrusherRecipe("glowstone", "dustGlowstone:4", "dustGlowstone:2",
                15));
        addRecipe(new CrusherRecipe("oreCoal",
                getItemStackString(new ItemStack(Items.COAL, 3)), getItemStackString(new ItemStack(Items.DIAMOND, 1)),
                1));
        addRecipe(new CrusherRecipe("minecraft:wool:" + OreDictionary.WILDCARD_VALUE + ":1",
                getItemStackString(new ItemStack(Items.STRING, 4)), "", 0));
        addRecipe(new CrusherRecipe("blockGlass", "sand:1", "", 0));
        addRecipe(new CrusherRecipe("gravel",
                getItemStackString(new ItemStack(Items.FLINT, 3)), getItemStackString(new ItemStack(Items.FLINT, 1)),
                20));

        // Adjust to Ore Dictionary
        String[] oreDictionary = OreDictionary.getOreNames();

        for (String entry : oreDictionary) {
            if (entry.startsWith("dust") && !doesRecipeExist(entry.replaceFirst("dust", "ore"))) {
                List<ItemStack> oreList = OreDictionary.getOres(entry.replaceFirst("dust", "ore"));
                if (!oreList.isEmpty()) {
                    // We want a single type output
                    List<ItemStack> dustList = OreDictionary.getOres(entry);
                    if (!dustList.isEmpty())
                        addRecipe(new CrusherRecipe(entry.replaceFirst("dust", "ore"),
                                getItemStackString(new ItemStack(dustList.get(0).getItem(), 2, dustList.get(0).getItemDamage())),
                                "", 0));
                }
            } else if (entry.startsWith("ingot")) {
                List<ItemStack> dustList = OreDictionary.getOres(entry.replaceFirst("ingot", "dust"));
                if (!dustList.isEmpty() && !doesRecipeExist(entry.replaceFirst("ingot", "dust"))) {
                    addRecipe(new CrusherRecipe(entry,
                            getItemStackString(new ItemStack(dustList.get(0).getItem(), 1, dustList.get(0).getItemDamage())),
                            "", 0));
                }
            }
        }

        // Add Flower to Dyes
        for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
            if (recipe instanceof ShapelessRecipes) {
                ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
                if (shapelessRecipe.recipeItems.size() == 1 &&
                        Block.getBlockFromItem(shapelessRecipe.recipeItems.get(0).getItem()) != null &&
                        Block.getBlockFromItem(shapelessRecipe.recipeItems.get(0).getItem()) instanceof BlockFlower) {
                    String inputString = getItemStackString(shapelessRecipe.recipeItems.get(0));
                    if (!doesRecipeExist(inputString))
                        addRecipe(new CrusherRecipe(inputString,
                                "dye:" +
                                        String.valueOf(shapelessRecipe.getRecipeOutput().getCount() * 2) + ":" +
                                        String.valueOf(shapelessRecipe.getRecipeOutput().getItemDamage()),
                                "", 0));
                }
            }
        }

        saveToFile();
    }

    /**
     * Does this already exist in our registry
     */
    private boolean doesRecipeExist(String stack) {
        for (CrusherRecipe recipe : recipes)
            if (stack.equalsIgnoreCase(recipe.inputItemStack))
                return true;
        return false;
    }
}