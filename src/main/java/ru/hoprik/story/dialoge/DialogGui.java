package ru.hoprik.story.dialoge;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.*;
import ru.hoprik.story.hero.StoryFunction;
import ru.hoprik.story.scripts.Bench;

import java.util.ArrayList;
import java.util.List;

public class DialogGui extends Screen {
    String heroSay;
    List<Bench> benches;
    List<Button> buttons = new ArrayList<>();
    int y = 72;
    PlayerEntity playerTrigger;

    protected DialogGui(String heroSay, List<Bench> benches, PlayerEntity playerTrigger) {
        super(new TranslationTextComponent("deathScreen.title"));
        this.heroSay = heroSay;
        this.benches = benches;
        this.playerTrigger = playerTrigger;
    }

    @Override
    protected void init() {
        for (Bench bench: benches) {
             Button button = this.addButton(new Button(this.width / 2 - 75, this.height / 4 + y, 150, 20, new StringTextComponent(bench.playerSay), (p_213021_1_) -> {
                 if(bench.dialog.heroSay.equals("END")) {
                     this.minecraft.displayGuiScreen((Screen) null);
                 }
                 else {
                     for (PlayerEntity player: playerTrigger.world.getPlayers()) {
                         if (player == playerTrigger) {
                             this.minecraft.displayGuiScreen(new DialogGui(bench.dialog.heroSay, bench.dialog.benches, playerTrigger));
                         }
                         else{
                             StoryFunction.SayPlayer(player, playerTrigger.getDisplayName().toString(), bench.playerSay);
                         }
                     }
                 }
             }));
             buttons.add(button);
             y+=30;
        }
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, heroSay, this.width / 2, 85, 16777215);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
