package fr.sonkuun.becameashinobi.util;

import fr.sonkuun.becameashinobi.geom.Rect;

public abstract class DefaultBoxed implements Boxed {
    protected Rect bounds;

    @Override
    public Rect getBounds() {
        return bounds;
    }

    @Override
    public Boxed setBounds(Rect bounds) {
        this.bounds = bounds;
        return this;
    }
}
