package fr.sonkuun.becameashinobi.util.bar;

import java.util.Random;

import fr.sonkuun.becameashinobi.geom.Direction;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.util.RandomWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;

public class StatBarFood extends StatBarBasic<PlayerEntity> {
    private final Random random = new Random();
    private final Minecraft MC = Minecraft.getInstance();
    
    @Override
    protected int getCurrent() {
        return host.getFoodStats().getFoodLevel();
    }

    @Override
    protected Rect getIcon(IconType icon, int pointsIndex) {
        boolean hasHunger = host.isPotionActive(Effects.HUNGER);
        int xOffset = hasHunger ? 88 : 52;

        switch(icon) {
            case BACKGROUND: return new Rect(hasHunger ? 133 : 16, 27, 9, 9);
            case HALF:       return new Rect(xOffset + 9, 27, 9, 9);
            case FULL:       return new Rect(xOffset, 27, 9, 9);
            default:         return null;
        }
    }

    @Override
    public Direction getNativeAlignment() {
        return Direction.EAST;
    }

    @Override
    protected int getIconBounce(int pointsIndex) {
        if(host.getFoodStats().getSaturationLevel() <= 0 && MC.ingameGUI.getTicks() % (getCurrent() * 3 + 1) == 0) {
            return new RandomWrapper(random).nextInt(-1, 2);
        } else {
            return 0;
        }
    }

    @Override
    public void render() {
        random.setSeed(MC.ingameGUI.getTicks());
        super.render();
    }
}
