package net.legenda.DiscordBot.command.commands.music;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.legenda.DiscordBot.Main;
import net.legenda.DiscordBot.command.Command;
import net.legenda.DiscordBot.command.CommandInfo;
import net.legenda.DiscordBot.command.CommandType;
import net.legenda.DiscordBot.exceptions.InvalidCommandArgumentException;

import java.util.Arrays;


@CommandInfo(name = "Playtop", description = "Adds a song to the queue", type = CommandType.Music)
public class PlayTopCommand extends Command {

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        if(args.length < 1){
            throw new InvalidCommandArgumentException("Usage: `.Playtop (Link/Search Query)`");
        }

        String input = String.join(" ", Arrays.copyOfRange(args, 0, args.length));

        if(!(input.startsWith("https://") || input.startsWith("http://"))){
            input = "ytsearch:" + input;
        }

        Main.INSTANCE.musicUtils.newTrack(input, event.getMember(), event.getMessage(), true);

    }

}
