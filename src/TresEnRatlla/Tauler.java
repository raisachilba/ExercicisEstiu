package TresEnRatlla;

import processing.core.PApplet;
import processing.core.PImage;

public class Tauler {

    float midaCasella;
    Casella[][] caselles;
    boolean hihaGuanyador;
    char guanyador;
    boolean finalPartida;
    int numTirades;

    PImage imgCreu, imgCercle;

    Casella[] casellesBuides;

    Casella[] valors;
    int numValors = 0;

    enum PLAYER {HUMA, ORDINADOR};

    public Tauler(int n, float w){
        caselles = new Casella[n][n];
        this.midaCasella = w / n;
        hihaGuanyador = false;
        guanyador = ' ';
        finalPartida = false;
        numTirades = 0;

        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c] = new Casella(f, c, midaCasella *c, midaCasella *f, midaCasella);
                caselles[f][c].setValor(Casella.VALOR.BLANC);
            }
        }

        valors = new Casella[n*n];
        numValors = 0;
    }

    public void display(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c].display(p5, imgCreu, imgCercle);
            }
        }
    }

    public void actualitzaGuanyador(){
        hihaGuanyador = comprovaGuanyador();
        if(hihaGuanyador){
            guanyador = numTirades%2==0 ? 'X' : 'O';
        }
        if(numTirades == caselles.length*caselles.length || hihaGuanyador){
            finalPartida = true;
        }
    }

    public void casellaPitjada(PApplet p5){
        if(!finalPartida) {
            for (int f = 0; f < caselles.length; f++) {
                for (int c = 0; c < caselles[f].length; c++) {
                    if (caselles[f][c].estaDins(p5.mouseX, p5.mouseY) && caselles[f][c].valor == Casella.VALOR.BLANC) {
                        if (numTirades%2==0) {
                            caselles[f][c].setValor(Casella.VALOR.CERCLE);
                        } else {
                            caselles[f][c].setValor(Casella.VALOR.CREU);
                        }
                        numTirades++;
                        break;
                    }
                }
            }
        }
    }

    public boolean comprovaGuanyador(){

        for(int f = 0; f< caselles.length; f++){
            if(filaIguals(f)== true){
                return true;
            }
        }

        for(int c = 0; c< caselles[0].length; c++){
            if(columnaIguals(c)== true){
                return true;
            }
        }

        return diagonalAscIguals() || diagonalDescIguals();
    }

    public boolean filaIguals(int f){
        boolean b = true;
        for(int c = 0; c< caselles[0].length-1; c++){
            b = b && (caselles[f][c].valor == caselles[f][c+1].valor);
        }
        return (b && caselles[f][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean columnaIguals(int c){
        boolean b = true;
        for(int f = 0; f< caselles.length-1; f++){
            b = b && (caselles[f][c].valor== caselles[f+1][c].valor);
        }
        return b && (caselles[0][c].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalAscIguals(){
        boolean b = true;
        int nc = caselles.length-1;
        for(int d=0; d<nc; d++){
            b = b && (caselles[nc-d][d].valor== caselles[nc-d-1][d+1].valor);
        }
        return b && (caselles[nc][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalDescIguals(){
        boolean b = true;
        for(int d = 0; d< caselles.length-1; d++) {
            b = b && (caselles[d][d].valor == caselles[d+1][d+1].valor);
        }
        return b && (caselles[0][0].valor!= Casella.VALOR.BLANC);
    }

    public void setImatges(PApplet p5){
        this.imgCreu   = p5.loadImage("creu.png");
        this.imgCercle = p5.loadImage("cercle.png");
    }


    // Retorna el número de caselles del tauler (NxN)
    int numCaselles(){
        return caselles.length*caselles.length;
    }

    // Indica si la partida segueix en joc o no
    boolean enJoc() {
        if (guanyaOrdinador() || guanyaPersona()){
            return false;
        }
        else if (estaPle()){
            return false;
        }
        else {
            return true;
        }
    }

    // Indica si guanya l'ordinador
    boolean guanyaOrdinador(){
        hihaGuanyador = comprovaGuanyador();
        return  hihaGuanyador && numTirades%2==0;
    }

    // Indica si guanya la Persona
    boolean guanyaPersona(){
        hihaGuanyador = comprovaGuanyador();
        return  hihaGuanyador && numTirades%2==1;
    }

    // Indica si totes les caselles del tauler són plenes
    boolean estaPle(){
        return numTirades == numCaselles();
    }

    // Retorna un array amb totes les caselles buides del tauler
    Casella[] getCasellesBuides() {

        casellesBuides = new Casella[numCaselles() - numTirades];

        int numBuida = 0;
        for (int f = 0; f < caselles.length; f++) {
            for (int c = 0; c < caselles[f].length; c++) {
                if (caselles[f][c].valor == Casella.VALOR.BLANC){
                    casellesBuides[numBuida] = caselles[f][c];
                    numBuida++;
                }
            }
        }
        return casellesBuides;
    }

    // Retorna el valor mínim
    public int retornaMin(int[] llista) {

        int min = Integer.MAX_VALUE;
        int index = Integer.MIN_VALUE;

        for (int i = 0; i < llista.length; ++i) {
            if (llista[i] < min) {
                min = llista[i];
                index = i;
            }
        }
        return llista[index];
    }

    // Retorna el valor màxim
    int retornaMax(int[] llista) {
        int max = Integer.MIN_VALUE;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < llista.length; ++i) {
            if (llista[i] > max) {
                max = llista[i];
                index = i;
            }
        }

        return llista[index];
    }

    // Mou a la casella c segons el jugador
    public void mou(Casella c, PLAYER jugador) {
        if(jugador==PLAYER.HUMA) {
            caselles[c.fila][c.columna].valor = Casella.VALOR.CERCLE;
        }
        else if(jugador==PLAYER.ORDINADOR) {
            caselles[c.fila][c.columna].valor = Casella.VALOR.CREU;
        }
    }

    // Algorisme MiniMax
    int minimax(int depth, PLAYER player) {

        if (guanyaOrdinador()){ return +1; }
        if (guanyaPersona()){return -1; }

        Casella[] casellesBuides = getCasellesBuides();

        if (casellesBuides.length==0){ return 0; }

        int[] marcadors = new int[casellesBuides.length];
        int numMarcadors = 0;

        for (int i = 0; i < casellesBuides.length; i++) {

            Casella c = casellesBuides[i];

            if(c!=null) {

                if (player == PLAYER.ORDINADOR) { //X's turn select the highest from below minimax() call
                    mou(c, PLAYER.ORDINADOR);
                    int currentScore = minimax(depth + 1, PLAYER.HUMA);
                    marcadors[numMarcadors] = currentScore;
                    numMarcadors++;

                    if (depth == 0) {
                        c.setValorMiniMax(currentScore);
                        valors[numValors] = c;
                        numValors++;
                    }

                } else if (player == PLAYER.HUMA) {//O's turn select the lowest from below minimax() call
                    mou(c, PLAYER.HUMA);
                    marcadors[numMarcadors] = minimax(depth + 1, PLAYER.ORDINADOR);
                    numMarcadors++;
                }

                caselles[c.fila][c.columna].valor = Casella.VALOR.BLANC; //Reset this point
            }
        }

        if( player == PLAYER.ORDINADOR ){
            return retornaMax(marcadors);
        }
        else {
            return retornaMin(marcadors);
        }
    }

    // Indica la casella del millor moviment
    Casella getMillorMoviment() {

        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < numValors; ++i) {
            if (max < valors[i].valorMiniMax) {
                max = valors[i].valorMiniMax;
                best = i;
            }
        }

        return valors[best];
    }

    // Crida a l'algorisme minimax
    void cridadaMinimax(int depth, PLAYER player){
        valors = new Casella[numCaselles()];
        numValors = 0;
        minimax(depth, player);
    }
}
