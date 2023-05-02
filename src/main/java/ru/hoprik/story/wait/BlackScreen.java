package ru.hoprik.story.wait;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class BlackScreen extends Screen {
    public BlackScreen() {
        super(new StringTextComponent("black"));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, "hi", this.width / 2, 85, 16777215);
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

    @Override
    public void tick() {
        minecraft.displayGuiScreen(new IngameMenuScreen(false));
        super.tick();
    }
}
