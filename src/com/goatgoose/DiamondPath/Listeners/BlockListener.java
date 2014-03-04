package com.goatgoose.diamondpath.Listeners;

import com.goatgoose.diamondpath.DiamondPath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    private DiamondPath plugin;

    public BlockListener(DiamondPath instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(plugin.getChangingBlocks().contains(event.getBlock())) {
            event.setCancelled(true);
        }
    }

}
