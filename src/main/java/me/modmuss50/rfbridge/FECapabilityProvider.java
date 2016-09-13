package me.modmuss50.rfbridge;

import cofh.api.energy.IEnergyConnection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

/**
 * Created by Mark on 06/06/2016.
 */
public class FECapabilityProvider implements ICapabilityProvider {

    TileEntity tileEntity;

    RFContainer container;

    public FECapabilityProvider(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
        container = new RFContainer();
        container.setTile(tileEntity);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        container.setDir(facing);
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) container;
        }
        return null;
    }
}
