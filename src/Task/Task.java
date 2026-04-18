package Task;

import data.Progress;
import main.GamePanel;

import java.awt.*;

public class Task extends TaskManager{

    GamePanel gp;
    public final int noTask = 0;
    public final  int taskGotoUni = 1;
    public final  int taskKillTheZombie = 2;
    public final  int taskGetYourStuff = 3;
    public final  int taskKillVlad = 4;
    public final int taskOpenDoor = 5;
    public final int taskLeaveUni = 6;
    public final int taskEscapeToBus = 7;
    public int taskNum;
    public String activeObjective;


    public Task(GamePanel gp){
        super(gp);
        this.gp = gp;


        checkTask();
    }

    public void checkTask(){
        if(Progress.universityReached){
            taskNum = taskGetYourStuff;
        }
        if(Progress.firstScenePlayed){
            taskNum = taskKillTheZombie;
        }
        if(Progress.zombieKilled){
            taskNum = taskGetYourStuff;
        }
        if(Progress.stuffTaken){
            taskNum = taskLeaveUni;
        }
        if(Progress.secondScenePlayed){
            taskNum = taskKillVlad;
        }
        if(Progress.vladDefeated){
            taskNum = taskLeaveUni;
        }
        if(Progress.finalScenePlayed){
            taskNum = noTask;
        }
        switch(taskNum){
            case noTask : activeTask = noTask; taskActive = false; activeObjective = ""; break;
            case taskGotoUni : activeTask = taskGotoUni; taskActive = true;
            activeObjective = "> Go to the university."; break;
            case taskKillTheZombie : activeTask = taskKillTheZombie; taskActive = true;
            activeObjective = "> Kill the zombie."; break;
            case taskGetYourStuff : activeTask = taskGetYourStuff; taskActive = true;
            activeObjective = "> Take your stuff."; break;
            case taskKillVlad : activeTask = taskKillVlad; taskActive = true;
            activeObjective = "> Kill Prof.Vlad.";
            break;
            case taskOpenDoor : activeTask = taskOpenDoor; taskActive = true;
            activeObjective = "> Open the door."; break;
            case taskLeaveUni : activeTask = taskLeaveUni; taskActive = true;
            activeObjective = "> Leave."; break;
            case taskEscapeToBus: activeTask = taskEscapeToBus; taskActive = true;
            activeObjective = "> Escape to the bus."; break;
        }
    }


    public void drawTaskWindow(Graphics2D g2){
        if(gp.keyH.taskWindowActive) {


            int width = 200;
            int height = 100;
            int x = gp.screenWidth - width - 20;
            int y = gp.tileSize*2;
            gp.ui.drawSubWindow(x, y, width, height);

            g2.setFont(g2.getFont().deriveFont(20F));
            g2.setColor(Color.white);
            g2.drawString(activeObjective, x + 20, y + 40);

        }
    }

}
