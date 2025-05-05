package me.MaurycyJaniak.GSSBot.commands;

import me.MaurycyJaniak.GSSBot.commands.handlers.*;
import me.MaurycyJaniak.GSSBot.utils.SendToSheet;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {
    private final RolesCommandHandler rolesCommandHandler;
    private final RollCommandHandler rollCommandHandler;
    private final ApproveCommandHandler approveCommandHandler;
    private final SetupCommandHandler setupCommandHandler;
    private final DiplomacyCommandHandler diplomacyCommandHandler;
    private final SetupAllianceCommandHandler setupAllianceCommandHandler;
    private final SetupCountryCommandHandler setupCountryCommandHandler;
    private final SendToSheet sendToSheet;
    private final ApproveMessageInteractionHandler approveMessageInteractionHandler;

    private CommandRegistrar commandRegistrar = new CommandRegistrar();

    public CommandManager() {
        this.rolesCommandHandler = new RolesCommandHandler();
        this.rollCommandHandler = new RollCommandHandler();
        this.approveCommandHandler = new ApproveCommandHandler();
        this.setupCommandHandler = new SetupCommandHandler();
        this.diplomacyCommandHandler = new DiplomacyCommandHandler();
        this.setupAllianceCommandHandler = new SetupAllianceCommandHandler();
        this.setupCountryCommandHandler = new SetupCountryCommandHandler();
        this.sendToSheet = new SendToSheet();
        this.approveMessageInteractionHandler = new ApproveMessageInteractionHandler(sendToSheet);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        switch (command) {
            case "roles" -> rolesCommandHandler.handle(event);
            case "roll" -> rollCommandHandler.handle(event);
            case "approve" -> approveCommandHandler.handle(event);
            case "setup" -> setupCommandHandler.handle(event);
            case "diplomacy" -> diplomacyCommandHandler.handle(event);
            case "setup-alliance" -> setupAllianceCommandHandler.handle(event);
            case "setup-country" -> setupCountryCommandHandler.handle(event);
        }

    }

    // Guild Command
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(commandRegistrar.getCommands()).queue();

    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {

        if (event.getName().equals("Approve Message")) {
            approveMessageInteractionHandler.handle(event);

        }

    }
}