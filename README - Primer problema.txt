README - Primer problema

Introducción:
La función Desplazar trabaja con un array de caracteres dividiéndolo en dos mitades y rotando cada mitad de forma independiente según el desplazamiento indicado ( y siempre desde el ultimo elemento 1 posicion a la derecho) intercalandose, sin utilizar arreglos temporales.

Cálculo Inicial:
Al inicio se calcula int mitad = tamanyo / 2 para dividir el tamaño total del array entre dos. Por ejemplo, si el array tiene 8 caracteres, cada mitad tendrá 4 caracteres.

División del Desplazamiento:
Las líneas int primera = (desplazo + 1) / 2 y int segunda = desplazo / 2 dividen el desplazamiento total entre las dos mitades. Si el desplazamiento es impar, la primera mitad recibe una posición más de rotación que la segunda.

Concepto de Ciclos:
La línea int ciclos = gcd(mitad, primera) calcula cuántos ciclos independientes existen en la rotación usando el máximo común divisor. Por ejemplo, al rotar 6 elementos 2 posiciones, hay 2 ciclos: (0→2→4→0) y (1→3→5→1). Cada ciclo se procesa de forma independiente.

La Función GCD:
La función gcd(int a, int b) implementa el algoritmo de Euclides para calcular el máximo común divisor. El bucle while (b != 0) continúa hasta que b sea cero, intercambiando valores con b = a % b. Este valor determina exactamente cuántos ciclos hay que procesar.

Proceso de Rotación por Ciclos:
El proceso se realiza dos veces: primero con if (primera > 0) para la primera mitad y luego con if (segunda > 0) para la segunda mitad.
Para cada ciclo, el bucle for (int inicio = 0; inicio < ciclos; inicio++) itera sobre cada punto de inicio. La variable char temp = s[inicio] guarda el primer elemento del ciclo actual.

Seguimiento del Ciclo:
Dentro del while (true), la línea int siguiente = (actual + primera) % mitad calcula dónde debe ir el elemento actual usando aritmética modular para crear el efecto circular. La condición if (siguiente == inicio) break detecta cuando completamos un ciclo completo y volvimos al punto de partida.

Movimiento de Elementos:
La línea s[actual] = s[siguiente] mueve el elemento de la posición siguiente a la posición actual, siguiendo la cadena del ciclo. Luego actual = siguiente avanza al siguiente elemento. Al finalizar, s[actual] = temp coloca el elemento guardado originalmente en su posición final.

\Procesamiento de la Segunda Mitad:
Para la segunda mitad, el proceso es idéntico pero todos los índices se desplazan: s[mitad + inicio], s[mitad + actual] y s[mitad + siguiente]. Esto permite trabajar en la segunda parte del arreglo sin afectar la primera.

Retorno:
La función termina con return s, devolviendo el array modificado con ambas mitades rotadas.

Complejidad y Rendimiento:
El algoritmo no requiere arreglos temporales, solo una variable simple para guardar el primer elemento de cada ciclo. El programa calcula y muestra una aproximación de "n log n" como referencia teórica. Por ejemplo, con 8 elementos y desplazamiento de 3, el tiempo de ejecución puede ser alrededor de 9100 nanosegundos, lo cual se aproxima al comportamiento esperado de un algoritmo con complejidad n log n.
