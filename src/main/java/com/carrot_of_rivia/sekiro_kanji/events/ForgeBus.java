package com.carrot_of_rivia.sekiro_kanji.events;

import com.carrot_of_rivia.sekiro_kanji.api.ServerToClient;
import com.carrot_of_rivia.sekiro_kanji.data.SekiroKanjiCap;
import com.carrot_of_rivia.sekiro_kanji.data.SekiroKanjiData;
import com.carrot_of_rivia.sekiro_kanji.utils.ConfigCommon;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeBus {
    @SubscribeEvent
    public void onAttachCap(final AttachCapabilitiesEvent<Entity> event){
        Entity entity = event.getObject();
        if(entity instanceof LivingEntity){
            SekiroKanjiCap cap = new SekiroKanjiCap();
            event.addCapability(SekiroKanjiCap.SEKIRO_CAP, cap);
            event.addListener(cap::invalidate);
        }
    }

    @SubscribeEvent
    public void onLivingEntity(final LivingEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if(livingEntity!=null && ! (livingEntity.level.isClientSide())){
            if (livingEntity instanceof CreeperEntity && !ConfigCommon.DISABLE_CREEPER_EXPLODE_DANGER_SIGN.get()){
                // if a creeper is trying to explode, send danger sign to the nearest player
                if(((CreeperEntity) livingEntity).getSwellDir() > 0){
                    PlayerEntity playerEntity = livingEntity.level.getNearestPlayer(livingEntity, 5);
                    if (playerEntity != null){
                        SekiroKanjiData sekiroKanjiData = livingEntity.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                        if(sekiroKanjiData.canSendDangerSign()){
                            ServerToClient.sendDangerSign(playerEntity);
                            sekiroKanjiData.setDangerMax();
                        }
                    }
                }
            }

            if(!ConfigCommon.DISABLE_POISON_SIGN.get() && livingEntity.hasEffect(Effects.POISON)){
                SekiroKanjiData livingData = livingEntity.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                if(livingData.canSendPoisonSign()) {
                    ServerToClient.sendPoisonSign(livingEntity);
                    livingData.setPoisonMax();
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent event){
        PlayerEntity playerEntity = event.player;
        if(! (playerEntity.level.isClientSide())){
            SekiroKanjiData sekiroKanjiData = playerEntity.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
            sekiroKanjiData.tickCD();

            if(!ConfigCommon.DISABLE_FALL_DANGER_SIGN.get() && !playerEntity.abilities.flying && playerEntity.fallDistance > 0.2f && sekiroKanjiData.canSendDangerSign()){
                // falling sense: if player is to fall 10 blocks
                int distThreshold = 10;
                boolean send = true;
                for (int dy = 1; dy <= distThreshold; dy++){
                    if (playerEntity.level.getBlockState(playerEntity.blockPosition().below(dy)).getBlock() != Blocks.AIR){
                        send = false;
                    }
                }
                if(send){
                    ServerToClient.sendDangerSign(playerEntity);
                    sekiroKanjiData.setDangerMax();
                }
            }
        }
    }


    @SubscribeEvent
    public void onLivingAttack(final LivingSetAttackTargetEvent event){
        LivingEntity attacker = event.getEntityLiving();
        LivingEntity target = event.getTarget();
        if(target instanceof PlayerEntity && !(target.level.isClientSide())){
            if((attacker instanceof CreeperEntity && !ConfigCommon.DISABLE_CREEPER_TRACK_DANGER_SIGN.get()) || (attacker instanceof EndermanEntity && !ConfigCommon.DISABLE_ENDERMAN_TRACK_DANGER_SIGN.get())){
                // if a creeper is trying to attack a player, send danger sign to the player
                SekiroKanjiData sekiroKanjiData = attacker.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                if(sekiroKanjiData.canSendDangerSign()){
                    ServerToClient.sendDangerSign(target);
                    sekiroKanjiData.setDangerMax();
                }
            }

            if(!ConfigCommon.DISABLE_ENEMY_ALERT_SIGN.get()){
                SekiroKanjiData attackerData = attacker.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                if(attackerData.canSendEnemyAlertSign()){
                    ServerToClient.sendEnemyAlertSign(attacker);
                    attackerData.setEnemyAlertMax();
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityHurt(final LivingHurtEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if(!ConfigCommon.DISABLE_BURN_SIGN.get() && livingEntity != null && ! livingEntity.level.isClientSide()){
            DamageSource damageSource = event.getSource();
            if(damageSource.isFire()){
                SekiroKanjiData livingData = livingEntity.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                if(livingData.canSendBurnSign()){
                    ServerToClient.sendBurnSign(livingEntity);
                    livingData.setBurnMax();
                }
            }
        }
    }

    @SubscribeEvent
    public void onEquipmentChange(final LivingEquipmentChangeEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if(!ConfigCommon.DISABLE_RESURRECT_SIGN.get() && livingEntity != null && ! livingEntity.level.isClientSide()){
            if(event.getFrom().getItem() == Items.TOTEM_OF_UNDYING){
                EquipmentSlotType slotType = event.getSlot();
                if(slotType == EquipmentSlotType.MAINHAND || slotType == EquipmentSlotType.OFFHAND){
                    if(livingEntity.hasEffect(Effects.ABSORPTION) && livingEntity.hasEffect(Effects.FIRE_RESISTANCE) && livingEntity.hasEffect(Effects.REGENERATION)){
                        SekiroKanjiData livingData = livingEntity.getCapability(SekiroKanjiCap.SEKIRO_KANJI_CAP).orElse(new SekiroKanjiData());
                        if(livingData.canSendResurrectSign()){
                            ServerToClient.sendResurrectSign(livingEntity);
                            livingData.setResurrectMax();
                        }
                    }
                }
            }
        }
    }

}
