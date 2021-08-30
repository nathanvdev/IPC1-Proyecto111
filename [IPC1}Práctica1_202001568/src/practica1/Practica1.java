package practica1;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Practica1 {

    static int Tablero[][] = new int[8][8], TableroP[][] = new int[8][8];
    static int Contador, ContPP = 0, ContP1 = 0, ContP2 = 0, ContP3 = 0, Posicion = 0, exp = 0, ContAc = 0;
    static char Opcion2 = 0;
    static boolean Penitencia = false;
    static String texto2 = "";
    static String texto4 = "";
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("#####.###");

    public static void main(String[] args) {

        //Penitencias();
        //Penalizaciones();
        //MostrarTablero();
        try {
            int opcion = 0;
            do {
                System.out.println("1. Iniciar Juego");
                System.out.println("2. Retomar Juego");
                System.out.println("3. Generar Reportes");
                System.out.println("4. Salir");
                System.out.println("");
                System.out.print("Elige una opcion ----> ");

                opcion = sc1.nextInt();

                switch (opcion) {
                    case 1:
                        Posicion = 1;
                        ContP1 = 0;
                        ContP2 = 0;
                        ContP3 = 0;
                        ContAc++;
                        texto4 += "                <tr>\n"
                                + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                                + "                    <td>Iniciar Juego</td>\n"
                                + "                    <td>El usuario inicio el juego</td>\n"
                                + "\n"
                                + "                </tr>\n"
                                + "                </tr>";

                        Penalizaciones();
                        Juego();
                        break;
                    case 2:

                        if (Posicion == 0) {
                            System.out.println("No has iniciado el juego" + "\n");
                            break;
                        }
                        ContAc++;
                        texto4 += "                <tr>\n"
                                + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                                + "                    <td>Reanudar Juego</td>\n"
                                + "                    <td>El usuario Reanudó el juego</td>\n"
                                + "\n"
                                + "                </tr>\n"
                                + "                </tr>";
                        Juego();
                        break;
                    case 3:
                        Scanner sc3 = new Scanner(System.in);
                        int Opcion3 = 0;
                        do {
                            System.out.println("1. Generar Reporte 1");
                            System.out.println("2. Generar Reporte 2");
                            System.out.println("3. Salir al menu principal");
                            System.out.print("Elige una opcion ----> ");
                            Opcion3 = sc3.nextInt();
                            System.out.println("");
                            
                            switch (Opcion3) {
                                case 1:
                                    GenerarReporte1();
                                    break;
                                    
                                case 2:
                                    GenerarReporte2();
                                    break;
                                    
                                default:
                                    break;

                            }

                        } while (Opcion3 != 3);
                        break;

                }
            } while (opcion != 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Juego() {

        MostrarTablero();

        do {

            if (Penitencia == true) {
                Penitencias();

                Penitencia = false;
            }
            //do {

            System.out.println("Presiona 'W' Para tirar el dado ");
            System.out.println("O 'P' Para pausar el juego y salir al menu principal");
            System.out.print("Elige una opcion ----> ");
            Opcion2 = sc2.next().charAt(0);

            if (Opcion2 == 'w') {

                int Dado = 0;
                Dado = (int) (Math.random() * 4 + 2);
                System.out.println("\n" + "El numero resultante del dado es: " + Dado);
                Posicion = Posicion + Dado;
                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Tirar dado</td>\n"
                        + "                    <td>resultado del dado = " + Dado + "<br>\n"
                        + "                    nueva posicion = " + Posicion + "</td>\n"
                        + "\n"
                        + "                </tr>\n"
                        + "                </tr>";

                if (Posicion >= 64) {
                    Posicion = 64;

                }

                MostrarTablero();

            } else if (Opcion2 == 'p') {
                System.out.println("\n" + "Volviendo al menu..." + "\n");
                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Regresar al menu </td>\n"
                        + "                    <td>Se pausara el juego y volvera al menu principal</td>\n"
                        + "                </tr>\n"
                        + "                </tr>";

            } else {
                System.out.println("### Opcion Incorrecta ###");
            }
            if (Posicion >= 64) {
                System.out.println("!!! Felicidades Has gando GG !!!" + "\n");
                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Fin del juego </td>\n"
                        + "                    <td>Se completo el juego y el usuario gano</td>\n"
                        + "                </tr>\n"
                        + "                </tr>";

            }

            //} while (Penitencia == false);
        } while (Opcion2 != 'p' && Posicion < 64);
    }

    public static void MostrarTablero() {

        try {

            Contador = 1;

            for (int i = 0; i < Tablero.length; i++) {
                for (int j = 0; j < Tablero.length; j++) {
                    Tablero[i][j] = Contador;
                    Contador++;
                }
            }

            boolean derecha = false;
            for (int i = Tablero.length - 1; i >= 0; i--) {
                if (derecha) {
                    for (int j = Tablero.length - 1; j >= 0; j--) {
                        if (Tablero[i][j] == Posicion && TableroP[i][j] == 1) {

                            Penitencia = true;
                            System.out.print("[" + Tablero[i][j] + "# @]");

                        } else if (Tablero[i][j] == Posicion) {
                            System.out.print("[" + Tablero[i][j] + "  @]");
                        } else if (TableroP[i][j] == 1) {
                            System.out.print("[" + Tablero[i][j] + "#  ]");
                        } else {
                            System.out.print("[" + Tablero[i][j] + "   ]");
                        }

                    }

                } else {
                    for (int j = 0; j < Tablero.length; j++) {
                        if (Tablero[i][j] == Posicion && TableroP[i][j] == 1) {

                            Penitencia = true;
                            System.out.print("[" + Tablero[i][j] + "# @]");

                        } else if (TableroP[i][j] == 1) {
                            System.out.print("[" + Tablero[i][j] + "#  ]");
                        } else if (Tablero[i][j] == Posicion) {
                            System.out.print("[" + Tablero[i][j] + "  @]");
                        } else {
                            System.out.print("[" + Tablero[i][j] + "   ]");
                        }

                    }

                }

                System.out.println("");
                derecha = !derecha;
            }

            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void Penalizaciones() {
        Random Generador = new Random();
        for (int i = 0; i < TableroP.length; i++) {
            int ContadorP = 0;
            for (int j = 0; j < TableroP.length; j++) {
                if (ContadorP <= 3) {
                    TableroP[i][j] = Generador.nextInt(2) + 0;
                    if (TableroP[i][j] == 1) {
                        ContadorP++;
                    }
                } else {
                    TableroP[i][j] = 0;
                }

            }
        }
    }

    public static void Penitencias() {

        System.out.println("¡Has caído en una penalización!");

        int Dado2 = 0;

        if (Posicion <= 16) {

            if (ContP1 != 2) {
                //do {
                Dado2 = (int) (1 + Math.random() * 3);
                //exp = Dado2;
                //} while (Dado2 != exp);

                System.out.println("Caso No." + Dado2);
                System.out.println("Penitencia Nivel Facil");
                System.out.println("Resuelve la ley de cosenos para Continuar con el juego" + "\n");

                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Penitencia</td>\n"
                        + "                    <td>Se realizo la penitencia de nivel facil caso No. " + Dado2 + " </td>\n"
                        + "                </tr>\n"
                        + "                </tr>";
            } else {
                Dado2 = 5;
            }

            switch (Dado2) {
                case 1:

                    LeyCosenosL(15, 25, 20, Dado2);
                    ContP1++;
                    break;
                case 2:

                    LeyCosenosL(10, 30, 25, Dado2);
                    ContP1++;
                    break;
                case 3:

                    LeyCosenosL(18, 30, 25, Dado2);
                    ContP1++;
                    break;
                default:

                    System.out.println("Has excedido el numero de penalizaciones por nivel, puedes continuar" + "\n");

                    break;
            }

        } else if (Posicion <= 40) {

            if (ContP2 != 2) {
                //do {
                Dado2 = (int) (1 + Math.random() * 3);
                //exp2 = Dado2;
                //} while (Dado2 != exp2);
                System.out.println("Penitencia Nivel Medio");
                System.out.println("Caso No." + Dado2);
                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Penitencia</td>\n"
                        + "                    <td>Se realizo la penitencia de nivel medio caso No. " + Dado2 + " </td>\n"
                        + "                </tr>\n"
                        + "                </tr>";
            } else {
                Dado2 = 5;
            }

            int A[][];
            int B[][];

            switch (Dado2) {
                case 1:

                    A = new int[][]{{7, 48, 5, 0, 1}, {57, 8, 4, 6, 14}, {0, 5, 6, 78, 15}, {21, 14, 8, 19, 54}, {32, 20, 26, 47, 12}};
                    B = new int[][]{{9, 5, 2, 1, 8}, {4, 2, 3, 47, 8}, {48, 55, 32, 19, 6}, {7, 56, 32, 14, 8}, {32, 87, 0, 1, 7}};
                    SumaMatrices(A, B, Dado2);
                    ContP2++;
                    break;

                case 2:
                    A = new int[][]{{4, 9, 7, 45, 18}, {7, 51, 26, 8, 38}, {48, 26, 37, 21, 19}, {1, 0, 6, 8, 1}, {2, 19, 55, 25, 15}};
                    B = new int[][]{{0, 2, 15, 1, 66}, {21, 48, 62, 7, 33}, {4, 88, 0, 68, 4}, {25, 18, 24, 7, 55}, {24, 15, 36, 5, 98}};
                    SumaMatrices(A, B, Dado2);
                    ContP2++;
                    break;

                case 3:
                    A = new int[][]{{0, 1, 15, 5, 36}, {1, 78, 65, 32, 4}, {48, 66, 39, 0, 55}, {14, 98, 63, 20, 15}, {11, 39, 84, 7, 1}};
                    B = new int[][]{{78, 25, 66, 48, 98}, {0, 45, 2, 3, 1}, {2, 9, 14, 10, 20}, {35, 87, 65, 2, 32}, {25, 8, 4, 9, 39}};
                    SumaMatrices(A, B, Dado2);
                    ContP2++;

                    break;
                default:
                    System.out.println("Has excedido el numero de penalizaciones por nivel, puedes continuar" + "\n");
                    break;
            }

        } else if (Posicion <= 64) {

            if (ContP3 != 2) {
                //do {
                Dado2 = (int) (1 + Math.random() * 3);
                //exp2 = Dado2;
                //} while (Dado2 != exp2);

                System.out.println("Penitencia Nivel Dificil");
                System.out.println("Caso No." + Dado2);
                ContAc++;
                texto4 += "                <tr>\n"
                        + "                    <th scope=\"row\">" + ContAc + "</th>\n"
                        + "                    <td>Penitencia</td>\n"
                        + "                    <td>Se realizo la penitencia de nivel dificl caso No. " + ContAc + " </td>\n"
                        + "                </tr>\n"
                        + "                </tr>";

            } else {
                Dado2 = 5;
            }

            double A[][];
            double B[][];

            switch (Dado2) {

                case 1:

                    A = new double[][]{{5, 10, 1, 3}, {9, 14, 2, 6}, {7, 8, 15, 3}, {6, 8, 9, 2}};
                    B = new double[][]{{5, 13, 9, 4}, {1, 9, 6, 3}, {8, 11, 69, 33}, {25, 6, 7, 4}};
                    DivisionMatrices(A, B, Dado2);
                    ContP3++;
                    break;
                case 2:
                    A = new double[][]{{1, 12, 9, 8}, {7, 6, 3, 2}, {0, 5, 6, 14}, {6, 9, 6, 10}};
                    B = new double[][]{{8, 19, 20, 4}, {12, 33, 6, 8}, {4, 5, 9, 7}, {8, 22, 14, 6}};
                    DivisionMatrices(A, B, Dado2);
                    ContP3++;
                    break;
                case 3:
                    A = new double[][]{{5, 9, 14, 5}, {6, 0, 5, 3}, {1, 14, 68, 8}, {7, 5, 3, 9}};
                    B = new double[][]{{0, 9, 7, 19}, {2, 30, 5, 48}, {1, 31, 2, 5}, {15, 8, 4, 3}};
                    DivisionMatrices(A, B, Dado2);
                    ContP3++;
                    break;
                default:
                    System.out.println("Has excedido el numero de penalizaciones por nnivel, puedes continuar" + "\n");
                    break;
            }

        }

    }

    public static double[] LeyCosenosL(double A, double c, double B, int caso) {
        double Res[] = new double[3];
        System.out.println("Valores dados");
        System.out.println("Lado A = " + A);
        System.out.println("Lado C = " + B);
        System.out.println("Lado α = " + c + "\n");

        Res[0] = Math.sqrt((Math.pow(A, 2) + Math.pow(B, 2)) - (2 * A * B * Math.cos(Math.toRadians(c))));
        Res[1] = Math.toDegrees(Math.acos((Math.pow(A, 2) - Math.pow(B, 2) - Math.pow(Res[0], 2)) / (-2 * B * Res[0])));
        Res[2] = 180 - (c + Res[1]);

        System.out.println("El resultado del lado B es = " + df.format(Res[0]));
        System.out.println("El resultado del angulo β es = " + df.format(Res[1]) + "°");
        System.out.println("El resultado del angulo γ es = " + df.format(Res[2]) + "°" + "\n");
        ContPP++;

        texto2 += "                <tr>\n"
                + "                    <th scope=\"row\">" + ContPP + "</th>\n"
                + "                    <td>Ley de Cosenos<br> Caso No." + caso + " </td>\n"
                + "                    <td>1. Mediante la fórmula A^2 = B^2 + C^2 - 2 Cos(a)\n"
                + "                        se despejó para encontrar el lado B del triángulo<br>\n"
                + "                        2. Seguido de igualmete despejarla para Encontrar el Angulo β del trángulo<br>\n"
                + "                        3. Y para encontrar el angulo faltante se realizo con la ecuación γ = 180-(α+β)\n"
                + "                    </td>\n"
                + "                    <td>El resultado del lado B es =  +" + df.format(Res[0]) + "+<br>\n"
                + "                        El resultado del angulo β es =  +" + df.format(Res[1]) + "+ °<br>\n"
                + "                        El resultado del angulo γ es =  +" + df.format(Res[2]) + "+°<br>\n"
                + "                </tr>";

        return Res;

    }

    public static int SumaMatrices(int MatrizA[][], int MatrizB[][], int caso) {
        int MatrizC[][] = new int[5][5];
        System.out.println("Matriz A");
        ImprimirMatriz(MatrizA);
        System.out.println("");
        System.out.println("Matriz B");
        ImprimirMatriz(MatrizB);
        System.out.println("");

        System.out.println("la matriz resultante es");
        for (int i = 0; i < MatrizB.length; i++) {
            for (int j = 0; j < MatrizA.length; j++) {
                MatrizC[i][j] = MatrizA[i][j] + MatrizB[i][j];
                System.out.print("[" + MatrizC[i][j] + "]");
            }
            System.out.println("");
        }
        ContPP++;

        texto2 += "                <tr>\n"
                + "                    <th scope=\"row\">" + ContPP + "</th>\n"
                + "                    <td>Suma de Matrices<br>\n"
                + "                        Caso No." + caso + "</td>\n"
                + "                    <td>\n"
                + "                       La operación se define de una manera muy sencilla: la matriz suma de dos matrices<br>\n"
                + "                        con la misma dimensión es la matriz que tiene en la posición fila i y columna j la suma<br>\n"
                + "                        de los elementos de la misma posición en las matrices que sumamos.<br>\n"
                + "                        Es decir, la suma de matrices se calcula sumando los elementos que ocupanla misma posición.\n"
                + "                    </td>\n"
                + "                    <td>\n";
        for (int i = 0; i < MatrizB.length; i++) {
            for (int j = 0; j < MatrizC.length; j++) {
                texto2 += " [" + MatrizC[i][j] + "] ";
            }
            texto2 += "<br>";
        }
        texto2 += "                    </td>\n"
                + "                </tr>";

        return 0;

    }

    public static void ImprimirMatriz(int Matrix[][]) {
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                System.out.print("[" + Matrix[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public static int DivisionMatrices(double MatrizA[][], double MatrizB[][], int caso) {
        double[][] Inv1 = new double[4][8];
        double[][] Q = new double[4][8];
        double[][] Inversa = new double[4][4];
        double[] piv = new double[Inv1.length];
        double[] Fila0, Fila1, Fila2, Fila3;

        System.out.println("Matriz A:");
        ImprimirMatriz(MatrizA);

        if (MatrizB[0][0] == 0) {

            Fila0 = MatrizB[0];
            Fila1 = MatrizB[1];
            MatrizB[0] = Fila1;
            MatrizB[1] = Fila0;
            System.out.println("\n" + "Se intercambiaron las filas 1 y 2 para poder realizar el proceso correctamente");

        } else if (MatrizB[1][1] == 0) {

            Fila1 = MatrizB[1];
            Fila2 = MatrizB[2];
            MatrizB[1] = Fila2;
            MatrizB[2] = Fila1;
            System.out.println("\n" + "Se intercambiaron las filas 2 y 3 para poder realizar el proceso correctamente");

        } else if (MatrizB[2][2] == 0) {

            Fila2 = MatrizB[2];
            Fila3 = MatrizB[3];
            MatrizB[2] = Fila3;
            MatrizB[3] = Fila2;
            System.out.println("\n" + "Se intercambiaron las filas 2 y 3 para poder realizar el proceso correctamente");

        } else if (MatrizB[3][3] == 0) {

            Fila3 = MatrizB[3];
            Fila0 = MatrizB[0];
            MatrizB[3] = Fila0;
            MatrizB[3] = Fila0;
            System.out.println("\n" + "Se intercambiaron las filas 3 y 1 para poder realizar el proceso correctamente");
        }

        for (int i = 0; i < MatrizB.length; i++) {
            for (int j = 0; j < MatrizB[i].length; j++) {
                Inv1[i][j] = MatrizB[i][j];
            }
        }

        System.out.println("MatrizB:");
        ImprimirMatriz(MatrizB);
        System.out.println("\n" + "Resultado:");

        Inv1[0][4] = 1;
        Inv1[1][5] = 1;
        Inv1[2][6] = 1;
        Inv1[3][7] = 1;

        Fila0 = Inv1[0];
        Fila1 = Inv1[1];
        Fila2 = Inv1[2];
        Fila3 = Inv1[3];

        for (int i = 0; i < 8; i++) {
            Inv1[1][i] = (MatrizB[0][0] * Fila1[i]) - (MatrizB[1][0] * Fila0[i]);
            Inv1[2][i] = (MatrizB[0][0] * Fila2[i]) - (MatrizB[2][0] * Fila0[i]);
            Inv1[3][i] = (MatrizB[0][0] * Fila3[i]) - (MatrizB[3][0] * Fila0[i]);
        }

        Q = ActualizarM(Inv1);

        for (int i = 0; i < 8; i++) {
            Inv1[2][i] = (Q[1][1] * Fila2[i]) - (Q[2][1] * Fila1[i]);
            Inv1[3][i] = (Q[1][1] * Fila3[i]) - (Q[3][1] * Fila1[i]);
        }

        Q = ActualizarM(Inv1);

        for (int i = 0; i < 8; i++) {
            Inv1[3][i] = (Q[2][2] * Fila3[i]) - (Q[3][2] * Fila2[i]);
        }

        Q = ActualizarM(Inv1);

        for (int i = 0; i < 8; i++) {
            Inv1[0][i] = (Q[3][3] * Fila0[i]) - (Q[0][3] * Fila3[i]);
            Inv1[1][i] = (Q[3][3] * Fila1[i]) - (Q[1][3] * Fila3[i]);
            Inv1[2][i] = (Q[3][3] * Fila2[i]) - (Q[2][3] * Fila3[i]);
        }

        Q = ActualizarM(Inv1);

        for (int i = 0; i < 8; i++) {
            Inv1[1][i] = (Q[2][2] * Fila1[i]) - (Q[1][2] * Fila2[i]);
            Inv1[0][i] = (Q[2][2] * Fila0[i]) - (Q[0][2] * Fila2[i]);
        }

        Q = ActualizarM(Inv1);

        for (int i = 0; i < 8; i++) {
            Inv1[0][i] = (Q[1][1] * Fila0[i]) - (Q[0][1] * Fila1[i]);
        }

        for (int i = 0; i < Inv1.length; i++) {
            piv[i] = Inv1[i][i];
        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                Inv1[j][i] = Inv1[j][i] / piv[j];
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                MatrizB[i][j] = Inv1[i][j + 4];
            }
        }

        for (int i = 0; i < Inversa.length; i++) {
            for (int j = 0; j < Inversa.length; j++) {
                for (int k = 0; k < Inversa.length; k++) {
                    Inversa[i][j] += MatrizA[i][k] * MatrizB[k][j];
                }
            }
        }

        for (int i = 0; i < Inversa.length; i++) {
            for (int j = 0; j < Inversa[i].length; j++) {
                System.out.print("[" + df.format(Inversa[i][j]) + "]");
            }
            System.out.println("");
        }
        ContPP++;
        texto2 += "                <tr>\n"
                + "                    <th scope=\"row\">" + ContPP + "</th>\n"
                + "                    <td>Division de matrices<br>\n"
                + "                        Caso No." + caso + "</td>\n"
                + "                  <td>Determinar qué matriz va en el numerador y qué matriz va en denominador.<br>\n"
                + "                        Hacer la inversa de la matriz que vaya en el denominador.<br>\n"
                + "                        Multiplicar la matriz del numerador por la matriz inversa.\n"
                + "                    </td>"
                + "                    <td>\n";
        for (int i = 0; i < MatrizB.length; i++) {
            for (int j = 0; j < Inversa[i].length; j++) {

                texto2 += " [" + df.format(Inversa[i][j]) + "] ";
            }
            texto2 += "<br>";
        }
        texto2 += "                    </td>\n"
                + "                </tr>";

        return 0;
    }

    public static double[][] ActualizarM(double M[][]) {
        double[][] Q = new double[4][8];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                Q[i][j] = M[i][j];
            }
        }
        return Q;
    }

    public static void ImprimirMatriz(double Matrix[][]) {
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[i].length; j++) {
                System.out.print("[" + df.format(Matrix[i][j]) + "]");
            }
            System.out.println("");
        }
    }

    public static void GenerarReporte1() {

        System.out.println("Ingrese la ruta donde desea guardar su reporte: ");
       // System.out.println("C:\\Users\\Nathan\\Desktop");
        String rutaReporte = new Scanner(System.in).nextLine();

        //Generación del reporte en java:
        FileWriter fichero = null;
        PrintWriter pw = null;

        String texto = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
                + "        integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Document</title>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"background-color:rgb(46, 51, 64);\">\n"
                + "\n"
                + "    <h1 style=\"color:rgb(204, 36, 182);font-size:60px;text-align:center;\">Reporte 1</h1>\n"
                + "    <p style=\"color:rgb(20, 140, 195);font-size:30px;text-align:center;\">\n"
                + "        Este reporte contiene todas las operaciones que el programa realizo en la ultima partida\n"
                + "    </p>\n"
                + "\n"
                + "    <ol>\n"
                + "\n"
                + "\n"
                + "\n"
                + "        <table class=\"table table-dark table-striped\">\n"
                + "            <thead>\n"
                + "                <tr>\n"
                + "                    <th scope=\"col\">#</th>\n"
                + "                    <th scope=\"col\">Operacion realizada</th>\n"
                + "                    <th scope=\"col\">Proceso </th>\n"
                + "                    <th scope=\"col\">Resultado</th>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "            </thead>\n"
                + "            <tbody>\n"
                + "\n"
                + "\n";

        texto += texto2;

        texto += "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>\n"
                + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js\"\n"
                + "            integrity=\"sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp\"\n"
                + "            crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js\"\n"
                + "            integrity=\"sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/\"\n"
                + "            crossorigin=\"anonymous\"></script>\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        try {
            fichero = new FileWriter(rutaReporte + "\\reporte.html");
            pw = new PrintWriter(fichero);

            for (int i = 0; i <= 0; i++) {
                pw.println(texto);
            }

            System.out.println("Reporte generado.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void GenerarReporte2() {

        System.out.println("Ingrese la ruta donde desea guardar su reporte: ");
        //System.out.println("C:\\Users\\Nathan\\Desktop");
        String rutaReporte2 = new Scanner(System.in).nextLine();

        //Generación del reporte en java:
        FileWriter fichero = null;
        PrintWriter pw = null;

        String texto3 = "<!DOCTYPE html>\n"
                + "<html  String texto3 = \"<!DOCTYPE html>\\n\"lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
                + "        integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Document</title>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"background-color:rgb(46, 51, 64);\">\n"
                + "\n"
                + "    <h1 style=\"color:rgb(204, 36, 182);font-size:60px;text-align:center;\">Reporte 2</h1>\n"
                + "\n"
                + "    <p style=\"color:rgb(20, 140, 195);font-size:30px;text-align:center;\">\n"
                + "        Este reporte contiene todas las acciones que se realicen durante el programa\n"
                + "    </p>\n"
                + "    \n"
                + "    <ol>\n"
                + "\n"
                + "\n"
                + "\n"
                + "        <table class=\"table table-dark table-striped\">\n"
                + "            <thead>\n"
                + "\n"
                + "                <tr>\n"
                + "                    <th scope=\"col\">#</th>\n"
                + "                    <th scope=\"col\">Tipo de Accion</th>\n"
                + "                    <th scope=\"col\">Decripcion</th>\n"
                + "\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "\n"
                + "            </thead>";

        texto3 += texto4;

        texto3 += "            </tbody>\n"
                + "        </table>\n"
                + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js\"\n"
                + "            integrity=\"sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp\"\n"
                + "            crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js\"\n"
                + "            integrity=\"sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/\"\n"
                + "            crossorigin=\"anonymous\"></script>\n"
                + "</body>\n"
                + "</html>";

        try {
            //fichero = new FileWriter(rutaReporte2 + "\\reporte2.html");
            fichero = new FileWriter(rutaReporte2+"\\reporte2.html");
            pw = new PrintWriter(fichero);

            for (int i = 0; i <= 0; i++) {
                pw.println(texto3);
            }

            System.out.println("Reporte generado.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
