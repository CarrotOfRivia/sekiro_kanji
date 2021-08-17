package com.carrot_of_rivia.sekiro_kanji.network;

import com.carrot_of_rivia.sekiro_kanji.SekiroKanji;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Channel {
    private static final String PROTOCOL_VERSION = "sekiro_kanji";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(SekiroKanji.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
}
