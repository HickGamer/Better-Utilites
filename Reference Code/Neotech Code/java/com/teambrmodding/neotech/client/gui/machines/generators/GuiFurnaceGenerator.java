package com.teambrmodding.neotech.client.gui.machines.generators;

import com.teambr.bookshelf.client.gui.GuiColor;
import com.teambr.bookshelf.client.gui.GuiTextFormat;
import com.teambr.bookshelf.client.gui.component.display.GuiComponentColoredZone;
import com.teambr.bookshelf.client.gui.component.display.GuiComponentFluidTank;
import com.teambr.bookshelf.client.gui.component.display.GuiComponentText;
import com.teambr.bookshelf.client.gui.component.display.GuiComponentTextureAnimated;
import com.teambr.bookshelf.network.PacketManager;
import com.teambr.bookshelf.util.ClientUtils;
import com.teambr.bookshelf.util.EnergyUtils;
import com.teambrmodding.neotech.client.gui.machines.GuiAbstractMachine;
import com.teambrmodding.neotech.collections.EnumInputOutputMode;
import com.teambrmodding.neotech.common.container.machines.generators.ContainerFurnaceGenerator;
import com.teambrmodding.neotech.common.tiles.machines.generators.TileFluidGenerator;
import com.teambrmodding.neotech.common.tiles.machines.generators.TileFurnaceGenerator;
import com.teambrmodding.neotech.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.awt.*;
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
 * @since 2/17/2017
 */
public class GuiFurnaceGenerator extends GuiAbstractMachine<ContainerFurnaceGenerator> {
    protected TileFurnaceGenerator generator;

    /**
     * Main constructor for Guis
     */
    public GuiFurnaceGenerator(EntityPlayer player, TileFurnaceGenerator generator) {
        super(new ContainerFurnaceGenerator(player.inventory, generator), 175, 165, "neotech.furnacegenerator.title",
                new ResourceLocation(Reference.MOD_ID, "textures/gui/generatorFurnace.png"), generator, player);
        this.generator = generator;
        addComponents();
    }

