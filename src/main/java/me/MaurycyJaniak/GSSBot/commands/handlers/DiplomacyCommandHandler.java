package me.MaurycyJaniak.GSSBot.commands.handlers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DiplomacyCommandHandler implements CommandHandler {
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String replyMessage = "";
        Category category = event.getGuild().getCategoryById(1091026049929854986L);

        List<Role> nationroles = new ArrayList<>();

        OptionMapping nationRole1 = event.getOption("nationrole1");
        Role role1 = nationRole1.getAsRole();
        nationroles.add(role1);

        OptionMapping nationRole2 = event.getOption("nationrole2");
        if (nationRole2 != null) {
            Role role2 = nationRole2.getAsRole();
            nationroles.add(role2);
        }

        OptionMapping nationRole3 = event.getOption("nationrole3");
        if (nationRole3 != null) {
            Role role3 = nationRole3.getAsRole();
            nationroles.add(role3);
        }

        OptionMapping nationRole4 = event.getOption("nationrole4");
        if (nationRole4 != null) {
            Role role4 = nationRole4.getAsRole();
            nationroles.add(role4);
        }

        OptionMapping nationRole5 = event.getOption("nationrole5");
        if (nationRole5 != null) {
            Role role5 = nationRole5.getAsRole();
            nationroles.add(role5);
        }

        OptionMapping nationRole6 = event.getOption("nationrole6");
        if (nationRole6 != null) {
            Role role6 = nationRole6.getAsRole();
            nationroles.add(role6);
        }

        OptionMapping nationRole7 = event.getOption("nationrole7");
        if (nationRole7 != null) {
            Role role7 = nationRole7.getAsRole();
            nationroles.add(role7);
        }

        OptionMapping nationRole8 = event.getOption("nationrole8");
        if (nationRole8 != null) {
            Role role8 = nationRole8.getAsRole();
            nationroles.add(role8);
        }

        OptionMapping nationRole9 = event.getOption("nationrole9");
        if (nationRole9 != null) {
            Role role9 = nationRole9.getAsRole();
            nationroles.add(role9);
        }

        OptionMapping nationRole10 = event.getOption("nationrole10");
        if (nationRole10 != null) {
            Role role10 = nationRole10.getAsRole();
            nationroles.add(role10);
        }

        OptionMapping nationRole11 = event.getOption("nationrole11");
        if (nationRole11 != null) {
            Role role11 = nationRole11.getAsRole();
            nationroles.add(role11);
        }

        OptionMapping nationRole12 = event.getOption("nationrole12");
        if (nationRole12 != null) {
            Role role12 = nationRole12.getAsRole();
            nationroles.add(role12);
        }

        OptionMapping nationRole13 = event.getOption("nationrole13");
        if (nationRole13 != null) {
            Role role13 = nationRole13.getAsRole();
            nationroles.add(role13);
        }

        OptionMapping nationRole14 = event.getOption("nationrole14");
        if (nationRole14 != null) {
            Role role14 = nationRole14.getAsRole();
            nationroles.add(role14);
        }

        OptionMapping nationRole15 = event.getOption("nationrole15");
        if (nationRole15 != null) {
            Role role15 = nationRole15.getAsRole();
            nationroles.add(role15);
        }

        OptionMapping nationRole16 = event.getOption("nationrole16");
        if (nationRole16 != null) {
            Role role16 = nationRole16.getAsRole();
            nationroles.add(role16);
        }

        String channelName = "";
        String cleanName = "";

        nationroles.sort(Comparator.comparing(Role::getName));

        for (int i = 0; i < nationroles.size(); i++) {
            replyMessage += "\n" + nationroles.get(i).getAsMention();

            if (nationroles.get(i).getName().endsWith(" | NPC")) {
                cleanName = nationroles.get(i).getName().replace(" | NPC", "").trim().replace(" ", "").toLowerCase();
            } else if (nationroles.get(i).getName().endsWith(" | Player")) {
                cleanName = nationroles.get(i).getName().replace(" | Player", "").trim().replace(" ", "").toLowerCase();
            } else if (nationroles.get(i).getName().endsWith(" | Alliance")) {
                cleanName = nationroles.get(i).getName().replace(" | Alliance", "").trim().replace(" ", "").toLowerCase();
            }

            if (nationroles.size() < 5) {

                if (i < nationroles.size() - 1) {
                    channelName += cleanName + "-";
                } else {
                    channelName += cleanName;
                }

            } else if (nationroles.size() >= 5) {

                if (i < nationroles.size() - 1) {
                    channelName += cleanName.substring(0, 3) + "-";
                } else {
                    channelName += cleanName.substring(0, 3);
                }

            }

        }

        if (!event.getGuild().getTextChannelsByName(channelName, true).isEmpty()) {
            event.reply("Channel already exists!").setEphemeral(true).queue();
            return;
        } else {

            event.getGuild().createTextChannel(channelName, category)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)) // Denies view permission to @everyone
                    .queue();

            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            TextChannel channel = event.getGuild().getTextChannelsByName(channelName, true)
                    .get(0);

            for (int i = 0; i < nationroles.size(); i++) {

                channel.upsertPermissionOverride(nationroles.get(i)).grant(
                                Permission.VIEW_CHANNEL,
                                Permission.MESSAGE_SEND,
                                Permission.MESSAGE_HISTORY)
                        .queueAfter(1, TimeUnit.SECONDS);

                channel.sendMessage(nationroles.get(i).getAsMention()).queueAfter(800, TimeUnit.MILLISECONDS);
            }

            Role staffRole = event.getGuild().getRolesByName("Staff", true).get(0);
            channel.sendMessage(staffRole.getAsMention()).queue();

            event.reply(replyMessage).setEphemeral(true).queue();

        }
    }
}
