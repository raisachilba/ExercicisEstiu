package Pescamines;

import processing.core.PApplet;

public class Casella {

    int fila, columna;
    int numBombes;
    boolean esBomba, descobert;

    Casella(int f, int c, boolean b){
        this.fila = f;
        this.columna = c;
        this.numBombes = 0;
        this.esBomba = b;
        this.descobert = false;
    }

    public void setEsBomba(boolean b) {
        this.esBomba = b;
    }

    public void setNumBombes(int nb) {
        this.numBombes = nb;
    }

    public void setDescobert(boolean b) {
        this.descobert = b;
    }

    void display(PApplet p5, float xc, float yc, float w, float h, boolean descobrirTot) {

        if (descobrirTot) {

            p5.fill(150); p5.strokeWeight(3);
            p5.rect(xc, yc, w, h, 5);

            if (this.esBomba) {
                p5.fill(0);
                p5.circle(xc + w / 2, yc + h / 2, w / 2);
            }
            else {
                p5.fill(0); p5.textSize(36); p5.textAlign(p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
        }
        else {
            if (this.descobert) {
                p5.fill(220); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h);

                p5.fill(0); p5.textSize(36); p5.textAlign(p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
            else {
                p5.fill(150); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h, 5);
            }

        }
    }
}
