package me.MaurycyJaniak.GSSBot.commands.handlers;

import me.MaurycyJaniak.GSSBot.utils.SendToSheet;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;

public class SetupCountryCommandHandler implements CommandHandler {

    private final SendToSheet sendToSheet = new SendToSheet();

    private String scriptUrl = "https://script.google.com/macros/s/AKfycbyNJfLC95mI6jL5TdAdv69KGc5jgz3POBO2BBcc1r06umivx2PXr8bPKwqO929Y2mpm/exec";

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        OptionMapping countryrole = event.getOption("countryrole");
        OptionMapping gdpnumber = event.getOption("gdpnumber");
        OptionMapping populationnumber = event.getOption("populationnumber");

        float gdp = 0;

        event.deferReply(true).queue();
        DecimalFormat formatter = new DecimalFormat("#.##");

        // Get GDP
        String gdpString = gdpnumber.getAsString().replace(",", ".");
        if (gdpString.matches("^\\d+(\\.\\d+)?[KMBT]?$")) {

            if (gdpString.charAt(gdpString.length() - 1) == 'K') {
                gdp = Float.parseFloat(gdpString.replace("K", "")) * 1000f;
            } else if (gdpString.charAt(gdpString.length() - 1) == 'M') {
                gdp = Float.parseFloat(gdpString.replace("M", "")) * 1000000f;
            } else if (gdpString.charAt(gdpString.length() - 1) == 'B') {
                gdp = Float.parseFloat(gdpString.replace("B", "")) * 1000000000f;
            } else if (gdpString.charAt(gdpString.length() - 1) == 'T') {
                gdp = Float.parseFloat(gdpString.replace("T", "")) * 1000000000000f;
            } else {
                gdp = Float.parseFloat(gdpString);
            }
        } else event.reply("GDP Input has to be a number or end in K, M, B or T").setEphemeral(true).queue();

        float population = 0;

        // Get population
        String populationString = populationnumber.getAsString().replace(",", ".");
        if (populationString.matches("^\\d+(\\.\\d+)?[KMB]?$")) {

            if (populationString.charAt(populationString.length() - 1) == 'K') {
                population = Float.parseFloat(populationString.replace("K", "")) * 1000f;
            } else if (populationString.charAt(populationString.length() - 1) == 'M') {
                population = Float.parseFloat(populationString.replace("M", "")) * 1000000f;
            } else if (populationString.charAt(populationString.length() - 1) == 'B') {
                population = Float.parseFloat(populationString.replace("B", "")) * 1000000000f;
            } else {
                population = Float.parseFloat(populationString);
            }
        } else event.reply("Population Input has to be a number or end in K, M or B").setEphemeral(true).queue();

        String country = "";

        // Process roles
        Role countryrole1 = countryrole.getAsRole();

        if (countryrole1.getName().endsWith(" | NPC")) {
            country = countryrole1.getName().replace(" | NPC", "");
        } else if (countryrole1.getName().endsWith(" | Player")) {
            country = countryrole1.getName().replace(" | Player", "");
        }

        // Create JSON Payload
        JSONObject payload = new JSONObject();
        payload.put("type", "registercountry");
        payload.put("countryname", country);
        payload.put("gdp", gdp);
        payload.put("population", population);

        // Send to Google Sheets via Apps Script
        try {
            int responseCode = sendToSheet.send(payload);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                event.getHook().editOriginal("Submission approved and logged to the spreadsheet")
                        .queue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.getHook().editOriginal("Error submitting to sheet: " + e.getMessage()).queue();
        }

        event.reply("GDP: " + formatter.format(gdp) + " Population: " + formatter.format(population) + " Country: " + country).setEphemeral(true).queue();
    }
}
