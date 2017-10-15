package net.legenda.DiscordBot.command.commands.Admin;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.legenda.DiscordBot.command.Command;
import net.legenda.DiscordBot.exceptions.InvalidCommandArgumentException;

import java.util.Arrays;

@Command.cmdInfo(name = "Ban", description = "Bans a user from the server", type = Command.Type.Admin, permission = Permission.BAN_MEMBERS)
public class BanCommand extends Command {

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        String reason = "";
        String[] arguments = event.getMessage().getRawContent().replaceAll("<@.*?>", "").split(" ");

        if (arguments.length <= 1) {
            throw new InvalidCommandArgumentException("Usage: .Ban <@User> <Reason>*");
        }
        System.out.println(arguments[1]);
        if (args.length > 1)
            reason = String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length));

        GuildController guildController = new GuildController(event.getGuild());
        User toBan = event.getMessage().getMentionedUsers().size() > 0 ? event.getMessage().getMentionedUsers().get(0) : null;
        if (toBan != null) {
            if (!reason.isEmpty())
                guildController.ban(toBan, 0, reason).queue();
            else
                guildController.ban(toBan, 0).queue();
            sendEmbedMessage("Banned user: " + toBan.getAsMention(), event.getChannel(), false);

        } else
            throw new InvalidCommandArgumentException("Could not find User");

    }

}