package com.carrot_of_rivia.sekiro_kanji.particles.particle_data;

import com.carrot_of_rivia.sekiro_kanji.particles.ParticleRegister;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleType;

public class ResurrectSignParticleData extends SekiroAbstractParticleData{
    public ResurrectSignParticleData(double x_shift, double y_shift, double z_shift, int followEntityId) {
        super(x_shift, y_shift, z_shift, followEntityId);
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegister.RESURRECT_SIGN_PARTICLE.get();
    }

    public static final IDeserializer<ResurrectSignParticleData> DESERIALIZER = new IDeserializer<ResurrectSignParticleData>() {
        @Override
        public ResurrectSignParticleData fromCommand(ParticleType<ResurrectSignParticleData> particleType, StringReader stringReader) throws CommandSyntaxException {
            // TODO
            return null;
        }

        @Override
        public ResurrectSignParticleData fromNetwork(ParticleType<ResurrectSignParticleData> particleType, PacketBuffer packetBuffer) {
            return new ResurrectSignParticleData(packetBuffer.readDouble(), packetBuffer.readDouble(), packetBuffer.readDouble(), packetBuffer.readInt());
        }
    };

    public static final Codec<ResurrectSignParticleData> CODEC = RecordCodecBuilder.create((p_239803_0_) ->  {
        return p_239803_0_.group(Codec.DOUBLE.fieldOf("x_shift").forGetter((particleData) -> {
            return particleData.x_shift;
        }), Codec.DOUBLE.fieldOf("y_shift").forGetter((particleData) -> {
            return particleData.y_shift;
        }), Codec.DOUBLE.fieldOf("z_shift").forGetter((particleData) -> {
            return particleData.z_shift;
        }), Codec.INT.fieldOf("followEntityId").forGetter((particleData) -> {
            return particleData.followEntityId;
        })).apply(p_239803_0_, ResurrectSignParticleData::new);
    });
}
