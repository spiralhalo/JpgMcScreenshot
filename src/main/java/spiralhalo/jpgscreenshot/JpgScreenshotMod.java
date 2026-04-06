package spiralhalo.jpgscreenshot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class JpgScreenshotMod implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("jpgscreenshot");
    public static final int QUALITY = 90; // 1-100

    private static Object PNG_HOLD_DOWN = null;

    public static boolean saveAsPng() {
        try {
            return ((KeyMapping) JpgScreenshotMod.PNG_HOLD_DOWN).isDown();
        } catch (Throwable ignored) {
            return false;
        }
    }

    @Override
    public void onInitializeClient() {
        boolean keymapping_made = false;
        try {
            final String langKey = "key.jpgscreenshot.png_hold_down";
            final Identifier langCat = Identifier.fromNamespaceAndPath("jpgscreenshot", "keybinding");
            PNG_HOLD_DOWN = new KeyMapping(langKey, InputConstants.KEY_LCONTROL, KeyMapping.Category.register(langCat));
            keymapping_made = true;
            KeyMappingHelper.registerKeyMapping((KeyMapping) PNG_HOLD_DOWN);
        } catch (Throwable ignored) {
            if (keymapping_made) {
                LOGGER.info("JPG Screenshot was unable to register key binding. This is optional and requires Fabric API.");
            } else {
                LOGGER.info("JPG Screenshot failed to register key binding in this Minecraft version.");
            }
        }

        LOGGER.info("JPG Screenshot is active.");
    }
}
