/*
 * This file ("ItemLens.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.items.lens;

import de.ellpeck.actuallyadditions.api.lens.ILensItem;
import de.ellpeck.actuallyadditions.api.lens.Lens;
import de.ellpeck.actuallyadditions.mod.items.base.ItemBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemLens extends ItemBase implements ILensItem{

    private final Lens type;

    public ItemLens(String name, Lens type){
        super(name);
        this.type = type;
        this.setMaxStackSize(1);
    }


    @Override
    public EnumRarity getRarity(ItemStack stack){
        return EnumRarity.UNCOMMON;
    }

    @Override
    public Lens getLens(){
        return this.type;
    }
}
