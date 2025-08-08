package TresEnRatlla;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Casella {

    public enum VALOR { BLANC, CREU, CERCLE };

    int fila, columna;
    VALOR valor;

    float x, y, w;

    // Valor de la casella segons el Minimax
    int valorMiniMax;

    public Casella(int f, int c, float x, float y, float w){
        this.fila = f;
        this.columna = c;
        this.valor = VALOR.BLANC;
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public void setValor(VALOR v){
        this.valor = v;
    }

    // Setter de la propietat valorMiniMax
    public void setValorMiniMax(int m){
        this.valorMiniMax = m;
    }

    public void display(PApplet p5, PImage imgCreu, PImage imgCercle){
        p5.pushStyle();
        p5.rectMode(PConstants.CORNER);
        p5.fill(255);

        if(estaDins(p5.mouseX, p5.mouseY)){
            p5.fill(200);
        }

        p5.rect(x, y, w, w);

        if(valor == VALOR.CREU && imgCreu != null){
            p5.image(imgCreu, x, y, w, w);
        }
        else if(valor == VALOR.CERCLE && imgCercle != null){
            p5.image(imgCercle, x, y, w, w);
        }

        p5.popStyle();
    }

    public boolean estaDins(float x, float y){
        return (this.x<=x && x<=this.x+w && this.y<=y && y<=this.y+w);
    }
}
