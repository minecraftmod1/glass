package dev.glassplayer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(
        method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
        at = @At("TAIL")
    )
    private void glassplayer$makeSelfGlass(LivingEntity entity,
                                           LivingEntityRenderState state,
                                           float tickProgress,
                                           CallbackInfo ci) {
        if (entity == MinecraftClient.getInstance().player) {
            state.invisible = true;
            state.invisibleToPlayer = true;
            state.outlineColor = 0xFFFFFFFF;
        }
    }
}
