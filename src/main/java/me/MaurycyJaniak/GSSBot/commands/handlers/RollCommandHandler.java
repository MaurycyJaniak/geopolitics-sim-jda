package me.MaurycyJaniak.GSSBot.commands.handlers;

import me.MaurycyJaniak.GSSBot.utils.DiceRoller;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class RollCommandHandler implements CommandHandler{
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String reply = "";

        //Get data from command options
        OptionMapping diceOption = event.getOption("dice");
        int dice = diceOption.getAsInt();
        OptionMapping countOption = event.getOption("count");
        int count = countOption.getAsInt();

        reply += "Rolling " + "d" + dice + " dice";

        int resultPlayers = 0;

        for (int t = 1; t <= count; t++) {
            //Rolling for the players
            resultPlayers = DiceRoller.rollForPlayers(dice);
            reply += "\n" + "Rolled " + resultPlayers;

            //Rolling for the system
            //int resultSystem = rolling.rollForSystem(dice);
            //reply+="\nSystem " + "rolled " + resultSystem + " against" + " Player "+t;
        }

        event.reply(reply).queue();
    }
}
