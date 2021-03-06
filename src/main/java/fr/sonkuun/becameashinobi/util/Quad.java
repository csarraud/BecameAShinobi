package fr.sonkuun.becameashinobi.util;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlStateManager;

import org.lwjgl.opengl.GL11;

import fr.sonkuun.becameashinobi.geom.Direction;
import fr.sonkuun.becameashinobi.geom.Point;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.geom.Size;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class Quad extends DefaultBoxed {
    private static final float TEX_SCALE = 1f / 256f;

    private double zLevel;
    private Rect texture;
    private final Map<Direction, Color> colors;
    private boolean hasColor = false;

    public Quad() {
        colors = new EnumMap<>(Direction.class);
    }

    public Quad(Quad quad) {
        colors = new EnumMap<>(quad.colors);
        hasColor = quad.hasColor;
        setZLevel(quad.zLevel);
        setTexture(quad.texture);
    }

    public Quad setZLevel(double zLevel) {
        this.zLevel = zLevel;
        return this;
    }

    public Quad setTexture(Rect texture) {
        this.texture = texture;
        return this;
    }

    public Quad setColor(Color color) {
        setColors(color, color, color, color);
        return this;
    }

    public Quad setColor(Direction direction, Color color) {
        colors.put(direction, color);
        hasColor = true;
        return this;
    }

    public Quad setColors(Color northWest, Color northEast, Color southWest, Color southEast) {
        colors.put(Direction.NORTH_WEST, northWest);
        colors.put(Direction.NORTH_EAST, northEast);
        colors.put(Direction.SOUTH_WEST, southWest);
        colors.put(Direction.SOUTH_EAST, southEast);
        hasColor = true;
        return this;
    }

    public Quad noColor() {
        hasColor = false;
        return this;
    }

    /**
     * OpenGL side-effect: texture 2D is enabled.
     * <p>{@inheritDoc}
     */
    @Override
    public void render() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();

        List<VertexFormatElement> elements = new ArrayList<>(3);
        elements.add(DefaultVertexFormats.POSITION_3F);
        if(texture != null) elements.add(DefaultVertexFormats.TEX_2F);
        if(hasColor) elements.add(DefaultVertexFormats.COLOR_4UB);

        VertexFormat format = new VertexFormat(ImmutableList.copyOf(elements));

        builder.begin(GL11.GL_QUADS, format);
        addVertex(bounds, builder, Direction.SOUTH_WEST);
        addVertex(bounds, builder, Direction.SOUTH_EAST);
        addVertex(bounds, builder, Direction.NORTH_EAST);
        addVertex(bounds, builder, Direction.NORTH_WEST);

        if(texture == null) {
            GlStateManager.disableTexture();
            tessellator.draw();
            GlStateManager.enableTexture();
        } else {
            tessellator.draw();
        }
    }

    private void addVertex(Rect bounds, BufferBuilder builder, Direction anchor) {
        for(VertexFormatElement element : builder.getVertexFormat().getElements()) {
            switch(element.getUsage()) {
                case POSITION: {
                    Point xy = bounds.getAnchor(anchor);
                    builder.pos(xy.getX(), xy.getY(), zLevel);
                    break;
                }
                case UV: {
                    Point uv = texture.getAnchor(anchor);
                    builder.tex(uv.getX() * TEX_SCALE, uv.getY() * TEX_SCALE);
                    break;
                }
                case COLOR: {
                    Color color = colors.get(anchor);
                    builder.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                    break;
                }
                default: throw new IllegalStateException("Unsupported builder element");
            }
        }
        builder.endVertex();
    }

    @Override
    public Size getPreferredSize() {
        return texture != null ? texture.getSize() : Size.zero();
    }
}
