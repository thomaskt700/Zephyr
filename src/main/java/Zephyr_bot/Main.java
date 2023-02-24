package Zephyr_bot;
import Zephyr_bot.Commands.botCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;


import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        JDA bot = JDABuilder.createLight("MTA3MzM2OTY2Nzc0MzI2NDc3OA.GFnIo-.LyKMYACjoMM1AXtA-goyl0O9Mo7yuSQ44RH41Q", EnumSet.noneOf(GatewayIntent.class))
                .setActivity(Activity.listening("Doja by Central Cee"))
                .addEventListeners(new botCommands())
                .build();

        CommandListUpdateAction commands = bot.updateCommands();

        commands.addCommands(
                Commands.slash("ban", "Ban a user from this server. Requires permission to ban users.")
                        .addOptions(new OptionData(USER, "user", "The user to ban") // USER type allows to include members of the server or other users by id
                                .setRequired(true)) // This command requires a parameter
                        .addOptions(new OptionData(INTEGER, "del_days", "Delete messages from the past days.") // This is optional
                                .setRequiredRange(0, 7)) // Only allow values between 0 and 7 (inclusive)
                        .addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)")) // optional reason
                        .setGuildOnly(true) // This way the command can only be executed from a guild, and not the DMs
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)) // Only members with the BAN_MEMBERS permission are going to see this command
        );

        // Simple reply commands
        commands.addCommands(
                Commands.slash("say", "Makes the bot say what you tell it to")
                        .addOption(STRING, "content", "What the bot should say", true) // you can add required options like this too
        );

        // Commands without any inputs
        commands.addCommands(
                Commands.slash("leave", "Make the bot leave the server")
                        .setGuildOnly(true) // this doesn't make sense in DMs
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED) // only admins should be able to use this command.
        );

        commands.addCommands(
                Commands.slash("prune", "Prune messages from this channel")
                        .addOption(INTEGER, "amount", "How many messages to prune (Default 100)") // simple optional argument
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_MANAGE))
        );

        commands.addCommands(
                Commands.slash("whatami", "uplifiting message")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED)

        );

        commands.addCommands(
                Commands.slash("search", "Search and return google results")
                        .addOption(OptionType.STRING, "searchTerm", "What would you like to search for?")
                        .addOption(OptionType.INTEGER, "num", "How many results would you like to see")// simple optional argument
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();



        // Send the new set of commands to discord, this will override any existing global commands with the new set provided here
        commands.queue();



    }
}
