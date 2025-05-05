package me.MaurycyJaniak.GSSBot.commands.handlers;

import me.MaurycyJaniak.GSSBot.utils.SendToSheet;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApproveMessageInteractionHandler {

    private final SendToSheet sendToSheet;

    public ApproveMessageInteractionHandler(SendToSheet sendToSheet) {
        this.sendToSheet = sendToSheet;
    }

    public void handle(MessageContextInteractionEvent event) {
        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.getHook().editOriginal("You don't have permission to use this command!")
                    .queue();
            return;

        }

        event.deferReply(true).queue();

        Message targetMessage = event.getTarget();
        String messageContent = targetMessage.getContentRaw();
        List<Message.Attachment> attachments = targetMessage.getAttachments();
        List<String> imageUrls = new ArrayList<>();

        StringBuilder response = new StringBuilder()
                .append("Message from: ").append(targetMessage.getAuthor().getAsMention()).append("\n")
                .append("Content: ").append(targetMessage.getContentRaw()).append("\n");

        // Get URLs of image attachments
        for (Message.Attachment attachment : attachments) {
            if (attachment.isImage()) {  // Checks if the attachment is an image
                imageUrls.add(attachment.getUrl());
            }
        }

        if (!imageUrls.isEmpty()) {
            response.append("Images found:\n");
            for (String url : imageUrls) {
                response.append("- ").append(url).append("\n");
            }
        }

        Channel channel = event.getChannel();

        String submissionCategory = null;

        Map<String, String> channelCategoryMap = Map.of(
                "citizen-submission", "Citizen",
                "investment-submission", "Investment",
                "company-submission", "Company",
                "company-action-submission", "CompanyAction",
                "tech-submission", "Technology",
                "requests", "DiplomaticAction"
        );

        submissionCategory = channelCategoryMap.getOrDefault(channel.getName().toLowerCase(), "misc");

        // Create JSON Payload
        JSONObject payload = new JSONObject();
        payload.put("type", "submission");
        payload.put("category", submissionCategory);
        payload.put("author", targetMessage.getAuthor().getName());
        payload.put("content", messageContent);
        payload.put("imageUrls", String.join(",", imageUrls));
        payload.put("appovedBy", event.getUser().getName());

        // Send to Google Sheets via Apps Script
        try {
            int responseCode = sendToSheet.send(payload);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                event.getHook().editOriginal("Submission approved and logged to the spreadsheet" + messageContent)
                        .queue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.getHook().editOriginal("Error submitting to sheet: " + e.getMessage()).queue();
        }
    }
}
