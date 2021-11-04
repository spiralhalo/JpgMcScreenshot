package spiralhalo.jpgscreenshot.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import org.lwjgl.stb.STBImageWrite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spiralhalo.jpgscreenshot.JpgScreenshotMod;

import java.nio.file.Path;

@Mixin(NativeImage.class)
public class MixinNativeImage {

    private boolean writeToJpg;

    @Inject(method = "writeToFile(Ljava/nio/file/Path;)V", at = @At(value = "HEAD"))
    public void startWritingToFile(Path path, CallbackInfo ci) {
        if (path.toString().endsWith(".jpg")) {
            writeToJpg = true;
        }
    }

    @Redirect(method = "writeToChannel", at = @At(value = "INVOKE", target = "Lorg/lwjgl/stb/STBImageWrite;nstbi_write_png_to_func(JJIIIJI)I"))
    private int writeJpg(long func, long context, int w, int h, int comp, long data, int stride_in_bytes) {
        if (writeToJpg) {
            writeToJpg = false;
            return STBImageWrite.nstbi_write_jpg_to_func(func, context, w, h, comp, data, JpgScreenshotMod.QUALITY);
        } else {
            return STBImageWrite.nstbi_write_png_to_func(func, context, w, h, comp, data, stride_in_bytes);
        }
    }

}
