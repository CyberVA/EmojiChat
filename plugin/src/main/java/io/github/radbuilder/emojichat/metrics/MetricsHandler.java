package io.github.radbuilder.emojichat.metrics;

import io.github.radbuilder.emojichat.EmojiChat;
import io.github.radbuilder.emojichat.hooks.EmojiChatHook;

import java.util.HashMap;
import java.util.Map;

/**
 * Metrics handler class.
 *
 * @author RadBuilder
 * @since 1.4
 */
public class MetricsHandler {
	/**
	 * The number of emojis used.
	 */
	private int emojisUsed;
	
	/**
	 * Creates the metrics handler class with the main class instance.
	 *
	 * @param plugin The EmojiChat main class instance.
	 */
	public MetricsHandler(EmojiChat plugin) {
		Metrics metrics = new Metrics(plugin); // Start Metrics
		
		metrics.addCustomChart(new Metrics.SingleLineChart("emojisUsed", () -> {
			int temp = emojisUsed;
			emojisUsed = 0; // Reset the number of emojis used when this is called
			return temp;
		}));
		
		metrics.addCustomChart(new Metrics.AdvancedPie("usedHooks", () -> {
			Map<String, Integer> usedHooksMap = new HashMap<>();
			if (plugin.getEnabledHooks().size() < 1) {
				usedHooksMap.put("None", 1);
			} else {
				for (EmojiChatHook hook : plugin.getEnabledHooks()) {
					usedHooksMap.put(hook.getName(), 1);
				}
			}
			return usedHooksMap;
		}));
	}
	
	/**
	 * Adds the specified number of emojis used to {@link #emojisUsed}.
	 *
	 * @param emojisUsed The number of emojis used to add to {@link #emojisUsed}.
	 */
	public void addEmojiUsed(int emojisUsed) {
		this.emojisUsed += emojisUsed;
	}
}