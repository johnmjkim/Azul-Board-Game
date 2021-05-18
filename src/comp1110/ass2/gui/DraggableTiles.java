package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @author Si Bo, Hu
 * This part is setting Tiles(we show them as images) from Storage, Center, Mosaic as the Draggable Tiles,
 * then add the mouse function
 * One thing is highlight nearest position not finished, fortunately it is not necessary.
 */
public class DraggableTiles extends ImageView implements Constants {
    double x, y;
    int tile_num, index;
    char type;
    private double mousex, mousey;
    private Viewer viewer;

    public DraggableTiles (double x, double y, String image_link, Viewer viewer, char type, int tile_num) {
        super(new Image(image_link));
        this.toFront();
        this.type = type;
        this.tile_num = tile_num;
        this.viewer = viewer;
        mouseControlSetting();
    }

    public DraggableTiles (double x, double y, String image_link, Viewer viewer, char type, int index, int tile_num) {
        super(new Image(image_link));
        this.toFront();
        this.type = type;
        this.index = index;
        this.tile_num = tile_num;
        this.viewer = viewer;
        mouseControlSetting();
    }

    public void mouseControlSetting(){
        this.setOnMousePressed(event ->{
            mousex = event.getSceneX();
            mousey = event.getSceneY();
        });

        this.setOnMouseDragged(event ->{
            mousex = event.getSceneX();
            mousey = event.getSceneY();

            setLayoutX(mousex - BIG_TILE_IMAGE_SIZE_X/2);
            setLayoutY(mousey - BIG_TILE_IMAGE_SIZE_Y/2);
            //System.out.println(" mouse x, y : " + (mousex - BIG_TILE_IMAGE_SIZE_X/2) + ", " + (mousey - BIG_TILE_IMAGE_SIZE_Y/2));
            viewer.highlightNearestRectangle(mousex - BIG_TILE_IMAGE_SIZE_X/2,mousey - BIG_TILE_IMAGE_SIZE_Y/2);

        });
        this.setOnMouseReleased(event ->{
            this.x = event.getSceneX() - BIG_TILE_IMAGE_SIZE_X/2;
            this.y = event.getSceneY() - BIG_TILE_IMAGE_SIZE_X/2;
            double mouse_highlight_dist = Math.sqrt(Math.pow(viewer.highlighted.x - this.x, 2) + Math.pow(viewer.highlighted.y - this.y, 2));
            //System.out.println(" Released at : x = " + this.x + " y = " + this.y + " distance = " + mouse_highlight_dist);

            if(mouse_highlight_dist <= BIG_TILE_IMAGE_SNAP_DISTANCE){
                StringBuilder SB = new StringBuilder();
                //System.out.print(" Snapped , move : ");
                if(viewer.multiazul.isMoveValid(viewer.currentState, findDraftingMove())){
                    String draggable_move = findDraftingMove();
                    //System.out.println("drafting valid : " + draggable_move);
                    moveTile(draggable_move);
                }
                else if(viewer.multiazul.isMoveValid(viewer.currentState, findTilingMove())){
                    String draggable_move = findTilingMove();
                    //System.out.println("tiling valid : " + draggable_move);
                    moveTile(draggable_move);
                }
                else{
                    //System.out.println("invalid");
                    returnTile();
                }

            }
            else{
                //System.out.println(" Returned ");
                returnTile();
            }
        });
    }

    public int getIndex() {
        return this.index;
    }

    public int getTileNum() {
        return this.tile_num;
    }

    public char getType(){
        return this.type;
    }

    public void returnTile(){
        double start_x, start_y;
        if(fromFactory()){
            start_x = viewer.FACTORIES_COORDINATES.getFactoryCoordinates(index).getPos_x(tile_num);
            start_y = viewer.FACTORIES_COORDINATES.getFactoryCoordinates(index).getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
            //System.out.print(" From " + getType() + " : " + " index : " + index + " tile_num : " + tile_num);
            //System.out.println(" start_x : " + start_x + " start_y : " + start_y);
        }
        else if(fromCenter()){
            start_x = viewer.CENTER_COORDINATES.getPos_x(tile_num);
            start_y = viewer.CENTER_COORDINATES.getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
            //System.out.print(" From " + getType() + " : " + " tile_num : " + tile_num);
            //System.out.println(" start_x : " + start_x + " start_y : " + start_y);
        }
        else if(fromStorage()){
            start_x = viewer.STORAGE_COORDINATES.getStorageRowCoordinates(index).getPos_x(tile_num);
            start_y = viewer.STORAGE_COORDINATES.getStorageRowCoordinates(index).getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
            //System.out.print(" From "  + getType() + " : " + " index : " + index + " tile_num : " + tile_num);
            //System.out.println(" start_x : " + start_x + " start_y : " + start_y);
        }
    }

    public void moveTile(String draggable_move){
        setLayoutX(viewer.highlighted.x);
        setLayoutY(viewer.highlighted.y);
        viewer.setMove(draggable_move);
        viewer.playerMap.get(viewer.current_turn).setMove(viewer.move);
        String[] state = viewer.multiazul.applyMove(viewer.currentState, draggable_move);
        viewer.setState(state);
        //viewer.displayState(viewer.currentState);
        viewer.refreshDisplay();
        //viewer.setMove(null);
    }

    public boolean fromFactory(){
        return getType() == FACTORY;
    }

    public boolean fromCenter(){
        return getType() == CENTER;
    }

    public boolean fromStorage(){
        return getType() == STORAGE;
    }

    public String findDraftingMove(){
        StringBuilder SB = new StringBuilder();
        SB.append(viewer.current_turn);

        if(fromFactory()){
            SB.append(getIndex());
            SB.append(viewer.ss.factories.getFactory(getIndex()).getTileColor(getTileNum()));
        }
        else if(fromCenter()){
            SB.append(CENTER);
            SB.append(viewer.ss.center.getTileColor(getTileNum()));
        }

        if(viewer.highlighted.toStorage()){
            SB.append(viewer.highlighted.getIndex());
        }
        else if(viewer.highlighted.toFloor()){
            SB.append(FLOOR);
        }
        return String.valueOf(SB);
    }

    public String findTilingMove(){
        StringBuilder SB = new StringBuilder();
        SB.append(viewer.current_turn);

        if(fromStorage()){
            SB.append(getIndex());
        }

        if(viewer.highlighted.toMosaic()){
            if(viewer.highlighted.index == this.index){
                SB.append(viewer.highlighted.getTileNum());
            }
        }
        else if(viewer.highlighted.toFloor()){
            SB.append(FLOOR);
        }
        return String.valueOf(SB);
    }
}