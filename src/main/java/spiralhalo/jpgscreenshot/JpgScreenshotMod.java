package spiralhalo.jpgscreenshot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;

public class JpgScreenshotMod implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("jpgscreenshot");
    public static final int QUALITY = 80; // 1-100

    public static final KeyMapping PNG_HOLD_DOWN = new KeyMapping("key.jpgscreenshot.png_hold_down", InputConstants.KEY_LALT, "key.jpgscreenshot.category");

    @Override
    public void onInitializeClient() {
        try {
            KeyBindingHelper.registerKeyBinding(PNG_HOLD_DOWN);
        } catch (Throwable ignored) {
        }

        LOGGER.info("JPG Screenshot is active.");
    }
}
