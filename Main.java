import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*
    Funcion: Desplazar
    Objetivo: Desplazar los elementos de un arreglo partido entre dos, 1 desplazamiento intercalado entre las mitades
    Argumentos: int (numero de desplazamientos), Cadena como arreglo de char's, int el tamanyo
    Retorna: Cadena char original pero ya desplazada
    */
    public static char[] Desplazar(int desplazo, char[] s, int tamanyo) {
        int mitad = tamanyo / 2;

        int primera = (desplazo + 1) / 2;
        int segunda = desplazo / 2;

        // Rotar primera mitad
        if (primera > 0 && mitad > 0) {
            if (primera != 0) {
                int ciclos = gcd(mitad, primera); // Calcula cuántos ciclos independientes hay en la rotación

                // Itera sobre cada ciclo independiente
                for (int inicio = 0; inicio < ciclos; inicio++) {
                    char temp = s[inicio]; // Guarda el primer elemento del ciclo
                    int actual = inicio;

                    // Sigue el ciclo moviendo elementos a su posición final
                    while (true) {
                        int siguiente = (actual + primera) % mitad; // Calcula dónde debe ir el elemento actual
                        if (siguiente == inicio) break; // Si volvemos al inicio, el ciclo está completo

                        s[actual] = s[siguiente]; // Mueve el elemento siguiente a la posición actual
                        actual = siguiente; // Avanza al siguiente elemento del ciclo
                    }
                    s[actual] = temp; // Coloca el elemento guardado en su posición final
                }
            }
        }

        // Rotar segunda mitad (mismo proceso pero desplazado por 'mitad' posiciones)
        if (segunda > 0 && mitad > 0) {
            if (segunda != 0) {
                int ciclos = gcd(mitad, segunda);

                for (int inicio = 0; inicio < ciclos; inicio++) {
                    char temp = s[mitad + inicio]; // Trabaja con la segunda mitad del arreglo
                    int actual = inicio;

                    while (true) {
                        int siguiente = (actual + segunda) % mitad;
                        if (siguiente == inicio) break;

                        s[mitad + actual] = s[mitad + siguiente]; // Los índices tienen offset de 'mitad'
                        actual = siguiente;
                    }
                    s[mitad + actual] = temp;
                }
            }
        }

        return s;
    }

    /*
    Funcion auxiliar: gcd (Greatest Common Divisor)
    Calcula el máximo común divisor usando el algoritmo de Euclides
    */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /*
    Funcion: generarEstudiantes
    Objetivo: Generar una lista de estudiantes con datos aleatorios
    Argumentos: int (cantidad de estudiantes a generar)
    Retorna: Lista de estudiantes con matricula, nombre e indice academico aleatorios
    */
    public static List<Estudiante> generarEstudiantes(int n) {
        List<Estudiante> estudiantes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Estudiante student = new Estudiante(
                    (int) (Math.random() * 100000000) + i,
                    "Estudiante #" + (i + 1),
                    (float) (Math.random() * 4)
            );
            estudiantes.add(student);
        }
        return estudiantes;
    }

    /*
    Funcion: ordenarMergeSort
    Objetivo: Ordenar una lista de estudiantes usando el algoritmo Merge Sort
    Argumentos: Lista de estudiantes sin ordenar, boolean (true para ascendente, false para descendente)
    Retorna: Lista de estudiantes ordenada por indice academico
    */
    public static List<Estudiante> ordenarMergeSort(List<Estudiante> listaSinOrdenar, boolean ascendente) {
        ArrayList<Estudiante> copia = new ArrayList<>(listaSinOrdenar);
        mergeSort(copia, 0, copia.size() - 1, ascendente);
        return copia;
    }

    /*
    Funcion: mergeSort
    Objetivo: Dividir recursivamente la lista en mitades hasta obtener sublistas de un elemento
    Argumentos: ArrayList de estudiantes, int inicio, int fin, boolean orden
    Retorna: Nada (modifica la lista original)
    */
    private static void mergeSort(ArrayList<Estudiante> lista, int inicio, int fin, boolean ascendente) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            mergeSort(lista, inicio, medio, ascendente);
            mergeSort(lista, medio + 1, fin, ascendente);
            merge(lista, inicio, medio, fin, ascendente);
        }
    }

    /*
    Funcion: merge
    Objetivo: Combinar dos sublistas ordenadas en una sola lista ordenada
    Argumentos: ArrayList de estudiantes, int inicio, int medio, int fin, boolean orden
    Retorna: Nada (modifica la lista original intercalando elementos de manera ordenada)
    */
    private static void merge(ArrayList<Estudiante> lista, int inicio, int medio, int fin, boolean ascendente) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        Estudiante[] izquierda = new Estudiante[n1];
        Estudiante[] derecha = new Estudiante[n2];

        for (int i = 0; i < n1; i++) {
            izquierda[i] = lista.get(inicio + i);
        }
        for (int j = 0; j < n2; j++) {
            derecha[j] = lista.get(medio + 1 + j);
        }

        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            if ((ascendente && izquierda[i].indiceAcademico <= derecha[j].indiceAcademico) ||
                    (!ascendente && izquierda[i].indiceAcademico >= derecha[j].indiceAcademico)) {
                lista.set(k, izquierda[i]);
                i++;
            } else {
                lista.set(k, derecha[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            lista.set(k, izquierda[i]);
            i++;
            k++;
        }
        while (j < n2) {
            lista.set(k, derecha[j]);
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== PROGRAMA DE DESPLAZAMIENTO Y ORDENAMIENTO ===\n");

        System.out.print("Ingresa la cadena: ");
        String cadena = sc.nextLine();

        System.out.print("Ingresa el desplazamiento: ");
        int t = sc.nextInt();

        char[] cad = cadena.toCharArray();
        int tamanyo = cad.length;

        if (tamanyo % 2 == 0 && (t > 0 && t <= tamanyo - 1)) {
            long start = System.nanoTime();
            cad = Desplazar(t, cad, tamanyo);
            long end = System.nanoTime();

            System.out.print("\nCadena desplazada: ");
            for (int i = 0; i < cad.length; i++) {
                System.out.print(cad[i]);
            }

            double nlogn = tamanyo * (Math.log(tamanyo) / Math.log(2));
            System.out.println("\nTiempo real: " + (end - start) + " ns");
            System.out.println("n log n aprox: " + nlogn);
        } else {
            System.out.println("ERROR: para fines del ejercicio se espera un desplazamiento menor a la cantidad de elementos y una cantidad par de elementos");
        }

        System.out.println("\n\n=== ORDENAMIENTO DE ESTUDIANTES ===\n");

        List<Estudiante> estudiantes = generarEstudiantes(10);

        System.out.println("Antes:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }

        ArrayList<Estudiante> ordenados = (ArrayList<Estudiante>) ordenarMergeSort(estudiantes, false);

        System.out.println("\nDespues:");
        for (Estudiante estudiante : ordenados) {
            System.out.println(estudiante);
        }

        sc.close();
    }

    /*
    Clase: Estudiante
    Objetivo: Representar un estudiante con matricula, nombre e indice academico
    */
    public static class Estudiante {
        int matricula;
        String nombre;
        double indiceAcademico;

        public Estudiante(int matricula, String nombre, double indiceAcademico) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.indiceAcademico = indiceAcademico;
        }

        @Override
        public String toString() {
            return matricula + " - " + nombre + " - " + indiceAcademico;
        }
    }
}