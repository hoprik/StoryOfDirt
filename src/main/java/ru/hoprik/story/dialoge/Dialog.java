package ru.hoprik.story.dialoge;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import ru.hoprik.story.scripts.Bench;

import java.util.ArrayList;
import java.util.List;

public class Dialog {
    String heroSay;
    List<Bench> benches = new ArrayList<>();
    Minecraft minecraft = Minecraft.getInstance();

    public Dialog(String heroSay){
        this.heroSay = heroSay;
    }
    public void addBench(Bench bench){
        benches.add(bench);
    }

    public void removerBench(int id){
        benches.remove(id);
    }

    public void show(PlayerEntity entity){
        for (PlayerEntity player: entity.world.getPlayers()) {
            if (player == entity) {
                minecraft.displayGuiScreen(new DialogGui(heroSay, benches, entity));
            }
        }
    }
}
