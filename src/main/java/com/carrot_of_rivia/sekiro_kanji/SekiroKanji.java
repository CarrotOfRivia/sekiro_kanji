package com.carrot_of_rivia.sekiro_kanji;

import com.carrot_of_rivia.sekiro_kanji.data.SekiroKanjiCap;
import com.carrot_of_rivia.sekiro_kanji.events.ForgeBus;
import com.carrot_of_rivia.sekiro_kanji.events.ForgeBusClient;
import com.carrot_of_rivia.sekiro_kanji.network.Channel;
import com.carrot_of_rivia.sekiro_kanji.network.packets.SignPacket;
import com.carrot_of_rivia.sekiro_kanji.particles.FollowMotionParticle;
import com.carrot_of_rivia.sekiro_kanji.particles.ParticleRegister;
import com.carrot_of_rivia.sekiro_kanji.utils.ConfigCommon;
import com.carrot_of_rivia.sekiro_kanji.utils.SekiroSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SekiroKanji.MOD_ID)
public class SekiroKanji
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sekiro_kanji";

    public SekiroKanji() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeBus());
        MinecraftForge.EVENT_BUS.register(new ForgeBusClient());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigCommon.COMMON);

        ParticleRegister.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SekiroSoundEvents.SOUND_EVENT.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        int idx = 0;
        Channel.INSTANCE.registerMessage(idx++, SignPacket.class, SignPacket::encode, SignPacket::decode, SignPacket::handle);

        SekiroKanjiCap.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }

        @SubscribeEvent
        public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event){
            Minecraft.getInstance().particleEngine.register(ParticleRegister.DANGER_SIGN_PARTICLE.get(), FollowMotionParticle.DangerSignFactory::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegister.RESURRECT_SIGN_PARTICLE.get(), FollowMotionParticle.ResurrectSignFactory::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegister.POISON_SIGN_PARTICLE.get(), FollowMotionParticle.PoisonSignFactory::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegister.BURN_SIGN_PARTICLE.get(), FollowMotionParticle.BurnSignFactory::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegister.ENEMY_ALERT_SIGN_PARTICLE.get(), FollowMotionParticle.EnemyAlertSignFactory::new);
        }
    }
}
