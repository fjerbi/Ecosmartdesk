package com.ecosmart.gui;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Cropper {

    enum Direction {NO,N,NE,E,SE,S,SO,O}

    enum Mode {CREATE,SELECT}

    public  Canvas          canvas;
    public  TextField       currentRect;
    public  Pane            container;
    public  Mode            currentMode;
    private Point2D         dragAnchor;
    private double          initX;
    private double          initY;
    public  Rectangle[]     corners = new Rectangle[8];
  
    

    public Cropper(Pane pane, Canvas canvas) {
        container = pane;
        this.canvas = canvas;
        setCanvasEventHandler();
        currentMode = Mode.CREATE;
        for (int i = 0; i < corners.length; i++) {
            corners[i] = new Rectangle(8, 8);
            corners[i].setOpacity(0.5d);
            corners[i].setStroke(Color.DARKRED);
            corners[i].setFill(Color.RED);
            corners[i].setVisible(false);
            corners[i].setFocusTraversable(false);
            container.getChildren().add(corners[i]);
            corners[i].addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                dragAnchor = new Point2D(e.getSceneX(), e.getSceneY());
                initX = ((Rectangle) e.getSource()).getTranslateX();
                initY = ((Rectangle) e.getSource()).getTranslateY();
            });

            corners[i].addEventFilter(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
                switch ((((Rectangle) e.getSource()).getCursor()).toString()) {

                    case "N_RESIZE":
                    case "S_RESIZE":
                        ((Rectangle) e.getSource()).setTranslateY(initY + e.getSceneY() - dragAnchor.getY());
                        break;
                    case "E_RESIZE":
                    case "W_RESIZE":
                        ((Rectangle) e.getSource()).setTranslateX(initX + e.getSceneX() - dragAnchor.getX());
                        break;
                    case "NW_RESIZE":
                    case "NE_RESIZE":
                    case "SE_RESIZE":
                    case "SW_RESIZE":
                        ((Rectangle) e.getSource()).setTranslateY(initY + e.getSceneY() - dragAnchor.getY());
                        ((Rectangle) e.getSource()).setTranslateX(initX + e.getSceneX() - dragAnchor.getX());
                        break;

                }
                fitRectangleToNewCorners(currentRect);
            });

        }
        corners[0].setCursor(Cursor.NW_RESIZE);
        corners[1].setCursor(Cursor.N_RESIZE);
        corners[2].setCursor(Cursor.NE_RESIZE);
        corners[3].setCursor(Cursor.E_RESIZE);
        corners[4].setCursor(Cursor.SE_RESIZE);
        corners[5].setCursor(Cursor.S_RESIZE);
        corners[6].setCursor(Cursor.SW_RESIZE);
        corners[7].setCursor(Cursor.W_RESIZE);

        corners[0].translateYProperty().bindBidirectional(corners[1].translateYProperty());
        corners[1].translateYProperty().bindBidirectional(corners[2].translateYProperty());

        corners[0].translateXProperty().bindBidirectional(corners[7].translateXProperty());
        corners[7].translateXProperty().bindBidirectional(corners[6].translateXProperty());

        corners[2].translateXProperty().bindBidirectional(corners[3].translateXProperty());
        corners[3].translateXProperty().bindBidirectional(corners[4].translateXProperty());

        corners[4].translateYProperty().bindBidirectional(corners[5].translateYProperty());
        corners[5].translateYProperty().bindBidirectional(corners[6].translateYProperty());

        corners[1].translateXProperty().bindBidirectional(corners[5].translateXProperty());
        corners[3].translateYProperty().bindBidirectional(corners[7].translateYProperty());

        corners[1].translateXProperty().bind(corners[0].translateXProperty().add((corners[2].translateXProperty().subtract(corners[0].translateXProperty())).divide(2)));
        corners[3].translateYProperty().bind(corners[0].translateYProperty().add((corners[4].translateYProperty().subtract(corners[2].translateYProperty())).divide(2)));

    }

    public TextField createRectangle(double x, double y, double width, double height) {
        TextField rec = new TextField();
        rec.setPrefHeight(1);
        rec.setPrefWidth(1);
        container.getChildren().add(rec);
        rec.setOpacity(0.7f);
        rec.setAlignment(Pos.BOTTOM_CENTER);
        rec.setTranslateX(x);
        rec.setTranslateY(y);
        rec.setFont(Font.font("System", FontWeight.BOLD, 14));
        rec.setPromptText("give a file name");
        rec.setCursor(Cursor.OPEN_HAND);      
        currentRect = rec;

        rec.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            if (currentMode == Mode.SELECT) {
                markRectangleAsCurrent((TextField) e.getSource());
                dragAnchor = new Point2D(e.getSceneX(), e.getSceneY());
                initX = ((TextField) e.getSource()).getTranslateX();
                initY = ((TextField) e.getSource()).getTranslateY();
            }
        });

        rec.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            ((TextField) e.getSource()).setTranslateX(initX + e.getSceneX() - dragAnchor.getX());
            ((TextField) e.getSource()).setTranslateY(initY + e.getSceneY() - dragAnchor.getY());
            surroundRectangleWithCorners(((TextField) e.getSource()));
        });

        rec.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {            
            this.currentMode=Mode.SELECT;
        });

        rec.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            this.currentMode=Mode.CREATE;
        });

        rec.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {

            if (e.getCode() == KeyCode.DELETE && currentRect == ((TextField) e.getSource())) {
                container.getChildren().remove(currentRect);
                setCurrentRectangleToNull();
            }           
        });
        return rec;
    }
    
    
    private void setCanvasEventHandler() {
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            if (currentMode == Mode.CREATE) {
                adjustRect(currentRect, e.getX() - initX, e.getY() - initY);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            if (currentMode == Mode.CREATE) {
                initX = e.getX();
                initY = e.getY();
                currentRect = createRectangle(initX, initY, 1, 1);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
                if (currentRect != null) {
                    setCurrentRectangleToNull();
                }
        });
    }


    public void markRectangleAsCurrent(TextField rec) {
        currentRect = rec;        
        surroundRectangleWithCorners(rec);
    }

    public void fitRectangleToNewCorners(TextField rec) {
        rec.setPrefWidth(corners[2].getLayoutX() + corners[2].getTranslateX() - corners[0].getLayoutX() - corners[0].getTranslateX());
        rec.setPrefHeight(corners[6].getLayoutY() + corners[6].getTranslateY() - corners[0].getLayoutY() - corners[0].getTranslateY());
        rec.setTranslateX(corners[0].getTranslateX() + corners[0].getWidth() / 2);
        rec.setTranslateY(corners[0].getTranslateY() + corners[0].getWidth() / 2);
    }

    public void adjustRect(TextField rec, double dx, double dy) {
        rec.setPrefWidth(dx);
        rec.setPrefHeight(dy);
    }

    public void hideCircles() {
        for (Rectangle rec : corners) {
            rec.setVisible(false);
        }
    }

    public void surroundRectangleWithCorners(TextField rec) {
        corners[0].setTranslateX(-corners[0].getWidth() / 2 + rec.getTranslateX());
        corners[0].setTranslateY(-corners[0].getWidth() / 2 + rec.getTranslateY());

        corners[4].setTranslateX(corners[0].getTranslateX() + rec.getWidth());
        corners[4].setTranslateY(corners[0].getTranslateY() + rec.getHeight());
        
        showCorners();
    }

    public void initCirclesTranslation() {
        for (Rectangle rec : corners) {
            rec.setTranslateX(0);
            rec.setTranslateY(0);
        }
    }

    public void takeSnapshot(String folder){
     Utility.save(container, canvas, folder);
    }
    

    private void showCorners() {
        for (Rectangle rec : corners) {
            rec.setVisible(true);
        }
    }

    public void setCurrentRectangleToNull() {
        if (currentRect != null) {
            hideCircles();
        }
        currentRect = null;
    }   
}

