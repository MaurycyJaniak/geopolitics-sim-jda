package me.MaurycyJaniak.GSSBot.commands.handlers;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public class RolesCommandHandler implements CommandHandler {
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String reply = "";
        List<Role> roles = event.getGuild().getRoles();

        for (int i = 0; i < roles.size(); i++) {
            reply += roles.get(i).getAsMention() + "\n";
        }

        event.reply(reply).queue();
    }
}
