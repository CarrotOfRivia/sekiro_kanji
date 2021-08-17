package com.carrot_of_rivia.sekiro_kanji.particles;

import com.carrot_of_rivia.sekiro_kanji.SekiroKanji;
import com.carrot_of_rivia.sekiro_kanji.particles.particle_data.*;
import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegister {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SekiroKanji.MOD_ID);

    public static final RegistryObject<ParticleType<DangerSignParticleData>> DANGER_SIGN_PARTICLE = PARTICLES.register("danger_sign", ()->new ParticleType<DangerSignParticleData>(false, DangerSignParticleData.DESERIALIZER) {
        @Override
        public Codec<DangerSignParticleData> codec() {
            return DangerSignParticleData.CODEC;
        }
    });

    public static final RegistryObject<ParticleType<ResurrectSignParticleData>> RESURRECT_SIGN_PARTICLE = PARTICLES.register("resurrect_sign", ()->new ParticleType<ResurrectSignParticleData>(false, ResurrectSignParticleData.DESERIALIZER) {
        @Override
        public Codec<ResurrectSignParticleData> codec() {
            return ResurrectSignParticleData.CODEC;
        }
    });

    public static final RegistryObject<ParticleType<PoisonSignParticleData>> POISON_SIGN_PARTICLE = PARTICLES.register("poison_sign", ()->new ParticleType<PoisonSignParticleData>(false, PoisonSignParticleData.DESERIALIZER) {
        @Override
        public Codec<PoisonSignParticleData> codec() {
            return PoisonSignParticleData.CODEC;
        }
    });

    public static final RegistryObject<ParticleType<BurnSignParticleData>> BURN_SIGN_PARTICLE = PARTICLES.register("burn_sign", ()->new ParticleType<BurnSignParticleData>(false, BurnSignParticleData.DESERIALIZER) {
        @Override
        public Codec<BurnSignParticleData> codec() {
            return BurnSignParticleData.CODEC;
        }
    });

    public static final RegistryObject<ParticleType<EnemyAlertSignParticleData>> ENEMY_ALERT_SIGN_PARTICLE = PARTICLES.register("enemy_alert_sign", ()->new ParticleType<EnemyAlertSignParticleData>(false, EnemyAlertSignParticleData.DESERIALIZER) {
        @Override
        public Codec<EnemyAlertSignParticleData> codec() {
            return EnemyAlertSignParticleData.CODEC;
        }
    });
}
