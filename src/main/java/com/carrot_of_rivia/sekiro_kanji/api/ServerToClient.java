package com.carrot_of_rivia.sekiro_kanji.api;

import com.carrot_of_rivia.sekiro_kanji.network.Channel;
import com.carrot_of_rivia.sekiro_kanji.network.packets.SignPacket;
import com.carrot_of_rivia.sekiro_kanji.utils.SekiroSoundEvents;
import com.carrot_of_rivia.sekiro_kanji.utils.Signs;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.network.PacketDistributor;

public class ServerToClient {
    /*
     Send a packet from server to client side to draw the signs and possibly play sound effects
     YOU MUST CALL IT FROM A SERVER SIDE!
     */
    public static void sendDangerSign(Entity entity){
        // Send a danger sign following a entity (on top of it)
        assert !entity.level.isClientSide();
        Channel.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SignPacket(0, 1, 0, entity.getId(), Signs.DANGER.getIndex()));
        entity.playSound(SekiroSoundEvents.DANGER.get(), 1, 1);

    }
    public static void sendResurrectSign(Entity entity){
        // Send a danger sign following a entity (on top of it)
        assert !entity.level.isClientSide();
        Channel.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SignPacket(0, 1, 0, entity.getId(), Signs.RESURRECT.getIndex()));
    }
    public static void sendPoisonSign(Entity entity){
        // Send a danger sign following a entity (on top of it)
        assert !entity.level.isClientSide();
        Channel.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SignPacket(0, 1, 0, entity.getId(), Signs.POISON.getIndex()));
    }
    public static void sendBurnSign(Entity entity){
        // Send a danger sign following a entity (on top of it)
        assert !entity.level.isClientSide();
        Channel.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SignPacket(0, 1.5, 0, entity.getId(), Signs.BURN.getIndex()));
    }
    public static void sendEnemyAlertSign(Entity entity){
        // Send a danger sign following a entity (on top of it)
        assert !entity.level.isClientSide();
        Channel.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new SignPacket(0, 1, 0, entity.getId(), Signs.ENEMY_ALERT.getIndex()));
        entity.playSound(SekiroSoundEvents.ENEMY_ALERT.get(), 0.5f, 1);
    }
}
