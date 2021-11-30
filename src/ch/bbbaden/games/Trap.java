package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class Trap implements GameObject{

    int x;
    int y;
    boolean die;

    Trap(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public void update(WSwingConsoleInterface csi, Player player) {

        csi.print(x,y, getDrawString(), getColor());
        if(player.getX() == x && player.getY() == y){
            die = true;
        }
    }

    @Override
    public String getDrawString() {
        return "*";
    }

    @Override
    public boolean isDead() {
        return die;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public CSIColor getColor() {
        return CSIColor.GREEN;
    }
}
