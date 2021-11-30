/*
 */
package ch.bbbaden.games;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael Schneider (michael.schneider@bbbaden.ch)
 */
public class LibBasicsEvents {
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private final WSwingConsoleInterface csi;

    public LibBasicsEvents() {
        csi = new WSwingConsoleInterface("Basic Game - Event Based");
        System.out.println("X-Dimension: " + csi.getXdim());
        System.out.println("Y-Dimension: " + csi.getYdim());
    }


    public void run() {



        Player p = new Player(1,1);


        int spawn = 0;
        setupGameObjects();
        // Draw static part
        setupGameBoard();
        do{
            spawn++;

            if(spawn == 5){
                objects.add(new Zombie(16,10));
                spawn = 0;
            }
            // Draw game board
            csi.restore();

            for (int i = 0; i < objects.size(); i++) {
                if(objects.get(i).isDead()){
                    objects.remove(i);
                }
            }


            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).update(csi,p);
                p.update(csi,p);
            }

            char key = (char)csi.inkey().code;
            p.action(key,csi);

        }while (p.getHealth() >= 0);

    }

    private void setupGameBoard() {
        csi.cls();
        csi.print(5, 5, "Welcome to THE game!", CSIColor.BABY_BLUE);
        csi.print(10, 9, "##############", CSIColor.LIGHT_GRAY);
        csi.print(10,10, "#  #         #", CSIColor.LIGHT_GRAY);
        csi.print(10,11, "#  ####      #", CSIColor.LIGHT_GRAY);
        csi.print(10,12, "#      #     #", CSIColor.LIGHT_GRAY);
        csi.print(10,13, "#       #    #", CSIColor.LIGHT_GRAY);
        csi.print(10,14, "#   ######   #", CSIColor.LIGHT_GRAY);
        csi.saveBuffer();
    }
    private void setupGameObjects(){

        for(int i = 0; i < 7; i++){

            Random random = new Random();

            int x = random.nextInt(80);
            int y = random.nextInt(25);

            objects.add(new Trap(x,y));

        }
        for(int i = 0; i < 7; i++){

            Random random = new Random();

            int x = random.nextInt(80);
            int y = random.nextInt(25);

            objects.add(new Medikit(x,y));

        }
        for(int i = 0; i < 8    ; i++){

            Random random = new Random();

            int x = random.nextInt(80);
            int y = random.nextInt(25);

            objects.add(new Zombie(x,y));

        }

    }

}
