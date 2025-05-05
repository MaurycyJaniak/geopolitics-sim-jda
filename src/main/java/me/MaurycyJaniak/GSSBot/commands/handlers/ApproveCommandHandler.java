package me.MaurycyJaniak.GSSBot.commands.handlers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ApproveCommandHandler implements CommandHandler {
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.reply("You don't have permission to use this command!")
                    .setEphemeral(true)
                    .queue();
            return;

        }

        // Get the message content from the option
        String messageContent = event.getOption("message").getAsString();

        // For now, let's just acknowledge the approval
        event.reply("Message approved: " + messageContent)
                .setEphemeral(true)
                .queue();
    }
}
