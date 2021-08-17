package com.carrot_of_rivia.sekiro_kanji.utils;

import com.carrot_of_rivia.sekiro_kanji.particles.particle_data.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;

public class AddSignAndSoundClient {
    /***
     Client only.
     */
    public static void addSignAndSound(double x_shift, double y_shift, double z_shift, int followPlayerId, int signId){
        Minecraft mc  = Minecraft.getInstance();
        assert mc.level != null;
        Entity entity = mc.level.getEntity(followPlayerId);
        if (entity != null){
            Signs sign = Signs.getSigns(signId);
            switch (sign){
                case DANGER:
                    mc.level.addParticle(new DangerSignParticleData(x_shift, y_shift, z_shift, followPlayerId), entity.xo, entity.yo, entity.zo, 0, 0, 0);
                    break;
                case RESURRECT:
                    mc.level.addParticle(new ResurrectSignParticleData(x_shift, y_shift, z_shift, followPlayerId), entity.xo, entity.yo, entity.zo, 0, 0, 0);
                    break;
                case BURN:
                    mc.level.addParticle(new BurnSignParticleData(x_shift, y_shift, z_shift, followPlayerId), entity.xo, entity.yo, entity.zo, 0, 0, 0);
                    break;
                case POISON:
                    mc.level.addParticle(new PoisonSignParticleData(x_shift, y_shift, z_shift, followPlayerId), entity.xo, entity.yo, entity.zo, 0, 0, 0);
                    break;
                case ENEMY_ALERT:
                    mc.level.addParticle(new EnemyAlertSignParticleData(x_shift, y_shift, z_shift, followPlayerId), entity.xo, entity.yo, entity.zo, 0, 0, 0);
                    break;
            }
        }
    }
}
