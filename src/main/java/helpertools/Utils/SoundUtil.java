package helpertools.Utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public final class SoundUtil {
    private SoundUtil() {

    }

    public static void playSoundAtEntity(Entity entity, String sound, float volume, float pitch) {
        entity.worldObj.playSound(null, entity.posX, entity.posY, entity.posZ, new SoundEvent(new ResourceLocation(sound)),
                (entity instanceof EntityPlayer) ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL, volume, pitch);
    }
}
