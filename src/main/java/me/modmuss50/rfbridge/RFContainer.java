package me.modmuss50.rfbridge;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Created by Mark on 06/06/2016.
 */
public class RFContainer implements IEnergyStorage {

    EnumFacing dir;

    IEnergyReceiver receiver;
    IEnergyProvider provider;
    IEnergyHandler handler;

    public void setDir(EnumFacing dir) {
        this.dir = dir;
    }

    public void setTile(TileEntity entity){
        if(entity instanceof IEnergyReceiver){
            receiver = (IEnergyReceiver) entity;
        }
        if(entity instanceof IEnergyProvider){
            provider = (IEnergyProvider) entity;
        }
        if(entity instanceof IEnergyHandler){
            handler = (IEnergyHandler) entity;
        }
    }


    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(receiver != null){
            return receiver.receiveEnergy(dir, maxReceive, simulate);
        }
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if(provider != null){
            return provider.extractEnergy(dir, maxExtract, simulate);
        }
        return 0;
    }

    @Override
    public int getEnergyStored() {
        if(handler != null){
            return handler.getEnergyStored(dir);
        }
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        if(handler != null){
            return handler.getMaxEnergyStored(dir);
        }
        return 0;
    }

    @Override
    public boolean canExtract() {
        return provider != null;
    }

    @Override
    public boolean canReceive() {
        return receiver != null;
    }
}
