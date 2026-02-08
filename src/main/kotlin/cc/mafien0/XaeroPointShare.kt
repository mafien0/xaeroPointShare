package cc.mafien0

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents
import net.minecraft.client.MinecraftClient
import org.slf4j.LoggerFactory

object XaeroPointShare : ModInitializer {
    private val logger = LoggerFactory.getLogger("xaeropointshare")

	override fun onInitialize() {
		val client = MinecraftClient.getInstance()
		ClientSendMessageEvents.ALLOW_CHAT.register { message -> handleChatMessages(message, client) }
	}

	private fun handleChatMessages(message: String, client: MinecraftClient): Boolean {

		// Check if its xaero waypoint
		// If it is, don't message send and just copy it
		if (message.startsWith("xaero-waypoint", ignoreCase = true)) {
			logger.info("xaero waypoint detected")
			client.keyboard.clipboard = message
			client.player?.sendMessage(
				net.minecraft.text.Text.literal("§7Message copied to clipboard"),
				false
			)
			return false
		}

		// Check if its self waypoint
		// If it is, send it to yourself
		if (message.startsWith("x%s:", ignoreCase = true)) {
			logger.info("Xaero self waypoint detected")
			client.player?.sendMessage(
				net.minecraft.text.Text.literal(message.substring(4)),
				false
			)
			return false
		}

		// Check if its other waypoint
		// If it is, send it as a regular waypoint, visible to everyone
		// Xaero ignores start of a message, so we just don't do anything. If it Works - don't touch it
		if (message.startsWith("x%o:", ignoreCase = true)) {
			logger.info("Xaero other waypoint detected")
			return true
		}

		// If its just a message, send it
		return true
	}
}