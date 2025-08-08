package Tetris;

import processing.core.PApplet;

public class Tauler {

    int x, y;
    int numCols, numFiles;
    float ampleCella, altCella;
    Figura.TIPUS_FIGURA[][] caselles;
    Figura[] figures;
    final int maxNumFigures = 100;
    int numFigures;

    Tauler(int nc, int nf, int x, int y, int w, int h){
        this.numCols = nc;
        this.numFiles = nf;
        this.x = x;
        this.y = y;
        this.ampleCella = w/(float)numCols;
        this.altCella = h/(float) numFiles;
        figures = new Figura[maxNumFigures];
        numFigures = 0;
    }

    void inicialitzaCaselles(){
        caselles = new Figura.TIPUS_FIGURA[numFiles][numCols];
        for(int c=0; c<numCols; c++){
            for(int f = 0; f< numFiles; f++){
                caselles[f][c]= Figura.TIPUS_FIGURA.BUIDA;
            }
        }
    }

    void dibuixaGraella(PApplet p5, int colorBUIT){
        for(float c=0; c<numCols; c++){
            for(float f = 0; f< numFiles; f++){
                p5.fill(colorBUIT);
                p5.stroke(0);
                p5.rect(c*ampleCella,f*altCella,ampleCella,altCella);
            }
        }
    }

    void aplicaFigura(Figura fig){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                if(fig.matriu[f][c]==1){
                    int ct = c + fig.col;
                    int ft = f + fig.fila;
                    this.caselles[ft][ct] = fig.tipusFigura;
                }
            }
        }
    }

    void dibuixaFigura(PApplet p5, Figura fig, int[] colors){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                int ct = c + fig.col;
                int ft = f + fig.fila;
                if(fig.matriu[f][c]==1){
                    p5.fill(colors[fig.tipusFigura.ordinal()]);
                    p5.stroke(0);
                    p5.rect(ct*ampleCella, ft*altCella,ampleCella,altCella);
                }
            }
        }
    }

    public void printTauler(){
        System.out.println("\nESTAT DEL TAULER: ");
        for(int f = 0; f< numFiles; f++){
            for(int c=0; c<numCols; c++){
                if(caselles[f][c] != Figura.TIPUS_FIGURA.BUIDA) {
                    System.out.print(caselles[f][c]);
                }
                System.out.print("\t|");
            }
            System.out.println();
        }
    }

    void afegirFigura(Figura f){
        if(numFigures<maxNumFigures){
            figures[numFigures] = f;
            numFigures++;
        }
    }

    void dibuixaFigures(PApplet p5, int[] colors){
        for(int i=0; i<numFigures; i++){
            dibuixaFigura(p5, figures[i], colors);
        }
    }

    void dibuixaCaselles(PApplet p5, int colorBUIT, int[] colors) {
        for (int c = 0; c < numCols; c++) {
            for (int f = 0; f < numFiles; f++) {
                p5.fill(colorBUIT);
                if (caselles[f][c] != Figura.TIPUS_FIGURA.BUIDA) {
                    int numColor = caselles[f][c].ordinal();
                    p5.fill(colors[numColor]);
                }
                p5.stroke(0);
                p5.rect(c * ampleCella, f * altCella, ampleCella, altCella);
            }
        }
    }

    boolean filaPlena(int nf){
        for(int c = 0; c< caselles[nf].length; c++){
            if(caselles[nf][c]== Figura.TIPUS_FIGURA.BUIDA){
                return false;
            }
        }
        return true;
    }

    boolean[] comprovaFilesPlenes(){
        boolean[] plenes = new boolean[caselles.length];
        for(int nf = caselles.length -1; nf>=0; nf--){
            plenes[nf] = filaPlena(nf);
        }
        return plenes;
    }

    void baixarFiguresAbansDe(int numf){
        for(int f=numf; f>0; f--){
            for(int c = 0; c < caselles[f].length; c++){
                caselles[f][c] = caselles[f-1][c];
            }
        }
        for(int c = 0; c < caselles[0].length; c++){
            caselles[0][c] = Figura.TIPUS_FIGURA.BUIDA;
        }

    }
}
