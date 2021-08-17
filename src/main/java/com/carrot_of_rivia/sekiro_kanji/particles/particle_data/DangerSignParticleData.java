package com.carrot_of_rivia.sekiro_kanji.particles.particle_data;

import com.carrot_of_rivia.sekiro_kanji.particles.ParticleRegister;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleType;

public class DangerSignParticleData extends SekiroAbstractParticleData {
    public DangerSignParticleData(double x_shift, double y_shift, double z_shift, int followEntityId) {
        super(x_shift, y_shift, z_shift, followEntityId);
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegister.DANGER_SIGN_PARTICLE.get();
    }

    public static final IDeserializer<DangerSignParticleData> DESERIALIZER = new IDeserializer<DangerSignParticleData>() {
        @Override
        public DangerSignParticleData fromCommand(ParticleType<DangerSignParticleData> particleType, StringReader stringReader) throws CommandSyntaxException {
            // TODO
            return null;
        }

        @Override
        public DangerSignParticleData fromNetwork(ParticleType<DangerSignParticleData> particleType, PacketBuffer packetBuffer) {
            return new DangerSignParticleData(packetBuffer.readDouble(), packetBuffer.readDouble(), packetBuffer.readDouble(), packetBuffer.readInt());
        }
    };

    public static final Codec<DangerSignParticleData> CODEC = RecordCodecBuilder.create((p_239803_0_) ->  {
        return p_239803_0_.group(Codec.DOUBLE.fieldOf("x_shift").forGetter((particleData) -> {
            return particleData.x_shift;
        }), Codec.DOUBLE.fieldOf("y_shift").forGetter((particleData) -> {
            return particleData.y_shift;
        }), Codec.DOUBLE.fieldOf("z_shift").forGetter((particleData) -> {
            return particleData.z_shift;
        }), Codec.INT.fieldOf("followEntityId").forGetter((particleData) -> {
            return particleData.followEntityId;
        })).apply(p_239803_0_, DangerSignParticleData::new);
    });
}
