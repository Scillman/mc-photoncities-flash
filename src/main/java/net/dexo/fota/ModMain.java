package net.dexo.fota;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dexo.fota.registry.ModSounds;
import net.fabricmc.api.ModInitializer;

public class ModMain implements ModInitializer {

    public static final String MOD_ID = "fota";
    public static final String MOD_NAME = "Flash of the Afterlife";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    /**
     * Mod entry point
     */
    @Override
    public void onInitialize() {
        ModSounds.register();
    }
}
