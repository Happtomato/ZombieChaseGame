package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import java.util.Random;

public class Zombie implements GameObject{

    int x;
    int y;
    boolean die;

    Zombie(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(WSwingConsoleInterface csi, Player player) {

        int move;

        int zombieOldX = x;
        int zombieOldY = y;

        Random rand = new Random();

        int anzahlZüge = rand.nextInt(5);


        for(int i = 0; i < anzahlZüge; i++){
            if(x < player.getX()){
                x++;
            }
            else if(y > player.getY()){
                y--;
            }
            else if(x > player.getX()){
                x--;
            }

            else{
                y++;
            }

        }




        try {

            csi.print(x, y, getDrawString(), getColor());
        }catch(Exception e){
            csi.print(zombieOldX, zombieOldY, getDrawString(), getColor());
        }
        if(player.getX() == x && player.getY() == y){
            die = true;
            player.setHealth(player.getHealth()-20);
        }

    }

    @Override
    public String getDrawString() {
        return "Z";
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
        return CSIColor.RED;
    }
}
