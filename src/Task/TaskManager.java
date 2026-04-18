package Task;

import main.GamePanel;

public class TaskManager {

    public boolean tasksOn;
    public boolean taskActive;
    public int activeTask;
    GamePanel gp;

    public TaskManager(GamePanel gp){
        this.gp = gp;
        tasksOn = true;
    }


}
