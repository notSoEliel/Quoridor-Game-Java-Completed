import java.util.Scanner;
/* Cosas a tener en cuenta
    Total de filas 16 (empezando desde el 0)
    total de columnas 16 (empezando desde el 0)
    posiciones de fichas: pares del 0 al 16 para filas y columnas
    cada que una ficha se mueve en cualquier direccion del tablero se mueve dos posiciones
    la ficha solo se puede mover en una direccion a la vez
    posiciones de muros ver: pares del 0 al 16 para filas e impares del 1 al 15 para columas
    posiciones de muros hor: impares del 1 al 15 para las filas y pares del 0 al 16 para columnas
*/

public class Quoridor {
    public static void main(String[] args) {
        Tablero t; // declaro la variable para el objeto de Tablero
        Scanner in = new Scanner(System.in);
        Fichas f = new Fichas(); // declaro y creo un objeto de la clase Fichas
        boolean error = false, salir = false;
        String op;
        char caracterCoordenada;
        int fila, columna;

        System.out.println("BIENVENIDO A QUORIDOR");

        // las X son las columnas y las Y las filas

        System.out.print("\nNombre del jugador 1: ");
        f.setNomJugador1(in.nextLine()); // escaneo el parametro para el metodo setNomJugador1
        f.setaX(8); // inicio la coordenada X de la ficha A en 8
        f.setaY(16); // inicio la coordenada Y de la bicha A en 16

        System.out.print("\nNombre del jugador 2: ");
        f.setNomJugador2(in.nextLine()); // escaneo el parametro para el metodo setNomJugador2
        f.setbX(8); // inicio la coordenada X de la bicha B en 8
        f.setbY(0); // inicio la coordenada Y de la bicha A en 0

        t = new Tablero(f.getaX(), f.getaY(), f.getbX(), f.getbY()); // creo el objeto de la clase Tablero y coloca las
                                                                     // fichas en sus lugares
        t.addRegistro(t.getTablero());
        
        while (!salir) { // ciclo que se repite mientras no haya ganado nadie
            // Empieza turno del jugador 1
            System.out.println(t.getTablero());  // imprime el tablero
            do {
                error = false;
                System.out.print("\nEs el turno del jugador 1" + " (" + f.getNomJugador1() + "). Te quedan " + t.getCantidadMurosA() + " muros\n");
                System.out.println("----------Menú del juego----------");
                System.out.println("\ta. Mover ficha \n\tb. Colocar muro \n\tc. EXIT");
                System.out.println("----------------------------------");

                do {
                    op = in.nextLine();
                    error = false; // error falso para salir del ciclo
                    // equalsIgnoreCase hace que no sea case sensitive
                    if (!(op.equalsIgnoreCase("a") || op.equalsIgnoreCase("b") || op.equalsIgnoreCase("c"))) {
                        error = true; // si hay un error, error es cierto para continuar el ciclo
                        System.out.println("Escoja una opción válida");
                    }
                } while (error);

                if (op.equalsIgnoreCase("a")) { // opcion para mover ficha
                    
                    do { // Ciclo hasta que las opcion escogida sea válida
                        System.out.println("\nIngresa: [UP]-[DOWN]-[LEFT]-[RIGHT] para moverte");
                        op = in.nextLine();
                        error = false; // error falso para salir del ciclo
                        // equalsIgnoreCase hace que no sea case sensitive
                        if (!(op.equalsIgnoreCase("UP") || op.equalsIgnoreCase("DOWN") || op.equalsIgnoreCase("RIGHT")
                                || op.equalsIgnoreCase("LEFT"))) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("Escoja una opción válida");
                        } else if (f.movInvalidaA(op)) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("No puede salirse del tablero");
                        } else if (t.hayMuro(f.getaY(), f.getaX(), op)) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("No puede atravesar muros");
                        }
                    } while (error);

                    t.borrarFichas(f.getaX(), f.getaY(), f.getbX(), f.getbY()); // Borra las fichas viejas antes del
                                                                                // movimiento
                    f.moverA(op); // mueve las coordenadas de las fichas
                    t.moverFichas(f.getaX(), f.getaY(), f.getbX(), f.getbY()); // Crea nuevas fichas en las nuevas
                                                                            // coordenadas
                                                                            // para moverlas
                    t.addRegistro(t.getTablero()); //se anade el movimiento al registro

                } else if (op.equalsIgnoreCase("b")) { //opción para poner el muro
                    if (t.quedanMuros(1)){
                        System.out.println("\na. Muro horizontal [---] \nb. Muro vertical [|]");
                        do {
                            op = in.nextLine();
                            error = false; // error falso para salir del ciclo
                            // equalsIgnoreCase hace que no sea case sensitive
                            if (!(op.equalsIgnoreCase("a") || op.equalsIgnoreCase("b"))) {
                                error = true; // si hay un error, error es cierto para continuar el ciclo
                                System.out.println("Escoja una opción válida");
                            }
                        } while (error);

                        if (op.equalsIgnoreCase("a")) { //opcion para colocar dos muros horizontales
                            
                            do {
                                System.out.println("\nMuro horizontal [---] ATENCION: SE COLOCAN DOS MUROS");
                                do {
                                    System.out.println("\nIngrese la letra de la fila del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las filas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    fila = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que el caracter esté entre "a" y "q" 
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la fila sea impar
                                    } else if (fila%2 == 0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta fila");
                                    }
                                } while (error);

                                do {
                                    System.out.println("\nIngrese la letra de la columna del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las columnas de la matriz con el codico ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    columna = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que el caracter esté entre "a" y "q"
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la columna sea par
                                    } else if (columna%2 !=0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta columna");
                                    }
                                } while (error);

                                //primero se verifica que donde se quiera colocar el muro no exista ya uno
                                if (t.checkCasilla(fila, columna).equals("---")) {
                                    System.out.println("Ya existe un muro horizontal [---] en esta coordenada");
                                    error = true;
                                //se verifica que a la derecha de donde se colocara el muro no esté fuera de la matriz y que a su izquierda no haya un muro para evitar problemas con los muros dobles
                                } else if (columna + 2 > 16 ) {
                                    if (t.checkCasilla(fila, columna - 2).equals("---")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                    }
                                //se verifica que a la izquierda de donde se colocara el muro no este fuera de la matriz y que a su derecha no haya un muro para evitar problemas con los muros dobles
                                } else if (columna - 2 < 0 ) {
                                    if (t.checkCasilla(fila, columna + 2).equals("---")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                    }
                                //se verifica que no hayan muros justo a la izquierda y derecha del muro a colocar para evitar problemas con los muros dobles
                                } else if (t.checkCasilla(fila, columna + 2).equals("---") && t.checkCasilla(fila, columna - 2).equals("---")) {
                                    error = true;
                                    System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                }
                            } while (error);
                            //se coloca el primer muro en la matriz y se imprime el tablero
                            t.colocarMurosH(fila, columna);
                            System.out.println(t.getTablero());

                            //codigo para segundo muro a la derecha o izquierda del primero
                            do{
                                System.out.println("\nQuiere que el segundo muro se coloque a la [DERECHA] o a la [IZQUIERDA]?");
                                //se guarda la eleccion
                                op = in.nextLine();
                                error = false;
                                //se verifica que la elección sea valida
                                if (!(op.equalsIgnoreCase("DERECHA") || op.equalsIgnoreCase("IZQUIERDA"))) {
                                    error = true;
                                    System.out.println("Ingrese una opcion valida");
                                } else {
                                    if (op.equalsIgnoreCase("DERECHA")) {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (columna + 2 > 16 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su derecha, manda error
                                        } else if (t.checkCasilla(fila, columna + 2).equals("---")) {
                                            System.out.println("Ya existe un muro horizontal [---] en esta posicion");
                                            error = true;
                                        }
                                    } else {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (columna - 2 < 0 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su izquierda, manda error
                                        } else if (t.checkCasilla(fila, columna - 2).equals("---")) {
                                            System.out.println("Ya existe un muro horizontal [---] en esta posicion");
                                            error = true;
                                        }
                                    }
                                }
                            } while (error);
                            //se coloca el segundo muro horizontal en la matriz
                            t.colocarMurosH(fila, columna, 1, op);

                        } else if (op.equalsIgnoreCase("b")) { //opcion para colocar dos muros verticales
            
                            do {
                                System.out.println("\nMuro vertical [|] ATENCION: SE COLOCAN DOS MUROS");
                                do {
                                    System.out.println("\nIngrese la letra de la fila del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las filas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    fila = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que la letra este entre a y q
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la letra sea par
                                    } else if (fila%2 != 0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta fila");
                                    }
                                } while (error);

                                do {
                                    System.out.println("\nIngrese la letra de la columna del primer muro");
                                     //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las columnas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    columna = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que la letra este entre a y q
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la letra sea par
                                    } else if (columna%2 ==0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta columna");
                                    }
                                } while (error);
                                //primero se verifica que donde se quiera colocar el muro no exista ya uno
                                if (t.checkCasilla(fila, columna).equals("|")) {
                                    System.out.println("Ya existe un muro vertical [|] en esta coordenada");
                                    error = true;
                                //se verifica que abajo de donde se colocara el muro no esté fuera de la matriz y que arriba no haya un muro para evitar problemas con los muros dobles
                                } else if (fila + 2 > 16 ) {
                                    if (t.checkCasilla(fila - 2, columna).equals("|")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta coordenada");
                                    }
                                 //se verifica que arriba de donde se colocara el muro no esté fuera de la matriz y que abajo no haya un muro para evitar problemas con los muros dobles
                                } else if (fila - 2 < 0 ) {
                                    if (t.checkCasilla(fila + 2, columna).equals("|")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta coordenada");
                                    }
                                //se verifica que no hayan muros justo abajo y arriba del muro a colocar para evitar problemas con los muros dobles
                                } else if (t.checkCasilla(fila + 2, columna).equals("|") && t.checkCasilla(fila - 2, columna).equals("|")) {
                                    error = true;
                                    System.out.println("No se puede colocar un muro muro vertical [|] en esta coordenada");
                                } 
                            } while (error);

                            //se coloca el primer muro vertical y se imprime el tablero
                            t.colocarMurosV(fila, columna);
                            System.out.println(t.getTablero());

                            //codigo para segundo muro arriba o abajo del primero
                            do{
                                System.out.println("\nQuiere que el segundo muro se coloque [ARRIBA] o a la [ABAJO]?");
                                //se guarda la eleccion
                                op = in.nextLine();
                                error = false;

                                //se verifica que la elección sea valida
                                if (!(op.equalsIgnoreCase("ARRIBA") || op.equalsIgnoreCase("ABAJO"))) {
                                    error = true;
                                    System.out.println("Ingrese una opcion valida");

                                } else {
                                    if (op.equalsIgnoreCase("ABAJO")) {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (fila + 2 > 16 ) {
                                            System.out.println("No se puede colocar un muro vertical [|] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su abajo, manda error
                                        } else if (t.checkCasilla(fila  + 2, columna).equals("|")) {
                                            System.out.println("Ya existe un muro horizontal [|] en esta posicion");
                                            error = true;
                                        }
                                    } else {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (fila - 2 < 0 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro abajo, manda error
                                        } else if (t.checkCasilla(fila - 2, columna).equals("|")) {
                                            System.out.println("Ya existe un muro horizontal [|] en esta posicion");
                                            error = true;
                                        }
                                    }
                                }
                            } while (error);
                            //se coloca el segundo muro vertical en la matriz
                            t.colocarMurosV(fila, columna, 1, op);
                        }
                        //se anade al registro los muros que se hayan colocado
                        t.addRegistro(t.getTablero());
                    } else {
                        error = true;
                    }
                } else { //opcion para Interrumpir el juego
                    salir = true;
                }
            } while (error);

            if (salir || f.aGana()) {
                break;
            } 
            // Termina turno del jugador 1

            // Empieza turno del jugador 2
            System.out.println(t.getTablero());  // imprime el tablero 
            do {
                error = false;
                System.out.print("\n\nEs el turno del jugador 2" + " (" + f.getNomJugador2() + "). Te quedan " + t.getCantidadMurosB() + " muros\n");
                System.out.println("----------Menú del juego----------");
                System.out.println("\ta. Mover ficha \n\tb. Colocar muro \n\tc. EXIT");
                System.out.println("----------------------------------");
                do {
                    op = in.nextLine();
                    error = false; // error falso para salir del ciclo
                    // equalsIgnoreCase hace que no sea case sensitive
                    if (!(op.equalsIgnoreCase("a") || op.equalsIgnoreCase("b") || op.equalsIgnoreCase("c"))) {
                        error = true; // si hay un error, error es cierto para continuar el ciclo
                        System.out.println("Escoja una opción válida");
                    }
                } while (error);
    
                if (op.equalsIgnoreCase("a")) { // opcion para mover ficha
                    do { // Ciclo hasta que las opcion escogida sea válida
                        System.out.println("\nIngresa: [UP]-[DOWN]-[LEFT]-[RIGHT] para moverte");
                        op = in.nextLine();
                        error = false; // error falso para salir del ciclo
                        if (!(op.equalsIgnoreCase("UP") || op.equalsIgnoreCase("DOWN") || op.equalsIgnoreCase("RIGHT")
                                || op.equalsIgnoreCase("LEFT"))) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("Escoja una opción válida");
                        } else if (f.movInvalidaB(op)) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("No puede salirse del tablero");
                        } else if (t.hayMuro(f.getbY(), f.getbX(), op)) {
                            error = true; // si hay un error, error es cierto para continuar el ciclo
                            System.out.println("No puede atravesar muros");
                        }
                    } while (error);
    
                    t.borrarFichas(f.getaX(), f.getaY(), f.getbX(), f.getbY()); // Borra las fichas viejas antes del
                                                                                // movimiento
                    f.moverB(op); // mueve las coordenadas de las fichas
                    t.moverFichas(f.getaX(), f.getaY(), f.getbX(), f.getbY()); // Crea nuevas fichas en las nuevas
                                                                               // coordenadas
                                                                               // para moverlas
                    t.addRegistro(t.getTablero());

                } else if (op.equalsIgnoreCase("b")) {//opcion para poner muros
                    if (t.quedanMuros(2)){
                        System.out.println("\na. Muro horizontal [---] \nb. Muro vertical [|]");
                        do {
                            op = in.nextLine();
                            error = false; // error falso para salir del ciclo
                            // equalsIgnoreCase hace que no sea case sensitive
                            if (!(op.equalsIgnoreCase("a") || op.equalsIgnoreCase("b"))) {
                                error = true; // si hay un error, error es cierto para continuar el ciclo
                                System.out.println("Escoja una opción válida");
                            }
                        } while (error);

                        if (op.equalsIgnoreCase("a")) { //opcion para colocar dos muros horizontales
                            
                            do {
                                System.out.println("\nMuro horizontal [---] ATENCION: SE COLOCAN DOS MUROS");
                                do {
                                    System.out.println("\nIngrese la letra de la fila del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las filas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    fila = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que el caracter esté entre "a" y "q" 
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la fila sea impar
                                    } else if (fila%2 == 0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta fila");
                                    }
                                } while (error);

                                do {
                                    System.out.println("\nIngrese la letra de la columna del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las columnas de la matriz con el codico ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    columna = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que el caracter esté entre "a" y "q"
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la columna sea par
                                    } else if (columna%2 !=0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta columna");
                                    }
                                } while (error);

                                //primero se verifica que donde se quiera colocar el muro no exista ya uno
                                if (t.checkCasilla(fila, columna).equals("---")) {
                                    System.out.println("Ya existe un muro horizontal [---] en esta coordenada");
                                    error = true;
                                //se verifica que a la derecha de donde se colocara el muro no esté fuera de la matriz y que a su izquierda no haya un muro para evitar problemas con los muros dobles
                                } else if (columna + 2 > 16 ) {
                                    if (t.checkCasilla(fila, columna - 2).equals("---")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                    }
                                //se verifica que a la izquierda de donde se colocara el muro no este fuera de la matriz y que a su derecha no haya un muro para evitar problemas con los muros dobles
                                } else if (columna - 2 < 0 ) {
                                    if (t.checkCasilla(fila, columna + 2).equals("---")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                    }
                                //se verifica que no hayan muros justo a la izquierda y derecha del muro a colocar para evitar problemas con los muros dobles
                                } else if (t.checkCasilla(fila, columna + 2).equals("---") && t.checkCasilla(fila, columna - 2).equals("---")) {
                                    error = true;
                                    System.out.println("No se puede colocar un muro horizontal [---] en esta coordenada");
                                }
                            } while (error);
                            //se coloca el primer muro en la matriz y se imprime el tablero
                            t.colocarMurosH(fila, columna);
                            System.out.println(t.getTablero());

                            //codigo para segundo muro a la derecha o izquierda del primero
                            do{
                                System.out.println("\nQuiere que el segundo muro se coloque a la [DERECHA] o a la [IZQUIERDA]?");
                                //se guarda la eleccion
                                op = in.nextLine();
                                error = false;
                                //se verifica que la elección sea valida
                                if (!(op.equalsIgnoreCase("DERECHA") || op.equalsIgnoreCase("IZQUIERDA"))) {
                                    error = true;
                                    System.out.println("Ingrese una opcion valida");
                                } else {
                                    if (op.equalsIgnoreCase("DERECHA")) {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (columna + 2 > 16 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su derecha, manda error
                                        } else if (t.checkCasilla(fila, columna + 2).equals("---")) {
                                            System.out.println("Ya existe un muro horizontal [---] en esta posicion");
                                            error = true;
                                        }
                                    } else {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (columna - 2 < 0 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su izquierda, manda error
                                        } else if (t.checkCasilla(fila, columna - 2).equals("---")) {
                                            System.out.println("Ya existe un muro horizontal [---] en esta posicion");
                                            error = true;
                                        }
                                    }
                                }
                            } while (error);
                            //se coloca el segundo muro horizontal en la matriz
                            t.colocarMurosH(fila, columna, 2, op);

                        } else if (op.equalsIgnoreCase("b")) { //opcion para colocar dos muros verticales
            
                            do {
                                System.out.println("\nMuro vertical [|] ATENCION: SE COLOCAN DOS MUROS");
                                do {
                                    System.out.println("\nIngrese la letra de la fila del primer muro");
                                    //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las filas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    fila = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que la letra este entre a y q
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la letra sea par
                                    } else if (fila%2 != 0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta fila");
                                    }
                                } while (error);

                                do {
                                    System.out.println("\nIngrese la letra de la columna del primer muro");
                                     //se lee la letra de la coordenada y se transforma a entero, luego se le resta 97  para hacerlas equivalentes a las columnas de la matriz con el codigo ASCII
                                    caracterCoordenada = in.nextLine().charAt(0);
                                    columna = (int)caracterCoordenada - 97; 
                                    error = false;
                                    //se verifica que la letra este entre a y q
                                    if ((int)caracterCoordenada < 97 || (int)caracterCoordenada > 113) {
                                        error = true;
                                        System.out.println("Ingrese una letra válida");
                                    //se verifica que la letra sea par
                                    } else if (columna%2 ==0) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta columna");
                                    }
                                } while (error);
                                //primero se verifica que donde se quiera colocar el muro no exista ya uno
                                if (t.checkCasilla(fila, columna).equals("|")) {
                                    System.out.println("Ya existe un muro vertical [|] en esta coordenada");
                                    error = true;
                                //se verifica que abajo de donde se colocara el muro no esté fuera de la matriz y que arriba no haya un muro para evitar problemas con los muros dobles
                                } else if (fila + 2 > 16 ) {
                                    if (t.checkCasilla(fila - 2, columna).equals("|")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta coordenada");
                                    }
                                 //se verifica que arriba de donde se colocara el muro no esté fuera de la matriz y que abajo no haya un muro para evitar problemas con los muros dobles
                                } else if (fila - 2 < 0 ) {
                                    if (t.checkCasilla(fila + 2, columna).equals("|")) {
                                        error = true;
                                        System.out.println("No se puede colocar un muro vertical [|] en esta coordenada");
                                    }
                                //se verifica que no hayan muros justo abajo y arriba del muro a colocar para evitar problemas con los muros dobles
                                } else if (t.checkCasilla(fila + 2, columna).equals("|") && t.checkCasilla(fila - 2, columna).equals("|")) {
                                    error = true;
                                    System.out.println("No se puede colocar un muro muro vertical [|] en esta coordenada");
                                } 
                            } while (error);

                            //se coloca el primer muro vertical y se imprime el tablero
                            t.colocarMurosV(fila, columna);
                            System.out.println(t.getTablero());

                            //codigo para segundo muro arriba o abajo del primero
                            do{
                                System.out.println("\nQuiere que el segundo muro se coloque [ARRIBA] o a la [ABAJO]?");
                                //se guarda la eleccion
                                op = in.nextLine();
                                error = false;

                                //se verifica que la elección sea valida
                                if (!(op.equalsIgnoreCase("ARRIBA") || op.equalsIgnoreCase("ABAJO"))) {
                                    error = true;
                                    System.out.println("Ingrese una opcion valida");

                                } else {
                                    if (op.equalsIgnoreCase("ABAJO")) {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (fila + 2 > 16 ) {
                                            System.out.println("No se puede colocar un muro vertical [|] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro a su abajo, manda error
                                        } else if (t.checkCasilla(fila  + 2, columna).equals("|")) {
                                            System.out.println("Ya existe un muro horizontal [|] en esta posicion");
                                            error = true;
                                        }
                                    } else {
                                        //si es correcta y se sale de la matriz, manda un error
                                        if (fila - 2 < 0 ) {
                                            System.out.println("No se puede colocar un muro horizontal [---] en esta posicion");
                                            error = true;
                                        //si es correcta, pero ya hay un muro abajo, manda error
                                        } else if (t.checkCasilla(fila - 2, columna).equals("|")) {
                                            System.out.println("Ya existe un muro horizontal [|] en esta posicion");
                                            error = true;
                                        }
                                    }
                                }
                            } while (error);
                            //se coloca el segundo muro vertical en la matriz
                            t.colocarMurosV(fila, columna, 2, op);
                        }
                        //se anade al registro los muros que se hayan colocado
                        t.addRegistro(t.getTablero());
                    } else {
                        error = true;
                    }
                } else { //opcion para intrrumpir el juego
                    salir = true;
                }
            } while(error);
            // Termina turno del jugador 2

            if (f.bGana()) {
                break; // rompe el ciclo si B gana
            }
        } // fin del ciclo while

        if (f.aGana()) { // if para imprimir al ganador
            System.out.println("\n¡Felicidades! " + f.getNomJugador1() + ". Has ganado.");
        } else if (f.bGana()){
            System.out.println("\n¡Felicidades! " + f.getNomJugador2() + ". Has ganado.");
        } else if (salir) {
            System.out.println("\nPartida Interrumpida");
        }

        //Se imprime el tablero inicial (posicion 0 en el registro)
        System.out.println("\nPosicion inicial de los jugadores:");
        System.out.println(t.printRegistro(0));

        //Se imprime el registro o historial de jugadas
        System.out.println("\nRegistro de jugadas\n");
        for(int i = 1; i < t.getRegistroSize(); i++) {
            if (i%2 != 0) {
                System.out.println("Jugada número: " + i + " Turno de: " + f.getNomJugador1());
                System.out.println(t.printRegistro(i));
            } else {
                System.out.println("Jugada número: " + i + " Turno de: " + f.getNomJugador2());
                System.out.println(t.printRegistro(i));
            }
            
        }
        
        in.close();
    }
}