package ru.hoprik.story.scripts;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;
import ru.hoprik.story.dialoge.Dialog;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.StoryFunction;

import javax.swing.text.StyledEditorKit;

@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class Script_three {
    static PlayerEntity playerTrigger = null;
    static Boolean isDialoge = false;
    static Boolean isCrafting = false;

    @SubscribeEvent
    public static void craftEvent(PlayerEvent.ItemCraftedEvent event){
        if (event.getCrafting().getItem() == ItemStory.half_god_igot.get() && !isCrafting){
            playerTrigger = event.getPlayer();
            isCrafting = true;
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 3);
            executer.addS(() -> {StoryFunction.ItemSay(playerTrigger);StoryFunction.SayPlayerAll(playerTrigger, "Земля","О ты скрафтил");}, 3);
            executer.addS(() -> {Dialog();}, 3);
            executer.Exec();
        }
    }

    @SubscribeEvent
    public static void Gui(TickEvent.PlayerTickEvent event){
        if (playerTrigger != null && event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.currentScreen == null && isDialoge) {
                Executer executer = new Executer();
                executer.addS(() -> {HoprikStory.LOGGER.info("");}, 10);
                executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Напоминаю стих. Где-то в мире существует центр, и иди от него пройти на сервер пока не дойдешь блоков до 150, и на восток сверни на блоков 40 прокопайся "); }, 3);
                executer.addS(() -> {StoryFunction.ItemDisbleSay(playerTrigger); }, 10);
                executer.Exec();
                isDialoge = false;
            }
        }
    }
    public static void Dialog(){
        Dialog dialog = new Dialog("Но, я забыл упомянуть один нюанс это не полный слиток бога");
        Dialog dialog1 = new Dialog("Чтобы скрафтить полный слиток бога нужно 9 слитков неполных");
        Dialog dialog2 = new Dialog("Не расстраивайся, дедушка мне оставил 13 блоков алмазов, и я тебе отдам");
        Dialog dialog3 = new Dialog("Я не знаю где находится, но знаю стих как их найти");
        Dialog dialog4 = new Dialog("Где-то в мире существует центр");
        Dialog dialog5 = new Dialog("и иди от него пройти на сервер пока не дойдешь блоков до 150");
        Dialog dialog6 = new Dialog("и на восток сверни на блоков 40 прокопайся");
        Dialog dialog7 = new Dialog("Если забудишь, то я тебе еще раз повторю");
        Dialog dialog8 = new Dialog("END");
        dialog.addBench(new Bench("что?", dialog1));
        dialog1.addBench(new Bench("блин и что делать?", dialog2));
        dialog2.addBench(new Bench("Спасибо", dialog3));
        dialog3.addBench(new Bench("Так", dialog4));
        dialog4.addBench(new Bench("Понял", dialog5));
        dialog5.addBench(new Bench("Ага", dialog6));
        dialog6.addBench(new Bench("Пошли", dialog7));
        dialog7.addBench(new Bench("Хорошо", dialog8));
        dialog.show(playerTrigger);
        BlockState diamond = Blocks.DIAMOND_BLOCK.getDefaultState();
        playerTrigger.world.setBlockState(new BlockPos(40, 37, -150), diamond);
        playerTrigger.world.setBlockState(new BlockPos(40, 38, -150), diamond);
        playerTrigger.world.setBlockState(new BlockPos(40, 37, -151), diamond);
        playerTrigger.world.setBlockState(new BlockPos(39, 38, -150), diamond);
        isDialoge = true;
    }
}
