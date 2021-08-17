package com.carrot_of_rivia.sekiro_kanji.particles.particle_data;

import com.carrot_of_rivia.sekiro_kanji.particles.ParticleRegister;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public abstract class SekiroAbstractParticleData implements IParticleData {


    protected final double x_shift;
    protected final double y_shift;
    protected final double z_shift;
    protected final int followEntityId;

    public SekiroAbstractParticleData(double x_shift, double y_shift, double z_shift, int followEntityId){

        this.x_shift = x_shift;
        this.y_shift = y_shift;
        this.z_shift = z_shift;
        this.followEntityId = followEntityId;
    }

    @Override
    public void writeToNetwork(PacketBuffer packetBuffer) {
        packetBuffer.writeDouble(x_shift);
        packetBuffer.writeDouble(y_shift);
        packetBuffer.writeDouble(z_shift);
        packetBuffer.writeInt(followEntityId);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", Registry.PARTICLE_TYPE.getKey(this.getType()), this.x_shift, this.y_shift, this.z_shift, this.followEntityId);
    }

    public double getX_shift() {
        return x_shift;
    }

    public double getY_shift() {
        return y_shift;
    }

    public double getZ_shift() {
        return z_shift;
    }

    public int getFollowEntityId() {
        return followEntityId;
    }
}
