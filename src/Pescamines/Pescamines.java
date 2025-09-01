package Pescamines;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    Tauler t;
    boolean gameOver, winner;

    public static void main(String[] args) {
        PApplet.main("Pescamines.Pescamines");
    }

    public void settings(){
        size(800, 800);
    }

    public void setup(){
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);
        t.setCaselles();
        t.setBombes(this);
        t.calculaNumBombes();
        gameOver = false;
        winner = false;
    }

    public void draw(){
        background(220);
        t.display(this);
        if(gameOver){
            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48);
            text("GAME OVER", width/2, height/2);
        }
        else if(winner){
            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48);
            text("YOU WIN", width/2, height/2);
        }
    }

    public void mousePressed() {
        if (!gameOver) {
            int[] indexos = t.casellaClicada(mouseX, mouseY);
            int f = indexos[0];
            int c = indexos[1];

            t.updateClicks();

            if (t.tauler[f][c].esBomba) {
                gameOver = true;
                t.setDescobrirTotes(true);
            }
            else {
                t.updateTauler(f, c);
                if (t.numCasellesVisibles == t.numCasellesPerObrir) {
                    winner = true;
                    t.setDescobrirTotes(true);
                }
            }
        }
    }
}
