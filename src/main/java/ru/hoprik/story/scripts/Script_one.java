package ru.hoprik.story.scripts;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;
import ru.hoprik.register.block.AnimationBlock;
import ru.hoprik.register.block.BlockStory;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.StoryFunction;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class Script_one {
    static int count_brick = 0;
    static BlockPos pos;
    static PlayerEntity playerTrigger;
    static boolean isPickup= false;
    static boolean isSay = false;
    static boolean isDrop = false;
    @SubscribeEvent
    public static void onCrashBlock(BlockEvent.BreakEvent event){
        if (event.getState() == BlockStory.stone.get().getDefaultState() && event.getPlayer().inventory.getCurrentItem().getItem() != ItemStory.hammer.get()){
            event.setCanceled(true);
        }
        if (event.getState() != BlockStory.stone.get().getDefaultState() && event.getPlayer().inventory.getCurrentItem().getItem() == ItemStory.hammer.get() && isDrop){
            event.getPlayer().inventory.getCurrentItem().getItem().setDamage(event.getPlayer().inventory.getCurrentItem().getStack(), 12);
        }
        if (event.getState() == Blocks.DIRT.getDefaultState() && count_brick <= 1){
            count_brick++;
        }
        else if (count_brick == 2){
            count_brick++;
            playerTrigger = event.getPlayer();
            pos = event.getPos();
            Executer executer = new Executer();
            AnimationBlock block = (AnimationBlock)BlockStory.stone.get();
            executer.addS(() -> {block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 2); }, 1);
            executer.addS(() -> {block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 1); StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Эй стой!"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Не копай меня я тебе одолжение сделаю!"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Я знаю крафт брони бога, она лучше чем незеритовая, ты же хочешь получить?");block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 0); }, 1);
            executer.addS(() -> {isSay=true; }, 1);
            executer.Exec();
        }
    }
    @SubscribeEvent
    public static void GetItem(EntityItemPickupEvent event){
        HoprikStory.LOGGER.info(event.getItem().getItem().getItem());
        if (event.getItem().getItem().getItem() == ItemStory.stoneHero.get() && isPickup){
            HoprikStory.LOGGER.info(event.getItem().getItem().getItem());
            Executer executer = new Executer();
            executer.addS(() -> {StoryFunction.ItemSay(playerTrigger); StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Спасибо что подобрал."); }, 1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Если хочешь ее скрафтить то сначала надо сделать одну вещь на рецепт"); }, 1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "И это слиток"); }, 1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Но вспомнить его не могу. Ты пока повыживай, а потом я скажу когда вспомню");StoryFunction.ItemDisbleSay(playerTrigger); Script_two.playerTriger = playerTrigger; }, 1);
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 10*60);
            executer.addS(() -> {Script_two.HeroSay(); }, 0);
            executer.Exec();
            isPickup = false;
            isDrop=true;
        }
    }
    @SubscribeEvent
    public static void sayMessage(ServerChatEvent event){
        if ((event.getMessage().equals("Да") || event.getMessage().equals("да") || event.getMessage().equals("ДА")) && isSay){
            event.setCanceled(true);
            isSay = false;
            StoryFunction.SayPlayerAll(event.getPlayer(), event.getPlayer().getName().getString(), event.getMessage());
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 1);
            AnimationBlock block = (AnimationBlock)BlockStory.stone.get();
            executer.addS(() -> {block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 1); StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Хорошо"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Я тебе прикажусть можешь добыть меня лопаткой?"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Не знаешь рецепт? На крафт"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Вот добавил в твою зеленую книгу");StoryFunction.unlockRecipe(playerTrigger, "hammer");block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 0);isPickup = true; },1);
            executer.addS(() -> {isPickup=true;},1);
            executer.Exec();
        }
        else if ((event.getMessage().equals("Нет") || event.getMessage().equals("нет") || event.getMessage().equals("НЕТ")) && isSay){
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 1);
            AnimationBlock block = (AnimationBlock)BlockStory.stone.get();
            executer.addS(() -> {block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 1); StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Хорошо"); },1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Ну как хочешь"); },1);
            executer.addS(() -> {StoryFunction.ShowWaitScreenAll(playerTrigger, "Спасибо за что поиграли в сюжет!");},1);
            executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Авторы: Валерий Морозов (hoprik), Александр Киселов (шрамом)");StoryFunction.unlockRecipe(playerTrigger, "hammer");block.setAnimate(BlockStory.stone.get().getDefaultState(), playerTrigger.world, pos, 0);isPickup = true; },1);
            executer.Exec();
        }
    }
}