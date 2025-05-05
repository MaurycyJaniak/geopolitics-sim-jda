package me.MaurycyJaniak.GSSBot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistrar {

    public List<CommandData> getCommands() {

        List<CommandData> commandData = new ArrayList<>();

        // Add Commands Here
        commandData.add(Commands.slash("roles", "List all roles"));

        OptionData count = new OptionData(OptionType.INTEGER, "count", "How many times to roll", true);
        OptionData dice = new OptionData(OptionType.INTEGER, "dice", "What dice to roll", true);
        commandData.add(Commands.slash("roll", "Rolls a specified dice").addOptions(count, dice));

        OptionData player = new OptionData(OptionType.USER, "player", "Who to create channels for", false);
        OptionData channelname = new OptionData(OptionType.STRING, "channelname", "What to name the channel", true);

        commandData.add(Commands.slash("setup", "Sets up country role and channel with permissions for a player")
                .addOptions(channelname, player)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
        );

        commandData.add(Commands.slash("formtest", "Form test"));

        commandData.add(Commands.message("Approve Message")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
        );

        OptionData nationRole1 = new OptionData(OptionType.ROLE, "nationrole1", "1st Role", true);
        OptionData nationRole2 = new OptionData(OptionType.ROLE, "nationrole2", "2nd Role", false);
        OptionData nationRole3 = new OptionData(OptionType.ROLE, "nationrole3", "3rd Role", false);
        OptionData nationRole4 = new OptionData(OptionType.ROLE, "nationrole4", "4th Role", false);
        OptionData nationRole5 = new OptionData(OptionType.ROLE, "nationrole5", "5th Role", false);
        OptionData nationRole6 = new OptionData(OptionType.ROLE, "nationrole6", "6th Role", false);
        OptionData nationRole7 = new OptionData(OptionType.ROLE, "nationrole7", "7th Role", false);
        OptionData nationRole8 = new OptionData(OptionType.ROLE, "nationrole8", "8th Role", false);
        OptionData nationRole9 = new OptionData(OptionType.ROLE, "nationrole9", "9th Role", false);
        OptionData nationRole10 = new OptionData(OptionType.ROLE, "nationrole10", "10th Role", false);
        OptionData nationRole11 = new OptionData(OptionType.ROLE, "nationrole11", "11th Role", false);
        OptionData nationRole12 = new OptionData(OptionType.ROLE, "nationrole12", "12th Role", false);
        OptionData nationRole13 = new OptionData(OptionType.ROLE, "nationrole13", "13th Role", false);
        OptionData nationRole14 = new OptionData(OptionType.ROLE, "nationrole14", "14th Role", false);
        OptionData nationRole15 = new OptionData(OptionType.ROLE, "nationrole15", "15th Role", false);
        OptionData nationRole16 = new OptionData(OptionType.ROLE, "nationrole16", "16th Role", false);

        // Create the command with multiple role options
        commandData.add(Commands.slash("diplomacy", "Command description")
                .addOptions(nationRole1, nationRole2, nationRole3, nationRole4,
                        nationRole5, nationRole6, nationRole7, nationRole8,
                        nationRole9, nationRole10, nationRole11, nationRole12,
                        nationRole13, nationRole14, nationRole15, nationRole16
                ));

        commandData.add(Commands.slash("setup-alliance", "Sets up an alliance")
                .addOption(OptionType.STRING, "alliancename", "What to name the alliance", true)
                .addOption(OptionType.USER, "alliancefounder", "Who is the founder of the alliance", true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));

        commandData.add(Commands.slash("setup-country", "Sets up a country sheet")
                .addOption(OptionType.ROLE, "countryrole", "The role of a country to add to the sheet", true)
                .addOption(OptionType.STRING, "gdpnumber", "GDP of the country", true)
                .addOption(OptionType.STRING, "populationnumber", "Population to add to the country on the sheet", true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));

        return commandData;
    }

}
