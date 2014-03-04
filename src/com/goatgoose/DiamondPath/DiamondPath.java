package com.goatgoose.diamondpath;

import com.goatgoose.diamondpath.Listeners.BlockListener;
import com.goatgoose.diamondpath.Listeners.PlayerListener;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DiamondPath extends JavaPlugin {

    private static PlayerListener playerListener;

    private static BlockListener blockListener;

    private Material blockMaterial = Material.DIAMOND_BLOCK;

    private int replaceDelay = 3;  // in seconds

    private List<Player> enabledPlayers = new ArrayList<Player>();

    private List<Block> changingBlocks = new ArrayList<Block>();

    @Override
    public void onEnable() {
        playerListener = new PlayerListener(this);
        blockListener = new BlockListener(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(command.getName().equalsIgnoreCase("dp")) {

            if(args.length == 0) {
                sender.sendMessage("DiamondPath version 0.1");
            } else {
                if(args[0].equalsIgnoreCase("enable")) {
                    if(sender.hasPermission("diamondpath.enable")) {
                        if(args.length == 1) {
                            enabledPlayers.add((Player) sender);
                            sender.sendMessage("Enabled DiamondTrail!");
                        } else if(args.length == 2) {
                            enabledPlayers.add(getServer().getPlayer(args[1]));
                            sender.sendMessage("Enabled DiamondTrail for user " + args[1] + "!");
                        }
                    }
                } else if(args[0].equalsIgnoreCase("disable")) {
                    if(sender.hasPermission("diamondpath.disable")) {
                        if(args.length == 1) {
                            enabledPlayers.remove((Player) sender);
                            sender.sendMessage("Disabled DiamondTrail!");
                        } else if(args.length == 2) {
                            if(enabledPlayers.contains(getServer().getPlayer(args[1]))) {
                                enabledPlayers.remove(getServer().getPlayer(args[1]));
                                sender.sendMessage("Disabled DiamondTrail for user " + args[1]);
                            } else {
                                sender.sendMessage("User '" + args[1] + "' not found.");
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public int getReplaceDelay() {
        int replaceDelayTicks = replaceDelay * 20;
        return replaceDelayTicks;
    }

    public List<Block> getChangingBlocks() {
        return changingBlocks;
    }

    public void addChangingBlock(Block block) {
        changingBlocks.add(block);
    }

    public void removeChangingBlock(Block block) {
        changingBlocks.remove(block);
    }

    public List<Player> getEnabledPlayers() {
        return enabledPlayers;
    }

}
