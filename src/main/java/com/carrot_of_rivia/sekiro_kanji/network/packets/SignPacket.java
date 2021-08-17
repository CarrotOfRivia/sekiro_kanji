package com.carrot_of_rivia.sekiro_kanji.network.packets;

import com.carrot_of_rivia.sekiro_kanji.utils.AddSignAndSoundClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SignPacket {

    private final double x_shift;
    private final double y_shift;
    private final double z_shift;
    private final int followPlayerId;
    private final int signId;

    public SignPacket(double x_shift, double y_shift, double z_shift, int followPlayerId, int signId){
        this.x_shift = x_shift;
        this.y_shift = y_shift;
        this.z_shift = z_shift;
        this.followPlayerId = followPlayerId;
        this.signId = signId;
    }

    public SignPacket(double x_shift, double y_shift, double z_shift, Entity entity, int signId){
        this.x_shift = x_shift;
        this.y_shift = y_shift;
        this.z_shift = z_shift;
        this.followPlayerId = entity.getId();
        this.signId = signId;
    }

    public static SignPacket decode(final PacketBuffer buffer){
        return new SignPacket(buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readInt(), buffer.readInt());
    }

    public void encode(final PacketBuffer buffer){
        buffer.writeDouble(x_shift);
        buffer.writeDouble(y_shift);
        buffer.writeDouble(z_shift);
        buffer.writeInt(followPlayerId);
        buffer.writeInt(signId);
    }

    public static void handle(SignPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            AddSignAndSoundClient.addSignAndSound(packet.x_shift, packet.y_shift, packet.z_shift, packet.followPlayerId, packet.signId);
        }));
        ctx.get().setPacketHandled(true);
    }
}
