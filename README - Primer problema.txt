README - Primer problema
Introducción
La función Desplazar trabaja con un array de caracteres dividiéndolo en dos mitades y rotando cada mitad de forma independiente según el desplazamiento indicado.

Cálculo Inicial
Al inicio se calcula "int mitad = tamanyo / 2" para dividir el tamaño total del array entre dos. Por ejemplo, si el array tiene 8 caracteres, cada mitad tendrá 4 caracteres.

División del Desplazamiento
Las líneas "int primera = (desplazo + 1) / 2" y "int segunda = desplazo / 2" dividen el desplazamiento total entre las dos mitades. Si el desplazamiento es impar, la primera mitad recibe una posición más de rotación que la segunda.

El Módulo en la Rotación
En "buffer[(i + primera) % mitad] = s[i]" el operador módulo garantiza que cuando la suma supere el tamaño de la mitad, el índice vuelva a empezar desde cero. Por ejemplo, (3 + 2) % 4 = 1, creando así el efecto de rotación circular.

El Buffer
El buffer "char[] buffer = new char[mitad]" es un array temporal del tamaño de la mitad que sirve como espacio de almacenamiento para guardar los caracteres en sus nuevas posiciones rotadas, sin perder información del array original.

Proceso de Rotación de las Mitades
El proceso se realiza dos veces: primero con "if (primera > 0)" para la primera mitad y luego con "if (segunda > 0)" para la segunda mitad. Ambos bloques funcionan idénticamente. El bucle "for (int bloque = 1; bloque <= mitad; bloque *= 2)" no afecta el resultado, parece ser código residual.

El bucle "for (int i = 0; i < mitad; i++)" recorre cada elemento y lo coloca en el buffer en su nueva posición. Para la primera mitad usa "buffer[(i + primera) % mitad] = s[i]" y para la segunda mitad "buffer[(i + segunda) % mitad] = s[mitad + i]". Luego, otro bucle copia los caracteres del buffer de vuelta al array original con "s[i] = buffer[i]" para la primera mitad y "s[mitad + i] = buffer[i]" para la segunda.

Retorno
La función termina con "return s", devolviendo el array modificado con ambas mitades rotadas.

Complejidad y Rendimiento
El programa calcula y muestra una aproximación de "n log n" como referencia teórica. Por ejemplo, con 8 elementos y desplazamiento de 3, el tiempo de ejecución puede ser alrededor de 9100 nanosegundos, lo cual se aproxima al comportamiento esperado de un algoritmo con complejidad n log n.

