package com.teambrmodding.neotech.common.tiles.storage.tanks;

import com.teambr.bookshelf.common.tiles.FluidHandler;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

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
public class TileBasicTank extends FluidHandler {

    // Variables
    public static final int TANK = 0;
    /**
     * Used to set up the tanks needed. You can insert any number of tanks
     */
    @Override
    protected void setupTanks() {
        tanks = new FluidTank[1];

        tanks[0] = new FluidTank(getCapacity());
    }

    /**
     * Which tanks can input
     *
     * @return An array with the indexes of the input tanks
     */
    @Override
    protected int[] getInputTanks() {
        return new int[] { TANK };
    }

    /**
     * Which tanks can output
     *
     * @return An array with the indexes of the output tanks
     */
    @Override
    protected int[] getOutputTanks() {
        return new int[] { TANK };
    }

    /**
     * Used to get the capacity of the tank, children will override this
     * @return The tank capacity
     */
    protected int getCapacity() {
        return Fluid.BUCKET_VOLUME * 16;
    }

    public float offset = 0.0F;
    private float dir = 0.01F;

    private int lastLightLevel = 0;

    /**
     * Used to get the Brightness of the fluid contained
     * @return The light level to use
     */
    protected int getBrightness() {
        if(tanks[TANK].getFluid() != null)
            return tanks[TANK].getFluid().getFluid().getLuminosity() * (tanks[TANK].getFluidAmount() / tanks[TANK].getCapacity());
        else
            return 0;
    }

    /**
     * Gets the fluid level for use in rendering in world
     * @return
     */
    public float getFluidLevelScaled() {
        return Math.min(14.99F, ((tanks[TANK].getFluidAmount() * 14) / tanks[TANK].getCapacity()) + 1.31F + offset) / 16F;
    }

    @Override
    protected void onClientTick() {
        if(tanks[TANK].getFluid() != null && !(this instanceof TileVoidTank)) {
            offset += dir;
            if(offset >= 0.3 || offset <= -0.3)
                    dir = -dir;
        }

        int light = getBrightness();
        if(lastLightLevel != light) {
            lastLightLevel = light;
            world.setLightFor(EnumSkyBlock.BLOCK, pos, light);
        }
    }
}
