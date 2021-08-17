package com.carrot_of_rivia.sekiro_kanji.data;

import com.carrot_of_rivia.sekiro_kanji.SekiroKanji;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SekiroKanjiCap implements ICapabilityProvider {
    private final SekiroKanjiData data = new SekiroKanjiData();
    public static final ResourceLocation SEKIRO_CAP = new ResourceLocation(SekiroKanji.MOD_ID, "sekiro_cap");

    private final LazyOptional<SekiroKanjiData> dataOptional = LazyOptional.of(() -> {
        return this.data;
    });

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return SEKIRO_KANJI_CAP.orEmpty(cap, this.dataOptional);
    }

    @CapabilityInject(SekiroKanjiData.class)
    public static Capability<SekiroKanjiData> SEKIRO_KANJI_CAP = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(SekiroKanjiData.class, new SekiroKanjiStorage(), SekiroKanjiData::new);
    }

    public void invalidate() {
        this.dataOptional.invalidate();
    }

}
