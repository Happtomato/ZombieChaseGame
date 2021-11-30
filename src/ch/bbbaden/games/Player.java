package ch.bbbaden.games;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import javax.swing.*;
import java.util.Random;

import static net.slashie.libjcsi.CharKey.UARROW;

public class Player implements GameObject{

    private int health = 150;
    private int points = 0;
    private int x;
    private int y;
    private int playerNextX = 0;
    private int playerNextY = 0;

    int tpCount = 5;
    boolean tpReady = true;
    boolean tpUsed = false;

    Player(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void action(char keyChar, WSwingConsoleInterface csi){
        boolean keyInputGood = true;

        tpCount++;

        if(tpCount >= 5){
            tpReady = true;
        }




        switch (keyChar) {
            case UARROW:
                playerNextY = y - 1;
                break;
            case CharKey.DARROW:
                playerNextY = y + 1;
                break;
            case CharKey.LARROW:
                playerNextX = x - 1;
                break;
            case CharKey.RARROW:
                playerNextX = x + 1;
                break;
            case CharKey.SPACE:
                if (tpReady) {
                    Random rand = new Random();

                    int tpX = rand.nextInt(80);
                    int tpY = rand.nextInt(25);

                    playerNextX = tpX;
                    playerNextY = tpY;
                    tpUsed = true;
                    tpReady = false;
                    tpCount = 0;

                    break;

                } else if (!tpReady) {
                    JOptionPane.showMessageDialog(null,"Der TP ist nicht bereit");
                    break;
                }
            case CharKey.Q:
            case CharKey.q:
                System.exit(0);
        }


        if(!tpUsed) {
            //check for Collision with border
            if (playerNextX == -1 || playerNextX == 80 || playerNextY == -1 || playerNextY == 24) {
                playerNextX = x;
                playerNextY = y;
            }
            //check for collision with obstacle
            else if ((csi.peekChar(playerNextX, playerNextY)) == '#') {
                playerNextX = x;
                playerNextY = y;
            }

            //move player
            else {
                x = playerNextX;
                y = playerNextY;
                points++;
            }
        }
        if(csi.peekChar(playerNextX, playerNextY) == '*'){
            health -= 10;
        }
        else if(csi.peekChar(playerNextX, playerNextY) == '+'){
            health += 10;
        }
        else if(csi.peekChar(playerNextX, playerNextY) == 'Z'){
            health -= 20;
        }

        tpUsed = false;

    }

    public int getHealth() {
        return health;
    }
    public void addHealth(int bonusHealth){
        health = health + bonusHealth;
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(int bonusPoints){
        points += bonusPoints;
    }

    @Override
    public void update(WSwingConsoleInterface csi, Player player) {
        csi.print(0,0, "Health:" + health, CSIColor.RED);
        csi.print(1,1, "Score:" + points, CSIColor.RED);
        csi.print(x, y, getDrawString(), getColor());
        csi.refresh();
    }


    @Override
    public boolean isDead() {
        if(health <= 0){
            return true;
        }
        return false;
    }

    @Override
    public CSIColor getColor() {
        return CSIColor.RED_VIOLET;
    }

    @Override
    public String getDrawString() {
        return "@";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
