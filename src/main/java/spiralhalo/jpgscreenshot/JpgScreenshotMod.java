package spiralhalo.jpgscreenshot;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JpgScreenshotMod implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("jpgscreenshot");
    public static final int QUALITY = 80; // 1-100

    @Override
    public void onInitializeClient() {
        LOGGER.info("JPG Screenshot is active.");
    }
}
