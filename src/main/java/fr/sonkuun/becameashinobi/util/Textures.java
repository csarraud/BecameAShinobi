package fr.sonkuun.becameashinobi.util;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import net.minecraft.util.ResourceLocation;

/**
 * Static definitions for common texture resources.
 *
 * @see net.minecraft.client.gui.Gui#ICONS
 */
public final class Textures {
    public static final ResourceLocation WIDGETS   = new ResourceLocation("textures/gui/widgets.png");
    public static final ResourceLocation PARTICLES = new ResourceLocation("textures/particle/particles.png");
    public static final ResourceLocation HUD_ICONS = new ResourceLocation(BecameAShinobi.MODID, "textures/gui/icons_hud.png");
    public static final ResourceLocation SETTINGS  = new ResourceLocation(BecameAShinobi.MODID, "textures/gui/settings.png");

	public static final ResourceLocation CHAKRA_CIRCULATION_TEXTURE = new ResourceLocation(BecameAShinobi.MODID, "textures/hud/chakra/chakra_circulation.png");

    private Textures() {}
}
