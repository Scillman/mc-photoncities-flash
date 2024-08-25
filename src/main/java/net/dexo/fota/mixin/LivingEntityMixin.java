package net.dexo.fota.mixin;

import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dexo.fota.ModMain;
import net.dexo.fota.registry.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    public long startTime = -1;
    public boolean shouldRender = true;

    private void playSoundOnPlayer(PlayerEntity player) {
        World world = player.getWorld();
        world.playSound(
            player, // Player - if non-null, the sound will play only for this player
            player.getX(), player.getY(), player.getZ(), // The position of the sound
            ModSounds.BELL, // The sound event
            SoundCategory.PLAYERS, // The category of the sound
            1.0f, // Volume
            1.0f // Pitch
        );
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"))
    private void onTotemUse(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof ServerPlayerEntity player) {
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();
            if (mainHand.isOf(Items.TOTEM_OF_UNDYING) || offHand.isOf(Items.TOTEM_OF_UNDYING)) {
//                entity.playSound(MY_SOUND_EVENT);
                playSoundOnPlayer(player);
                LoggerFactory.getLogger(ModMain.MOD_ID).info("WOW!!!");
                shouldRender = true;
                startTime = -1;
            }
        }
    }
}
