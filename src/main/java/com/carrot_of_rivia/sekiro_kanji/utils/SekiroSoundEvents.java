package com.carrot_of_rivia.sekiro_kanji.utils;

import com.carrot_of_rivia.sekiro_kanji.SekiroKanji;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SekiroSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SekiroKanji.MOD_ID);

    public static final RegistryObject<SoundEvent> DANGER = SOUND_EVENT.register("danger", () -> new SoundEvent(new ResourceLocation(SekiroKanji.MOD_ID, "danger")));
    public static final RegistryObject<SoundEvent> ENEMY_ALERT = SOUND_EVENT.register("enemy_alert", () -> new SoundEvent(new ResourceLocation(SekiroKanji.MOD_ID, "enemy_alert")));
}
