package dev.glassplayer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(
        method = "updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V",
        at = @At("TAIL")
    )
    private void glassplayer$makeSelfGlass(AbstractClientPlayerEntity player,
                                           PlayerEntityRenderState state,
                                           float tickProgress,
                                           CallbackInfo ci) {
        if (player == MinecraftClient.getInstance().player) {
            state.invisible = true;
            state.invisibleToPlayer = true;
            state.outlineColor = 0xFFFFFFFF;
        }
    }
}
