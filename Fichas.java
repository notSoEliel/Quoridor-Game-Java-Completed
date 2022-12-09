public class Fichas {
    //a y b indican la ficha y X y Y indican las coordenadas
    private int aX, aY, bX, bY;
    private String nomJugador1, nomJugador2;
    
    public int getaX() {
        return aX;
    }
    public void setaX(int aX) {
        this.aX = aX;
    }
    public int getaY() {
        return aY;
    }
    public void setaY(int aY) {
        this.aY = aY;
    }
    public int getbX() {
        return bX;
    }
    public void setbX(int bX) {
        this.bX = bX;
    }
    public int getbY() {
        return bY;
    }
    public void setbY(int bY) {
        this.bY = bY;
    }
    public String getNomJugador1() {
        return nomJugador1;
    }
    public void setNomJugador1(String nomJugador1) {
        this.nomJugador1 = nomJugador1;
    }
    public String getNomJugador2() {
        return nomJugador2;
    }
    public void setNomJugador2(String nomJugador2) {
        this.nomJugador2 = nomJugador2;
    }

    /**
     * Mueve las coordenadas de la ficha A 
     * dos espacios dentro de la matriz
     * la dirección correspondiente
     * 
     * @param movida
     */
    public void moverA(String movida) {
        if (movida.equalsIgnoreCase("UP")) {
            aY -= 2;
        } else if (movida.equalsIgnoreCase("DOWN")) {
            aY += 2;
        } else if (movida.equalsIgnoreCase("LEFT")) {
            aX -= 2;
        } else if (movida.equalsIgnoreCase("RIGHT")) {
            aX += 2;
        } 
    }

    /**
     * Mueve las coordenadas de la ficha B
     * dos espacios dentro de la matriz
     * en la dirección correspondiente
     * 
     * @param movida
     */
    public void moverB(String movida) {
        if (movida.equalsIgnoreCase("UP")) {
            bY -= 2;
        } else if (movida.equalsIgnoreCase("DOWN")) {
            bY += 2;
        } else if (movida.equalsIgnoreCase("LEFT")) {
            bX -= 2;
        } else if (movida.equalsIgnoreCase("RIGHT")) {
            bX += 2;
        } 
    }

    /**
     * Indica si la movida de la ficha A 
     * es valida (si no se sale del tablero)
     * 
     * @param movida
     * @return
     */
    public boolean movInvalidaA(String movida) {
        if (movida.equalsIgnoreCase("UP") && (aY - 2) >= 0) {
            return false;
        } else if (movida.equalsIgnoreCase("DOWN") && (aY + 2) <= 16) {
            return false;
        } else if (movida.equalsIgnoreCase("LEFT") && (aX - 2) >= 0) {
            return false;
        } else if (movida.equalsIgnoreCase("RIGHT") && (aX + 2) <= 16) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Indica si la movida de la ficha B 
     * es valida (si no se sale del tablero)
     * 
     * @param movida
     * @return
     */
    public boolean movInvalidaB(String movida) {
        if (movida.equalsIgnoreCase("UP") && (bY - 2) >= 0) {
            return false;
        } else if (movida.equalsIgnoreCase("DOWN") && (bY + 2) <= 16) {
            return false;
        } else if (movida.equalsIgnoreCase("LEFT") && (bX - 2) >= 0) {
            return false;
        } else if (movida.equalsIgnoreCase("RIGHT") && (bX + 2) <= 16) {
            return false;
        } else {
            return true;
        }
     }

    /**
     * Determina si la ficha A (jugador 1) ganó 
     * 
     */
    public boolean aGana() {
        if (aY <= 0) {
            return true;
        } else {
            return false;
        }
    }

        /**
     * Determina si la ficha A (jugador 1) ganó 
     * 
     */
    public boolean bGana() {
        if (bY >= 16) {
            return true;
        } else {
            return false;
        }
    }
}
