package Tetris;

import processing.core.PApplet;

public class Tetris extends PApplet {

    Tauler t;
    Colors colorsTetris;
    Figura[] figures;
    int numFigures = 0;
    Figura figActual;
    int speed = 30;
    int numLinies = 0;
    boolean gameOver = false;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("Tetris.Tetris");
    }

    public void setup(){
        t = new Tauler(10,20, 200, 0, width/2, height);
        t.inicialitzaCaselles();
        colorsTetris = new Colors(this);
        figures = new Figura[10];
        figActual = Figura.creaFiguraRandom(this, t);
    }

    public void draw(){
        if (!gameOver && frameCount % speed == 0) {
            if (!figActual.mouBaix(t)) {
                if (figActual.fila == 0) {
                    gameOver = true;
                }
                else {
                    t.afegirFigura(figActual);
                    t.aplicaFigura(figActual);
                    figActual = Figura.creaFiguraRandom(this, t);
                    numFigures++;
                }
            }
            else {
                boolean[] plenes = t.comprovaFilesPlenes();
                for (int f = 0; f < plenes.length; f++) {
                    if (plenes[f] == true) {
                        t.baixarFiguresAbansDe(f);
                        numLinies++;
                    }
                }
            }
        }
        if(gameOver){
            dibuixaPantallaResultat();
        }
        else {
            dibuixaJOC();
        }
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            figActual.mouEsquerra(t);
        }
        else if(keyCode == RIGHT){
            figActual.mouDreta(t);
        }
        else if (keyCode == DOWN){
            figActual.mouBaix(t);
        }
        else if(key == 'b' || key == 'B'){
            figActual.mouTopeBaix(t);
        }
        else if(key=='r' || key=='R'){
            figActual.rota();
        }
    }

    public void dibuixaJOC(){
        background(200);
        pushMatrix();
        translate(t.x, t.y);
        t.dibuixaCaselles(this, colorsTetris.colorBUIT, colorsTetris.colors);
        t.dibuixaFigura(this, figActual, colorsTetris.colors);
        popMatrix();
        fill(0); textAlign(LEFT); textSize(20);
        text("Figures: "+ numFigures, 50, 50);
        text("Línies: "+ numLinies, 50, 70);
    }

    public void dibuixaPantallaResultat(){

        background(200, 100, 100);
        fill(0); textAlign(CENTER); textSize(50);
        text("GAME OVER", width / 2, height / 2);
        text("FIGURES:" + numFigures, width / 2, height / 2 + 100);
        text("LÍNIES:" + numLinies, width / 2, height / 2 + 200);
    }

}
