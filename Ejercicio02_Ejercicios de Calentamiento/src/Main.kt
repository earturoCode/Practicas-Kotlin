
import kotlin.random.Random

fun main() {
    array()
    arrayGrande()

}


fun array() {
    // Crear un array de 10 elementos con números aleatorios entre -5 y 5
    val digitos = IntArray(10) { Random.nextInt(-5, 6)}
    // Imprimir el array
    println("El array de numeroe es: ")
    println(digitos.joinToString(","))
    // Calcular el mayor elemento
    val mayor = digitos.maxOrNull()
    // Mostrar el mayor elemento
    println("El mayor número del array es: $mayor")
}


fun arrayGrande(){
    val numeros = IntArray(100) { Random.nextInt(-30, 31) }

    // Imprimir el array
    println("El array de números es:")
    println(numeros.joinToString(", "))

    // Contar la frecuencia de cada número
    val frecuencia = numeros.groupBy { it }.mapValues { it.value.size }

    // Buscar el número más repetido
    val repetido = frecuencia.maxByOrNull { it.value }
    println("El número con más frecuencia es: ${repetido?.key} (repetido ${repetido?.value} veces)")

    // Buscar los números del -30 al 30 que no están en el array
    val faltantes = (-30..30).filterNot { it in frecuencia }

    if (faltantes.isEmpty()) {
        println("Todos los números del -30 al 30 están presentes.")
    } else {
        println("Números faltantes: ${faltantes.joinToString(", ")}")
    }
}

