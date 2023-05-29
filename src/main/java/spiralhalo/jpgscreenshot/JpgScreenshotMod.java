package spiralhalo.jpgscreenshot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;

public class JpgScreenshotMod implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("jpgscreenshot");
    public static final int QUALITY = 80; // 1-100

    public static final KeyMapping PNG_HOLD_DOWN;

    static {
        final boolean devEnv = FabricLoader.getInstance().isDevelopmentEnvironment();

        final String langKey;
        final String langCat;

        if (FabricLoader.getInstance().isModLoaded("fabric-resource-loader-v0")) {
            langKey = "key.jpgscreenshot.png_hold_down";
            langCat = "key.jpgscreenshot.category";

            if (devEnv) {
                LOGGER.info("Fabric resource loader is present.");
            }
        } else {
            langKey = "Hold to Save as .png";
            langCat = "JPG Screenshot";

            LOGGER.info("JPG Screenshot couldn't find fabric resource loader. Falling back to English");
        }

        PNG_HOLD_DOWN = new KeyMapping(langKey, InputConstants.KEY_LALT, langCat);
    }

    @Override
    public void onInitializeClient() {
        try {
            KeyBindingHelper.registerKeyBinding(PNG_HOLD_DOWN);
        } catch (Throwable ignored) {
            // LOGGER.info("JPG Screenshot was unable to register key binding. This is optional and requires Fabric API");
        }

        LOGGER.info("JPG Screenshot is active.");
    }
}
