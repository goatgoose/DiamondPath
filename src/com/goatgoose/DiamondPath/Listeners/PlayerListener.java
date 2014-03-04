package com.goatgoose.diamondpath.Listeners;

import com.goatgoose.diamondpath.DiamondPath;
import com.goatgoose.diamondpath.Tasks.ResetBlockTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    private DiamondPath plugin;

    public PlayerListener(DiamondPath instance) {
        plugin = instance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(plugin.getEnabledPlayers().contains(event.getPlayer())) {
            Block block = event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
            Material lastMaterial = block.getType();

            if(!plugin.getChangingBlocks().contains(block)) {
                if(lastMaterial != Material.AIR) {
                    block.setType(plugin.getBlockMaterial());
                    plugin.addChangingBlock(block);
                    ResetBlockTask resetBlockTask = new ResetBlockTask(plugin, block, lastMaterial);
                    resetBlockTask.runTaskLater(plugin, plugin.getReplaceDelay());
                }
            }
        }
    }

}
