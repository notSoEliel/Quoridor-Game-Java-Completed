import java.util.ArrayList;

public class Tablero {
    private String fichaA = "[1]";
    private String fichaB = "[2]";
    private String noFicha = "[ ]";

    private String muroV = "|";
    private String muroH = "---";
    private int cantidadMurosA = 10;
    private int cantidadMurosB = 10;

    private ArrayList<String> registro = new ArrayList<String>();

    String[] coorMuros = { " a ", "b", " c ", "d", " e ", "f", " g ", "h", " i ", "j", " k ", "l", " m ", "n",
            " o ", "p", " q " };

    String[][] tablero = {
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" },
            { "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ", " ", "   ",
                    " ",
                    "    " },
            { "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]", " ", "[ ]",
                    " ",
                    "[ ]" }
    };

    /**
     * Establece las posiciones iniciales para las fichas en el tablero
     * 
     * @param aX
     * @param aY
     * @param bX
     * @param bY
     */
    public Tablero(int aX, int aY, int bX, int bY) {
        tablero[bY][bX] = fichaB;
        tablero[aY][aX] = fichaA;
    }

    /**
     * Genera un String con el Tablero de Quoridor
     */
    public String getTablero() {
        String tableroS = "\n";
        for (int i = 0; i < coorMuros.length; i++) {
            tableroS += coorMuros[i];
        }
        tableroS += "\n";
        for (int f = 0; f < tablero.length; f++) {
            for (int c = 0; c < tablero[f].length; c++) {
                tableroS += tablero[f][c];
            }
            tableroS += coorMuros[f];
            tableroS += "\n";
        }

        return tableroS;
    }

    /**
     * Borra las fichas viejas antes de mover las fichas
     */
    public void borrarFichas(int aX, int aY, int bX, int bY) {
        tablero[bY][bX] = noFicha;
        tablero[aY][aX] = noFicha;
    }

    /**
     * Crea fichas nuevas en las posiciones del tablero que se quiera
     */
    public void moverFichas(int aX, int aY, int bX, int bY) {
        tablero[bY][bX] = fichaB;
        tablero[aY][aX] = fichaA;
    }

    /**
     * Genera muros horizontales en las coordenadas que ingrese
     * 
     * @param fila
     * @param columna
     */
    public void colocarMurosH(int fila, int columna) {
        tablero[fila][columna] = muroH;
    }

    /**
     * Sirve para generar el segundo muro horizontal y restar 
     * la cantidad de muros de cada jugador
     * 
     * @param fila
     * @param columna
     * @param jugador
     * @param dir
     */
    public void colocarMurosH(int fila, int columna, int jugador, String dir) {

        if (dir.equalsIgnoreCase("DERECHA"))
            tablero[fila][columna+2] = muroH;
        else 
            tablero[fila][columna-2] = muroH;

        if (jugador == 1) {
            cantidadMurosA--;
        } else {
            cantidadMurosB--;
        }
    }

    /**
     * Genera muros verticales en las coordenadas que ingrese
     * 
     * @param fila
     * @param columna
     */
    public void colocarMurosV(int fila, int columna) {
        tablero[fila][columna] = muroV;
    }


    /**
     * Sirve para generar el segundo muro vertical y restar 
     * la cantidad de muros de cada jugador
     * 
     * @param fila
     * @param columna
     * @param jugador
     * @param dir
     */
    public void colocarMurosV(int fila, int columna, int jugador, String dir) {
        tablero[fila][columna] = muroV;

        if (dir.equalsIgnoreCase("ABAJO"))
        tablero[fila+2][columna] = muroV;
        else 
        tablero[fila-2][columna] = muroV;
        

        if (jugador == 1) {
            cantidadMurosA--;
        } else {
            cantidadMurosB--;
        }
    }


    /**
     * Valida si el segundo muro horizontal es válido
     * 
     * @param columna
     * @return
     */
    public boolean muroInvalidoH(int columna) {
        if ((columna - 2) >= 0) {
            return false;
        } else if ((columna + 2) <= 16) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Valida si el segundo muro vertical es valido
     * 
     * @param fila
     * @return
     */
    public boolean muroInvalidoV(int fila) {
        if ((fila - 2) >= 0) {
            return false;
        } else if ((fila + 2) <= 16) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retorna la cantidad de muros que tiene el jugador 1
     * @return
     */
    public int getCantidadMurosA() {
        return cantidadMurosA;
    }

    /**
     * Retorna la cantidad de muros que tiene el jugador 2
     * @return
     */
    public int getCantidadMurosB() {
        return cantidadMurosB;
    }

    /**
     * Retorna el valor que hay en una casilla del tablero
     * @param fila
     * @param columna
     * @return
     */
    public String checkCasilla(int fila, int columna) {
        return tablero[fila][columna];
            
    }

    /**
     * Retorna cierto si al jugador le quedan muros y falso si no
     * @param jugador
     * @return
     */
    public boolean quedanMuros(int jugador) {
        if (jugador == 1 && cantidadMurosA < 1) {
            return false;
        } else if (jugador == 2 && cantidadMurosB < 1){
            return false;
        } else {
            return true;
        }
    }


    /**
     * Indica si hay un muro en la dirección 
     * que se quiera mover la ficha
     * @param fila
     * @param columna
     * @param opcion
     * @return
     */
    public boolean hayMuro (int fila, int columna, String opcion) {
        if (opcion.equalsIgnoreCase("UP") && (checkCasilla(fila - 1, columna)).equals("---")) {
            return true;
        } else if (opcion.equalsIgnoreCase("DOWN") && ( checkCasilla(fila + 1, columna)).equals("---")) {
            return true;
        } else if (opcion.equalsIgnoreCase("LEFT") && ( checkCasilla(fila , columna - 1)).equals("|")) {
            return true;
        } else if (opcion.equalsIgnoreCase("RIGHT") && ( checkCasilla(fila , columna + 1)).equals("|")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Anade al registro el tablero luego de cada jugada
     * 
     * @param tablero
     */
    public void addRegistro (String tablero) {
        registro.add(tablero);
    }

    /**
     * retorna los tableros guardados en el registro
     * @param i
     * @return
     */
    public String printRegistro (int i) {
        return registro.get(i);
    }

    /**
     * Retorna el tamano de la lista del registro
     * @return
     */
    public int getRegistroSize() {
        return registro.size();
    }

}
