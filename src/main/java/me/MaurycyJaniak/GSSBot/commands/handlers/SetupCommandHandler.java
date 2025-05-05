package me.MaurycyJaniak.GSSBot.commands.handlers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.Arrays;
import java.util.Collections;

public class SetupCommandHandler implements CommandHandler{
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.reply("You don't have permission to use this command!")
                    .setEphemeral(true)
                    .queue();
        } else {

            Category category = event.getGuild().getCategoryById(1323089559390781460L);

            //Get data from command options
            OptionMapping playerOption = event.getOption("player");

            OptionMapping channelnameOption = event.getOption("channelname");
            String channelname = channelnameOption.getAsString();

            String typeOfNation = "";

            if (playerOption != null) {
                typeOfNation = " | Player";
            } else if (playerOption == null) {
                typeOfNation = " | NPC";
            }

            //Create role
            event.getGuild().createRole().setName(channelname + typeOfNation).queue(role -> {

                if (playerOption != null) {
                    // Add the role to the user
                    event.getGuild().addRoleToMember(playerOption.getAsUser(), role).queue();
                    Role roleNation = event.getGuild().getRolesByName("Nation", false).get(0);
                    event.getGuild().addRoleToMember(playerOption.getAsUser(), roleNation).queue();
                }

                // Create a new forum channel with the role
                event.getGuild().createForumChannel(channelname, category)
                        .addPermissionOverride(role, Arrays.asList(
                                Permission.VIEW_CHANNEL,          // View Channel
                                Permission.MANAGE_CHANNEL,        // Manage Channel
                                Permission.MESSAGE_SEND,          // Send Messages in Posts
                                Permission.MESSAGE_ATTACH_FILES,  // Attach Files
                                Permission.MESSAGE_ADD_REACTION,  // Add Reactions
                                Permission.MESSAGE_MANAGE,        // Manage Messages
                                Permission.MANAGE_THREADS,        // Manage Posts
                                Permission.MESSAGE_HISTORY,       // Read Post History
                                Permission.CREATE_PUBLIC_THREADS  // Create Posts (threads)
                        ), Collections.emptyList())
                        .queue(forumChannel -> {
                            MessageCreateData message = MessageCreateData.fromContent("Welcome to your new channel! " + " " + role.getAsMention());
                            forumChannel.createForumPost("Welcome to your new channel!", message).queue();
                        });

                if (playerOption != null) {
                    event.reply("Channel created for " + playerOption.getAsUser().getAsMention() + " with role " + role.getAsMention() + " and permissions set.")
                            .setEphemeral(true)
                            .queue();
                } else if (playerOption == null) {
                    event.reply("Channel created for " + channelname + " NPC" + " with role " + role.getAsMention() + " and permissions set.")
                            .setEphemeral(true)
                            .queue();
                }

            });
        }
    }
}