    /**
     * This will be called after the GUI has been initialized and should be where you add all components.
     */
    @Override
    protected void addComponents() {
        if(generator != null) {
            // Flame for Burn
            components.add(new GuiComponentTextureAnimated(this, 80, 41, 176, 83,
                    14, 14, GuiComponentTextureAnimated.ANIMATION_DIRECTION.UP) {
                @Override
                protected int getCurrentProgress(int scale) {
                    return generator.isActive() ? generator.getBurnProgressScaled(14) : 0;
                }
            });

            // Power Bar
            components.add(new GuiComponentTextureAnimated(this, 16, 12, 176, 97,
                    16, 62, GuiComponentTextureAnimated.ANIMATION_DIRECTION.UP) {
                @Override
                protected int getCurrentProgress(int scale) {
                    return (machine.getEnergyStored() * scale) / machine.getMaxEnergyStored();
                }

                /**
                 * Used to determine if a dynamic tooltip is needed at runtime
                 *
                 * @param mouseX Mouse X Pos
                 * @param mouseY Mouse Y Pos
                 * @return A list of string to display
                 */
                @Nullable
                @Override
                public List<String> getDynamicToolTip(int mouseX, int mouseY) {
                    List<String> toolTip = new ArrayList<>();
                    EnergyUtils.addToolTipInfo(machine.getCapability(CapabilityEnergy.ENERGY, null),
                            toolTip, machine.energyStorage.getMaxInsert(), machine.energyStorage.getMaxExtract());
                    return toolTip;
                }
            });

            // Currently Generating
            components.add(new GuiComponentText(this, 64, 18,
                    GuiColor.ORANGE + ClientUtils.translate("bookshelfapi.energy.energyTick") + generator.getEnergyProduced(), Color.DARK_GRAY) {
                /**
                 * Called after base render, is already translated to guiLeft and guiTop, just move offset
                 *
                 * @param guiLeft
                 * @param guiTop
                 * @param mouseX
                 * @param mouseY
                 */
                @Override
                public void renderOverlay(int guiLeft, int guiTop, int mouseX, int mouseY) {
                    String string = GuiColor.RED + ClientUtils.translate("bookshelfapi.energy.energyTick") + " " +
                            EnergyUtils.getEnergyDisplay(generator.getEnergyProduced());
                    setXPos(xSize / 2 - (fontRendererObj.getStringWidth(string) / 2));
                    setLabel(string);
                    super.renderOverlay(guiLeft, guiTop, mouseX, mouseY);
                }
            });

            // Fluid Tank
            components.add(new GuiComponentFluidTank(this, 144, 12, 16, 62,
                    generator.tanks[TileFluidGenerator.TANK]) {
                /**
                 * Used to determine if a dynamic tooltip is needed at runtime
                 *
                 * @param mouseX Mouse X Pos
                 * @param mouseY Mouse Y Pos
                 * @return A list of string to display
                 */
                @Nullable
                @Override
                public List<String> getDynamicToolTip(int mouseX, int mouseY) {
                    List<String> toolTip = new ArrayList<>();
                    toolTip.add(generator.tanks[TileFluidGenerator.TANK].getFluid() != null ?
                            GuiColor.ORANGE + generator.tanks[TileFluidGenerator.TANK].getFluid().getLocalizedName() :
                            GuiColor.RED + ClientUtils.translate("neotech.text.empty"));
                    toolTip.add(ClientUtils.formatNumber(generator.tanks[TileFluidGenerator.TANK].getFluidAmount()) + " / " +
                            ClientUtils.formatNumber(generator.tanks[TileFluidGenerator.TANK].getCapacity()) + " mb");
                    toolTip.add("");
                    toolTip.add(GuiColor.GRAY + "" + GuiTextFormat.ITALICS + ClientUtils.translate("neotech.text.clearTank"));
                    return toolTip;
                }

                /**
                 * Called when the mouse is pressed
                 *
                 * @param x      Mouse X Position
                 * @param y      Mouse Y Position
                 * @param button Mouse Button
                 */
                @Override
                public void mouseDown(int x, int y, int button) {
                    if(ClientUtils.isCtrlPressed() && ClientUtils.isShiftPressed()) {
                        generator.tanks[TileFluidGenerator.TANK].setFluid(null);
                        PacketManager.updateTileWithClientInfo(generator);
                    }
                }
            });

            // Fluid Color Indicator
            components.add(new GuiComponentColoredZone(this, 143, 11, 18, 64, new Color(0, 0, 0, 0)){
                /**
                 * Override this to change the color
                 *
                 * @return The color, by default the passed color
                 */
                @Override
                protected Color getDynamicColor() {
                    Color color = new Color(0, 0, 0, 0);

                    // Checking if input is enabled
                    for(EnumFacing dir : EnumFacing.values()) {
                        if(generator.getModeForSide(dir) == EnumInputOutputMode.INPUT_ALL) {
                            color = EnumInputOutputMode.INPUT_ALL.getHighlightColor();
                            break;
                        } else if(generator.getModeForSide(dir) == EnumInputOutputMode.INPUT_SECONDARY)
                            color = EnumInputOutputMode.INPUT_SECONDARY.getHighlightColor();
                    }
                    return color;
                }
            });

            // Slot Color Indicator
            components.add(new GuiComponentColoredZone(this, 78, 56, 20, 20, new Color(0, 0, 0, 0)){
                /**
                 * Override this to change the color
                 *
                 * @return The color, by default the passed color
                 */
                @Override
                protected Color getDynamicColor() {
                    Color color = new Color(0, 0, 0, 0);

                    // Checking if input is enabled
                    for(EnumFacing dir : EnumFacing.values()) {
                        if(generator.getModeForSide(dir) == EnumInputOutputMode.INPUT_ALL) {
                            color = EnumInputOutputMode.INPUT_ALL.getHighlightColor();
                            break;
                        } else if(generator.getModeForSide(dir) == EnumInputOutputMode.INPUT_PRIMARY)
                            color = EnumInputOutputMode.INPUT_PRIMARY.getHighlightColor();
                    }

                    return color;
                }
            });
        }
    }
}
