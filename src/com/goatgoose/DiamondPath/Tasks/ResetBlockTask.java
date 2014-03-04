package com.goatgoose.diamondpath.Tasks;

import com.goatgoose.diamondpath.DiamondPath;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class ResetBlockTask extends BukkitRunnable {

    private DiamondPath plugin;

    private Block block;

    private Material replaceWith;

    public ResetBlockTask(DiamondPath instance, Block block, Material replaceWith) {
        plugin = instance;
        this.block = block;
        this.replaceWith = replaceWith;
    }

    @Override
    public void run() {
        block.setType(replaceWith);
        plugin.removeChangingBlock(block);
    }

}
