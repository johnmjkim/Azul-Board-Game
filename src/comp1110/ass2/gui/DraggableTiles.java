package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            System.out.println(" mouse x, y : " + (mousex - BIG_TILE_IMAGE_SIZE_X/2) + ", " + (mousey - BIG_TILE_IMAGE_SIZE_Y/2));
            viewer.highlightNearestRectangle(mousex - BIG_TILE_IMAGE_SIZE_X/2,mousey - BIG_TILE_IMAGE_SIZE_Y/2);

        });
        this.setOnMouseReleased(event ->{
            this.x = event.getSceneX() - BIG_TILE_IMAGE_SIZE_X/2;
            this.y = event.getSceneY() - BIG_TILE_IMAGE_SIZE_X/2;
            double mouse_highlight_dist = Math.sqrt(Math.pow(viewer.highlighted.x - this.x, 2) + Math.pow(viewer.highlighted.y - this.y, 2));
            System.out.println(" Released at : x = " + this.x + " y = " + this.y + " distance = " + mouse_highlight_dist);

            if(mouse_highlight_dist <= BIG_TILE_IMAGE_SNAP_DISTANCE){
                StringBuilder SB = new StringBuilder();
                System.out.print(" Snapped , move : ");
                if(viewer.multiazul.isMoveValid(viewer.currentState, findDraftingMove())){
                    String draggable_move = findDraftingMove();
                    System.out.println("drafting valid : " + draggable_move);
                    viewer.setMove(draggable_move);
                    setLayoutX(viewer.highlighted.x);
                    setLayoutY(viewer.highlighted.y);
                }
                else if(viewer.multiazul.isMoveValid(viewer.currentState, findTilingMove())){
                    String draggable_move = findTilingMove();
                    System.out.println("tiling valid : " + draggable_move);
                    viewer.setMove(draggable_move);
                    setLayoutX(viewer.highlighted.x);
                    setLayoutY(viewer.highlighted.y);
                }
                else{
                    System.out.println("invalid");
                    returnTile();
                }

            }
            else{
                System.out.println(" Returned ");
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

    public void returnTile(){
        double start_x, start_y;
        if(fromFactory()){
            start_x = viewer.FACTORIES_COORDINATES.getFactoryCoordinates(index).getPos_x(tile_num);
            start_y = viewer.FACTORIES_COORDINATES.getFactoryCoordinates(index).getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
        }
        else if(fromCenter()){
            start_x = viewer.CENTER_COORDINATES.getPos_x(tile_num);
            start_y = viewer.CENTER_COORDINATES.getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
        }
        else if(fromStorage()){
            start_x = viewer.STORAGE_COORDINATES.getStorageRowCoordinates(index).getPos_x(tile_num);
            start_y = viewer.STORAGE_COORDINATES.getStorageRowCoordinates(index).getPos_y(tile_num);
            setLayoutX(start_x);
            setLayoutY(start_y);
        }
    }

    public boolean fromFactory(){
        return type == FACTORY;
    }

    public boolean fromCenter(){
        return type == CENTER;
    }

    public boolean fromStorage(){
        return type == STORAGE;
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