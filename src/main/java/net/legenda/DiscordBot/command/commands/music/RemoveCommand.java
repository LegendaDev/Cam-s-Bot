package net.legenda.DiscordBot.command.commands.music;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.legenda.DiscordBot.Main;
import net.legenda.DiscordBot.command.Command;
import net.legenda.DiscordBot.exceptions.InvalidCommandArgumentException;
import net.legenda.DiscordBot.exceptions.InvalidCommandStateException;

@Command.cmdInfo(name = "Remove", description = "Removes track from queue", type = Command.Type.Music, role = "DJ")
public class RemoveCommand extends Command {

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        if(args.length <= 0){
            throw new InvalidCommandArgumentException("Usage: .Remove (Index in Queue)");
        }
        int number = Integer.parseInt(args[0]);
        if(number == 0)
            throw new InvalidCommandStateException("Cannot stop current track, use .skip");
        String message = Main.INSTANCE.musicUtils.remove(event.getGuild(), number);
        event.getTextChannel().sendMessage(message).queue();
    }
}
