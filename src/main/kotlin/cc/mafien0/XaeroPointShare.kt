package cc.mafien0

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import org.slf4j.LoggerFactory

object XaeroPointShare : ModInitializer {
    private val logger = LoggerFactory.getLogger("xaeropointshare")

	override fun onInitialize() {
		val client = Minecraft.getInstance()
		ClientSendMessageEvents.ALLOW_CHAT.register { message -> handleChatMessages(message, client) }
	}

	private fun handleChatMessages(message: String, client: Minecraft): Boolean {

		if (message.startsWith("xaero-waypoint", ignoreCase = true)) {
			logger.info("xaero waypoint detected")
			client.keyboardHandler.setClipboard(message)
			client.player?.sendSystemMessage(
				Component.literal("§7Message copied to clipboard")
			)
			return false
		}

		if (message.startsWith("x%s:", ignoreCase = true)) {
			logger.info("Xaero self waypoint detected")
			client.player?.sendSystemMessage(
				Component.literal(message.substring(4))
			)
			return false
		}

		if (message.startsWith("x%o:", ignoreCase = true)) {
			logger.info("Xaero other waypoint detected")
			return true
		}

		return true
	}
}