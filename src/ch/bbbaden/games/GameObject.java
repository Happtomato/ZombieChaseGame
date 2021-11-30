package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public interface GameObject {
    public void update (WSwingConsoleInterface csi, Player player);
    public String getDrawString();
    public boolean isDead();
    public int getX();
    public int getY();
    public CSIColor getColor();
}
