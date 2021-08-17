package com.carrot_of_rivia.sekiro_kanji.particles;

import com.carrot_of_rivia.sekiro_kanji.particles.particle_data.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class FollowMotionParticle extends SpriteTexturedParticle {
    private final double x_shift;
    private final double y_shift;
    private final double z_shift;
    private final int followEntityId;
    /*
    A particle that follows you
     */

    public FollowMotionParticle(ClientWorld clientWorld, double xo, double yo, double zo, double x_shift, double y_shift, double z_shift, int followEntityId) {
        super(clientWorld, xo, yo, zo);
        this.x_shift = x_shift;
        this.y_shift = y_shift;
        this.z_shift = z_shift;
        this.followEntityId = followEntityId;
        this.scale(2.f);
        this.setLifetime(30);
    }

    public FollowMotionParticle(){
        this(null, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        assert Minecraft.getInstance().level != null;
        Entity entity = Minecraft.getInstance().level.getEntity(followEntityId);
        if (entity != null){
            Vector3d pos = entity.getEyePosition(1f).add(x_shift, y_shift, z_shift);
            this.x = pos.x;
            this.y = pos.y;
            this.z = pos.z;
        }
    }

    public static class DangerSignFactory implements IParticleFactory<DangerSignParticleData>{

        private final IAnimatedSprite iAnimatedSprite;

        public DangerSignFactory(IAnimatedSprite iAnimatedSprite){
            this.iAnimatedSprite = iAnimatedSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(DangerSignParticleData dangerSignParticleData, ClientWorld clientWorld, double v, double v1, double v2, double v3, double v4, double v5) {
            FollowMotionParticle particle = new FollowMotionParticle(clientWorld, v, v1, v2, dangerSignParticleData.getX_shift(), dangerSignParticleData.getY_shift(), dangerSignParticleData.getZ_shift(), dangerSignParticleData.getFollowEntityId());
            particle.pickSprite(iAnimatedSprite);
            return particle;
        }
    }
    public static class ResurrectSignFactory implements IParticleFactory<ResurrectSignParticleData>{

        private final IAnimatedSprite iAnimatedSprite;

        public ResurrectSignFactory(IAnimatedSprite iAnimatedSprite){
            this.iAnimatedSprite = iAnimatedSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(ResurrectSignParticleData ResurrectSignParticleData, ClientWorld clientWorld, double v, double v1, double v2, double v3, double v4, double v5) {
            FollowMotionParticle particle = new FollowMotionParticle(clientWorld, v, v1, v2, ResurrectSignParticleData.getX_shift(), ResurrectSignParticleData.getY_shift(), ResurrectSignParticleData.getZ_shift(), ResurrectSignParticleData.getFollowEntityId());
            particle.pickSprite(iAnimatedSprite);
            return particle;
        }
    }
    public static class PoisonSignFactory implements IParticleFactory<PoisonSignParticleData>{

        private final IAnimatedSprite iAnimatedSprite;

        public PoisonSignFactory(IAnimatedSprite iAnimatedSprite){
            this.iAnimatedSprite = iAnimatedSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(PoisonSignParticleData PoisonSignParticleData, ClientWorld clientWorld, double v, double v1, double v2, double v3, double v4, double v5) {
            FollowMotionParticle particle = new FollowMotionParticle(clientWorld, v, v1, v2, PoisonSignParticleData.getX_shift(), PoisonSignParticleData.getY_shift(), PoisonSignParticleData.getZ_shift(), PoisonSignParticleData.getFollowEntityId());
            particle.pickSprite(iAnimatedSprite);
            return particle;
        }
    }
    public static class BurnSignFactory implements IParticleFactory<BurnSignParticleData>{

        private final IAnimatedSprite iAnimatedSprite;

        public BurnSignFactory(IAnimatedSprite iAnimatedSprite){
            this.iAnimatedSprite = iAnimatedSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(BurnSignParticleData BurnSignParticleData, ClientWorld clientWorld, double v, double v1, double v2, double v3, double v4, double v5) {
            FollowMotionParticle particle = new FollowMotionParticle(clientWorld, v, v1, v2, BurnSignParticleData.getX_shift(), BurnSignParticleData.getY_shift(), BurnSignParticleData.getZ_shift(), BurnSignParticleData.getFollowEntityId());
            particle.pickSprite(iAnimatedSprite);
            return particle;
        }
    }
    public static class EnemyAlertSignFactory implements IParticleFactory<EnemyAlertSignParticleData>{

        private final IAnimatedSprite iAnimatedSprite;

        public EnemyAlertSignFactory(IAnimatedSprite iAnimatedSprite){
            this.iAnimatedSprite = iAnimatedSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(EnemyAlertSignParticleData EnemyAlertSignParticleData, ClientWorld clientWorld, double v, double v1, double v2, double v3, double v4, double v5) {
            FollowMotionParticle particle = new FollowMotionParticle(clientWorld, v, v1, v2, EnemyAlertSignParticleData.getX_shift(), EnemyAlertSignParticleData.getY_shift(), EnemyAlertSignParticleData.getZ_shift(), EnemyAlertSignParticleData.getFollowEntityId());
            particle.pickSprite(iAnimatedSprite);
            return particle;
        }
    }
}
