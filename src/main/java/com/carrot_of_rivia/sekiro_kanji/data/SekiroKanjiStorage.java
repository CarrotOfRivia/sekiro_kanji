package com.carrot_of_rivia.sekiro_kanji.data;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class SekiroKanjiStorage implements Capability.IStorage<SekiroKanjiData> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<SekiroKanjiData> capability, SekiroKanjiData instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("danger_cd", instance.getDangerCD());
        nbt.putInt("resurrect_cd", instance.getResurrectCD());
        nbt.putInt("burn_cd", instance.getBurnCD());
        nbt.putInt("poison_cd", instance.getPoisonCD());
        nbt.putInt("enemy_alert_cd", instance.getEnemyAlertCD());
        return nbt;
    }

    @Override
    public void readNBT(Capability<SekiroKanjiData> capability, SekiroKanjiData instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setDangerCD(((CompoundNBT) nbt).getInt("danger_cd"));
            instance.setResurrectCD(((CompoundNBT) nbt).getInt("resurrect_cd"));
            instance.setBurnCD(((CompoundNBT) nbt).getInt("burn_cd"));
            instance.setPoisonCD(((CompoundNBT) nbt).getInt("poison_cd"));
            instance.setEnemyAlertCD(((CompoundNBT) nbt).getInt("enemy_alert_cd"));
        }

    }
}
