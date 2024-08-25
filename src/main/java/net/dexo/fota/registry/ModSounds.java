package net.dexo.fota.registry;

import net.dexo.fota.ModMain;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

/**
 * Contains all the custom sounds in the mod.
 */
public final class ModSounds {

    public static final SoundEvent BELL = register("bell");

    /**
     * Register a sound event.
     * @param name The name of the sound file.
     * @return The sound event as registered.
     */
    private static SoundEvent register(String name) {
        Identifier id = new Identifier(ModMain.MOD_ID, name) ;
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    /**
     * Registers everything related to sounds.
     * @remarks Must be called even when empty.
     */
    public static void register() {
    }
}
