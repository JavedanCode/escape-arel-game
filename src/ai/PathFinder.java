package ai;

import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<Node>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;

        instantiateNodes();

    }


    public void instantiateNodes() {

        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;

            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        //RESET OPEN LIST AND PATH LIST
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;

    }

    /*public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {

        resetNodes();

        //Set start and goal nodes
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision) {
                node[col][row].solid = true;
            }

            //CHECK INTERACTIVE TILES
            for (int i = 0; i < gp.iTile[1].length; i++) {
                if (gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible ) {
                    int itCol = gp.iTile[gp.currentMap][i].worldX / gp.iTile[gp.currentMap][i].solidArea.height;
                    int itRow = gp.iTile[gp.currentMap][i].worldY / gp.iTile[gp.currentMap][i].solidArea.width;
                    node[itCol][itRow].solid = true;

                }
            }

            //SET COST
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }*/
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // Set start and goal nodes
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision) {
                node[col][row].solid = true;
            }

            // Check interactive tiles
            for (int i = 0; i < gp.iTile[gp.currentMap].length; i++) {
                if (gp.iTile[gp.currentMap][i] != null ) {
                    int itColStart = (gp.iTile[gp.currentMap][i].worldX + gp.iTile[gp.currentMap][i].solidArea.x) / gp.tileSize;
                    int itRowStart = (gp.iTile[gp.currentMap][i].worldY + gp.iTile[gp.currentMap][i].solidArea.y) / gp.tileSize;
                    int itColEnd = (gp.iTile[gp.currentMap][i].worldX + gp.iTile[gp.currentMap][i].solidArea.x + gp.iTile[gp.currentMap][i].solidArea.width) / gp.tileSize;
                    int itRowEnd = (gp.iTile[gp.currentMap][i].worldY + gp.iTile[gp.currentMap][i].solidArea.y + gp.iTile[gp.currentMap][i].solidArea.height) / gp.tileSize;

                    for (int itCol = itColStart; itCol <= itColEnd; itCol++) {
                        for (int itRow = itRowStart; itRow <= itRowEnd; itRow++) {
                            if (itCol >= 0 && itCol < gp.maxWorldCol && itRow >= 0 && itRow < gp.maxWorldRow) {
                                node[itCol][itRow].solid = true;
                            }
                        }
                    }
                }
            }

            // Set cost
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node){
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //F cost
        node.fCost = node.gCost + node.hCost;

    }

    public boolean search(){

        while(!goalReached&&step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            //Check the current node
            currentNode.checked = true;
            openList.remove(currentNode);


            //Open the Up node
            if(row - 1 >= 0){
                openNode(node[col][row-1]);

            }
            //Open the left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            //Open the right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            //Open the down node
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }

            //Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 1000;

            for(int i = 0; i < openList.size(); i++){
                if(openList.get(i).fCost < bestNodeFCost){
                    bestNodeFCost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }
                else if(openList.get(i).fCost == bestNodeFCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            if(openList.size() == 0){
                break;
            }

            //After the loop, openList[bestNodeIndex] is the next step (=current node)
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node){
        if(!node.solid && !node.checked && !node.open){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    public void trackThePath(){
        Node currentNode = goalNode;

        while(currentNode != startNode){
            pathList.add(0,currentNode);
            currentNode = currentNode.parent;
        }
    }
}

