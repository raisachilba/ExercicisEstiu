package Tetris;

import processing.core.PApplet;

public class Figura {

    public enum TIPUS_FIGURA {BUIDA, I, S, SI, T, L, LI, O}
    TIPUS_FIGURA tipusFigura;

    int[][] matriu;
    int fila, col;

    Figura(int[][] matriu, TIPUS_FIGURA t){
        this.matriu = matriu;
        this.tipusFigura = t;
    }

    void setPosicio(int f, int c){
        this.fila = f;
        this.col = c;
    }

    void setTipusFigura(TIPUS_FIGURA tipusFigura){
        this.tipusFigura = tipusFigura;
    }

    public static Figura creaFigura(PApplet p5, Figura.TIPUS_FIGURA tipus, Tauler t){
        int f = 0;
        int c = (int) p5.random(0, t.numCols - 4);

        Figura fig;

        switch(tipus){
            case L:     fig = new FiguraL();  break;
            case T:     fig = new FiguraT();  break;
            case S:     fig = new FiguraS();  break;
            case O:     fig = new FiguraO();  break;
            case I:     fig = new FiguraI();  break;
            case LI:    fig = new FiguraLI(); break;
            case SI:    fig = new FiguraSI(); break;
            default:    fig = new FiguraO();  break;
        }
        fig.setPosicio(f, c);
        return fig;
    }

    public static Figura creaFiguraRandom(PApplet p5, Tauler tauler){
        int numFiguresDiferents = Figura.TIPUS_FIGURA.values().length;
        int n  = (int) p5.random(0, numFiguresDiferents);
        return creaFigura(p5, Figura.TIPUS_FIGURA.values()[n], tauler);
    }

    boolean posicioLliure(Tauler t, int ff, int cf){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]!=0 && t.caselles[ff+f][cf+c]!= Figura.TIPUS_FIGURA.BUIDA){
                    return false;
                }
            }
        }
        return true;
    }

    int getMaxCol(){
        int maxc = 0;
        for(int f = 0; f<matriu.length; f++){
            for(int c = 0; c<matriu.length; c++){
                if(matriu[f][c]==1 && c>maxc){
                    maxc = c;
                }
            }
        }
        return maxc;
    }
    int getMinCol(){
        int minc = matriu[0].length;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1 && c<minc){
                    minc=c;
                }
            }
        }
        return minc;
    }
    int getMaxFil(){
        int n=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1){
                    n = f;
                }
            }
        }
        return n;
    }
    int getMinFil(){
        int n=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1){
                    return f;
                }
            }
        }
        return n;
    }

    void mouEsquerra(Tauler t){
        if(this.col + this.getMinCol() > 0){
            int newCol = this.col - 1;
            if(posicioLliure(t, this.fila, newCol)){
                this.col--;
            }
        }
    }
    void mouDreta(Tauler t){
        if(this.col + this.getMaxCol() < t.numCols - 1){
            int newCol = this.col + 1;
            if(posicioLliure(t, this.fila, newCol)) {
                this.col++;
            }
        }
    }

    boolean mouBaix(Tauler t){

        if(this.fila + this.getMaxFil() < t.numFiles - 1){
            int newFila = this.fila + 1;
            if(posicioLliure(t, newFila, this.col)){
                this.fila++;
                return true;
            }
        }
        return false;
    }
    void mouTopeBaix(Tauler t){
        while(mouBaix(t));
    }

    int[][] copia(){
        int[][] q = new int[this.matriu.length][this.matriu[0].length];
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                q[f][c] = matriu[f][c];
            }
        }
        return q;
    }
    void rota(){
        int[][] q = this.copia();
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                matriu[f][c]=q[matriu.length - c -1][f];
            }
        }
    }
}
