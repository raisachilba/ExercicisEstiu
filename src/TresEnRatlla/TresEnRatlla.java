package TresEnRatlla;

import processing.core.PApplet;
import processing.core.PFont;

public class TresEnRatlla extends PApplet {

    // Tauler del joc
    Tauler t;

    // Dimensió del tauler
    final int N = 3;

    // Fonts per mostrar textos
    PFont font1, font2;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("TresEnRatlla.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(N, width);
        t.setImatges(this);

        // Carregar fonts
        font1 = createFont("Dimitri.ttf", 48);
        font2 = createFont("Platinum.ttf", 24);
    }

    public void draw(){

        background(200, 100, 100);
        t.display(this);

        if(t.enJoc()){

            if(t.numTirades%2==1) {
                t.cridadaMinimax(0, Tauler.PLAYER.ORDINADOR);
                t.mou(t.getMillorMoviment(), Tauler.PLAYER.ORDINADOR);
                t.numTirades++;
            }
        }
        else {
            fill(200, 150);
            rect(0, 0, width, height);
            if (t.hihaGuanyador) {
                textAlign(CENTER);
                textSize(24);
                fill(0);
                textFont(font1);
                text("GUANYADOR " + t.guanyador, width / 2, height / 2 - 20);
            }
            if (t.finalPartida) {
                textAlign(CENTER);
                textSize(24);
                fill(0);
                textFont(font2);
                text("FINAL PARTIDA", width / 2, height / 2 + 20);
            }
        }
    }

    public void mousePressed(){
        if(t.enJoc() && t.numTirades%2==0) {
            t.casellaPitjada(this);
            t.actualitzaGuanyador();
        }
    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            t = new Tauler(N, width);
            t.setImatges(this);
        }
    }
}
