package net.dexo.fota.client.screen;

import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.systems.RenderSystem;

import net.dexo.fota.ModMain;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;

public class HudOverlayRenderer {

    public static final Identifier IMAGE_LOCATION = new Identifier(ModMain.MOD_ID, "textures/afterlife.png");
    public static final int FADE_DURATION = 10; // Duration of fade in ticks (3 seconds at 20 ticks per second)
    public static final int DISPLAY_DURATION = 0; // Duration to display before fading (1 second)

    public long startTime;
    public boolean shouldRender;

    public HudOverlayRenderer() {
        this.startTime = -1;
        this.shouldRender = true;
    }

    public void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        Window window = client.getWindow();
        int screenWidth = window.getScaledWidth();
        int screenHeight = window.getScaledHeight();

        long currentTime = client.world.getTime();
        if (startTime == -1) {
            startTime = currentTime;
        }

        long elapsedTime = currentTime - startTime;

        if (elapsedTime > DISPLAY_DURATION + FADE_DURATION) {
            shouldRender = false;
            return;
        }

        int imageWidth = 64;  // Replace with your image's width
        int imageHeight = 64; // Replace with your image's height
        int x = (screenWidth - imageWidth) / 2;
        int y = (screenHeight - imageHeight) / 2;

        float alpha = 1.0f;
        if (elapsedTime > DISPLAY_DURATION) {
            alpha = 1.0f - (float) (double) ((elapsedTime - DISPLAY_DURATION) / FADE_DURATION);
            LoggerFactory.getLogger(ModMain.MOD_ID).info(String.valueOf(alpha));
        }

        RenderSystem.enableBlend();
        drawContext.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        drawContext.drawTexture(IMAGE_LOCATION, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
    }
}
