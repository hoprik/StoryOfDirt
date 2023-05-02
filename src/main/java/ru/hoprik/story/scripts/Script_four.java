package ru.hoprik.story.scripts;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.story.dialoge.Dialog;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.StoryFunction;

@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class Script_four {
    static int blockCount = 0;
    static PlayerEntity playerTrigger;
    static Boolean isDialoge = false;
    static boolean say = false;
    @SubscribeEvent
    public static void BlockBreak(BlockEvent.BreakEvent event){
        if (blockCount == 3){
            playerTrigger = event.getPlayer();
            blockCount++;
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 1);
            executer.addS(() -> {StoryFunction.ItemSay(playerTrigger); Dialog(); }, 5);
            executer.Exec();
        }
        else {
            BlockState diamond = Blocks.DIAMOND_BLOCK.getDefaultState();
            if (event.getState().equals(diamond) && event.getPos().equals(new BlockPos(40, 37, -150))) {
                blockCount++;
            }
            else if (event.getState().equals(diamond) && event.getPos().equals(new BlockPos(40, 38, -150))) {
                blockCount++;
            }
            else if (event.getState().equals(diamond) && event.getPos().equals(new BlockPos(40, 37, -151))) {
                blockCount++;
            }
            else if (event.getState().equals(diamond) && event.getPos().equals(new BlockPos(39, 38, -150))) {
                blockCount++;
            }
        }
    }
    @SubscribeEvent
    public static void sayMessage(ServerChatEvent event){
        if ((event.getMessage().equals("Да") || event.getMessage().equals("да") || event.getMessage().equals("Я готов") || event.getMessage().equals("Готов")) && say){
            event.setCanceled(true);
            say=false;
            StoryFunction.SayPlayerAll(event.getPlayer(), event.getPlayer().getName().getString(), event.getMessage());
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 2);
            executer.addS(() -> {StoryFunction.ItemSay(playerTrigger);StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Отлично отправляйся от центра мира 2457 блоков на юг и 1294 на восток.");StoryFunction.GenerateTempel(playerTrigger.world, 1294, 37,2457); }, 10);
            executer.addS(() -> {StoryFunction.ShowWaitScreenAll(playerTrigger, "Спустя 1 час"); }, 1);
            executer.addS(() -> {StoryFunction.TeleportEntityAll(playerTrigger, new Vector3d(1295, 40, 2460)); }, 3);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Ну вроде пришли, копай вниз");StoryFunction.ItemDisbleSay(playerTrigger);}, 1);
            executer.Exec();
        }
        if ((event.getMessage().equals("Нет") || event.getMessage().equals("нет")) & say) {
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 2);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Хорошо когда будешь готов скажи мне"); }, 1);
            executer.Exec();
        }
    }
    @SubscribeEvent
    public static void Gui(TickEvent.PlayerTickEvent event){
        if (playerTrigger != null && event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.currentScreen == null && isDialoge) {
                isDialoge = false;
                Executer executer = new Executer();
                executer.addS(() -> {HoprikStory.LOGGER.info("");}, 2);
                executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Нет. Ты че как мог так подумать?"); }, 1);
                executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Ладно опустим момент. Нам придеться идти к хранителю вечности"); }, 1);
                executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Ты готов?"); }, 1);
                executer.addS(() -> {say=true; }, 1);
                executer.Exec();
            }
        }
    }
    public static void Dialog(){
        Dialog dialog = new Dialog("");
        Dialog dialog1 = new Dialog("Я не знаю");
        Dialog dialog2 = new Dialog("END");
        dialog.addBench(new Bench("Стоп! А где остальные?", dialog1));
        dialog1.addBench(new Bench("Ты меня хотел обмнануть?",dialog2));
        dialog.show(playerTrigger);
        isDialoge = true;
    }
}