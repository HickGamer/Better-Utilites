/*
 * This file ("BookletChapterTrials.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.chapter;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageTrials;
import de.ellpeck.actuallyadditions.mod.data.PlayerData;
import de.ellpeck.actuallyadditions.mod.util.ModUtil;
import de.ellpeck.actuallyadditions.mod.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BookletChapterTrials extends BookletChapter{

    public BookletChapterTrials(String identifier, ItemStack displayStack, boolean secondPageText){
        super(identifier, ActuallyAdditionsAPI.entryTrials, displayStack, new PageTrials(1, false, true), new PageTrials(2, true, secondPageText));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getLocalizedName(){
        return StringUtil.localize("booklet."+ModUtil.MOD_ID+".trials."+this.getIdentifier()+".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getLocalizedNameWithFormatting(){
        EntityPlayer player = Minecraft.getMinecraft().player;
        PlayerData.PlayerSave data = PlayerData.getDataFromPlayer(player);
        boolean completed = data.completedTrials.contains(this.getIdentifier());

        return (completed ? TextFormatting.DARK_GREEN : TextFormatting.DARK_RED)+TextFormatting.ITALIC.toString()+this.getLocalizedName();
    }
}
