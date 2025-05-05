package me.MaurycyJaniak.GSSBot.commands.handlers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class SetupAllianceCommandHandler implements CommandHandler{
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        Category category = event.getGuild().getCategoryById(1094061474705580062L);
        OptionMapping allianceName = event.getOption("alliancename");
        OptionMapping allianceFounder = event.getOption("alliancefounder");

        User founder = allianceFounder.getAsUser();
        String allianceNameAsString = allianceName.getAsString();

        if (!event.getGuild().getTextChannelsByName(allianceNameAsString, true).isEmpty()) {
            event.reply("Channel already exists!").setEphemeral(true).queue();
            return;
        } else if (!event.getGuild().getRolesByName(allianceNameAsString + " | Alliance", true).isEmpty()) {
            event.reply("Role already exists!").setEphemeral(true).queue();
            return;
        } else {

            //Alliance Role and Founder Assigning
            String allianceNameAsStringClean = allianceNameAsString.replace(" ", "") + " | Alliance";
            event.getGuild().createRole().setName(allianceNameAsStringClean).queue();

            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Role allianceRole = event.getGuild().getRolesByName(allianceNameAsStringClean, true).get(0);
            event.getGuild().addRoleToMember(founder, allianceRole).queue();

            //Secret Alliance Channel
            event.getGuild().createTextChannel(allianceNameAsString, category)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)).queue(); // Denies view permission to @everyone

            allianceNameAsString.replaceAll(" ", "");

            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            TextChannel channel = event.getGuild().getTextChannelsByName(allianceNameAsString, true).get(0);
            channel.upsertPermissionOverride(allianceRole).grant(
                            Permission.VIEW_CHANNEL,
                            Permission.MESSAGE_SEND,
                            Permission.MESSAGE_HISTORY)
                    .queue();
            channel.sendMessage(founder.getAsMention() + " has created this alliance!").queue();

            //Public Alliance Forum Channel
            Role member = event.getGuild().getRolesByName("Member", true).get(0);

            event.getGuild().createForumChannel(allianceNameAsString, category)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)).queue();

            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ForumChannel forumChannel = event.getGuild().getForumChannelsByName(allianceNameAsString, true).get(0);
            forumChannel.upsertPermissionOverride(member).grant(
                            Permission.VIEW_CHANNEL,
                            Permission.MESSAGE_HISTORY)
                    .queue();

            forumChannel.upsertPermissionOverride(allianceRole).grant(
                            Permission.VIEW_CHANNEL,          // View Channel
                            Permission.MANAGE_CHANNEL,        // Manage Channel
                            Permission.MESSAGE_SEND,          // Send Messages in Posts
                            Permission.MESSAGE_ATTACH_FILES,  // Attach Files
                            Permission.MESSAGE_ADD_REACTION,  // Add Reactions
                            Permission.MESSAGE_MANAGE,        // Manage Messages
                            Permission.MANAGE_THREADS,        // Manage Posts
                            Permission.MESSAGE_HISTORY,       // Read Post History
                            Permission.CREATE_PUBLIC_THREADS  // Create Posts (threads)
                    )
                    .queue();
        }

        event.reply("Alliance " + allianceNameAsString + " has been created!").setEphemeral(true).queue();

    }
}
