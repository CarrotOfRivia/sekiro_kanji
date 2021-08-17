package com.carrot_of_rivia.sekiro_kanji.events;

import com.carrot_of_rivia.sekiro_kanji.SekiroKanji;
import com.carrot_of_rivia.sekiro_kanji.utils.ConfigCommon;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class ForgeBusClient {
    private static final ResourceLocation NOOB = new ResourceLocation(SekiroKanji.MOD_ID, "textures/gui/noob.png");
    private static final ResourceLocation DEATH = new ResourceLocation(SekiroKanji.MOD_ID, "textures/gui/death.png");
    private static final Random random = new Random();
    private static double result=-1;

    @SubscribeEvent
    public void onDrawGui(final GuiScreenEvent.DrawScreenEvent.Post event){
        if(!ConfigCommon.DISABLE_DEATH_SCREEN.get() && event.getGui() instanceof DeathScreen){
            Minecraft mc = Minecraft.getInstance();
            if(((DeathScreen) event.getGui()).delayTicker == 0){
                result = random.nextDouble();
            }

            int showDeathTime = ConfigCommon.SHOW_DEATH_TIME.get();
            boolean showNoob = result < ConfigCommon.NOOB_CHANCE.get();
            if (showNoob){
                showDeathTime += 50;
            }

            if(((DeathScreen) event.getGui()).delayTicker <= showDeathTime){
                if(showNoob){
                    mc.getEntityRenderDispatcher().textureManager.bind(NOOB);
                }
                else {
                    mc.getEntityRenderDispatcher().textureManager.bind(DEATH);
                }

                assert mc.screen != null;
                MatrixStack matrixStack = event.getMatrixStack();
                matrixStack.pushPose();
                float scale = 0.8f;
                matrixStack.scale(scale, scale, scale);
                mc.screen.blit(matrixStack, mc.screen.width/2 - (int)(80), mc.screen.height/2 - (int)(100), 0, 0, 256, 256);
                matrixStack.popPose();
            }
        }
    }
}
