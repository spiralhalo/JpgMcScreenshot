package spiralhalo.jpgscreenshot.mixin;

import net.minecraft.client.Screenshot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;

@Mixin(Screenshot.class)
public class MixinScreenshot {
    @Redirect(method = "getFile", at = @At(value = "NEW", target = "java/io/File" ), require = 1)
    private static File createFile(File parent, String child) {
        return new File(parent, child.replace(".png", ".jpg"));
    }
}
