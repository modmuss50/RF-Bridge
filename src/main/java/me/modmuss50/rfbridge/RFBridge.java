package me.modmuss50.rfbridge;

import cofh.api.energy.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = "RF Bridge", modid = "rfbridge", version = "@MODVERSION@", dependencies = "required-after:reborncore;required-after:Forge@[12.18.1.2080,);")
public class RFBridge {

    @Mod.Instance
    public static RFBridge INSTANCE;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }


    @SubscribeEvent
    public void onTELoad(AttachCapabilitiesEvent.TileEntity event) {
        TileEntity tile = event.getTileEntity();
        if (tile instanceof IEnergyReceiver || tile instanceof IEnergyProvider || tile instanceof IEnergyHandler) {
            //This is where we need EnumFacing.UNKNOWN
            try{
                if(tile.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH)){
                    return;
                }
            }catch (Exception e){
                return;
            }

            event.addCapability(new ResourceLocation("rfbridge:forgeEnergy"), new FECapabilityProvider(tile));
        }
    }


}
