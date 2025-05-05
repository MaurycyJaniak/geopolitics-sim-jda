package me.MaurycyJaniak.GSSBot.listeners;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

//Unused
public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("testform")){
            String fielda1 = event.getValue("field1.1").getAsString();
            //String fielda2 = event.getValue("field1.2").getAsString();
            //String fielda3 = event.getValue("field1.3").getAsString();
            //String fielda4 = event.getValue("field1.4").getAsString();
            //String fielda5 = event.getValue("field1.5").getAsString();

            String usermention = event.getUser().getAsMention();

            String reply = usermention + " submitted this nation submission form: \n" +
                    fielda1
                    //"Field 2: " + fielda2 + "\n" +
                    //"Field 3: " + fielda3 + "\n" +
                    //"Field 4: " + fielda4 + "\n" +
                    //"Field 5: " + fielda5 + " "
                    ;

            String[] lines = fielda1.split("\n");

            event.reply(reply).setEphemeral(true).queue();

        } else if (event.getModalId().equals("submitnationform")){
            String fielda1 = event.getValue("field1.1").getAsString();
            //String fielda2 = event.getValue("field1.2").getAsString();
            //String fielda3 = event.getValue("field1.3").getAsString();
            //String fielda4 = event.getValue("field1.4").getAsString();
            //String fielda5 = event.getValue("field1.5").getAsString();

            String usermention = event.getUser().getAsMention();
            Long id = event.getUser().getIdLong();

            String reply = usermention + "with ID: " + id + "submitted this nation submission form: \n" +
                    fielda1
                    //"Field 2: " + fielda2 + "\n" +
                    //"Field 3: " + fielda3 + "\n" +
                    //"Field 4: " + fielda4 + "\n" +
                    //"Field 5: " + fielda5 + " "
                    ;

            String[] lines = fielda1.split("\n");

            event.reply(reply).setEphemeral(true).queue();

        }



    }
}
