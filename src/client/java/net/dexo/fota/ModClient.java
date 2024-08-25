package net.dexo.fota;

import net.dexo.fota.client.screen.HudOverlayRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ModClient implements ClientModInitializer {

    private HudOverlayRenderer overlayRenderer;

    @Override
    public void onInitializeClient() {
        this.overlayRenderer = new HudOverlayRenderer();
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            this.overlayRenderer.render(drawContext, tickDelta);
        });
    }
}
