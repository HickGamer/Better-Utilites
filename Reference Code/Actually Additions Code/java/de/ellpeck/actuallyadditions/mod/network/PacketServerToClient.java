/*
 * This file ("PacketServerToClient.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.network;

import de.ellpeck.actuallyadditions.mod.util.ModUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketServerToClient implements IMessage{

    private NBTTagCompound data;
    private IDataHandler handler;

    public PacketServerToClient(){

    }

    public PacketServerToClient(NBTTagCompound data, IDataHandler handler){
        this.data = data;
        this.handler = handler;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        PacketBuffer buffer = new PacketBuffer(buf);
        try{
            this.data = buffer.readCompoundTag();

            int handlerId = buffer.readInt();
            if(handlerId >= 0 && handlerId < PacketHandler.DATA_HANDLERS.size()){
                this.handler = PacketHandler.DATA_HANDLERS.get(handlerId);
            }
        }
        catch(Exception e){
            ModUtil.LOGGER.error("Something went wrong trying to receive a client packet!", e);
        }
    }

    @Override
    public void toBytes(ByteBuf buf){
        PacketBuffer buffer = new PacketBuffer(buf);

        buffer.writeCompoundTag(this.data);
        buffer.writeInt(PacketHandler.DATA_HANDLERS.indexOf(this.handler));
    }

    public static class Handler implements IMessageHandler<PacketServerToClient, IMessage>{

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(final PacketServerToClient message, final MessageContext ctx){
            Minecraft.getMinecraft().addScheduledTask(new Runnable(){
                @Override
                public void run(){
                    if(message.data != null && message.handler != null){
                        message.handler.handleData(message.data, ctx);
                    }
                }
            });
            return null;
        }
    }
}
