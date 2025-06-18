fun nextBig(numero: Int): Int? {

    // Se convierte el número a array de dígitos
    var numeroIngresado = numero.toString().mapNotNull { it.digitToIntOrNull() }.toMutableList()

    // Si el número tiene un solo dígito, no hay otro número posible y retorna null
    if (numeroIngresado.size <= 1) {
        return null
    }

    // Para obtener el mayor número posible, simplemente ordenamos los dígitos de mayor a menor
    numeroIngresado.sortByDescending { it } // Ordena de mayor a menor

    // Convertir el array de dígitos de vuelta a un número
    val resultado = numeroIngresado.fold(0) { acc, digit -> acc * 10 + digit }

    // Si el resultado es igual al número original, no hay un número mayor y devuelve null
    if (resultado == numero) {
        return null
    }

    return resultado
}

fun main() {
    // Mensaje para que el usuario ingrese el número
    println("Ingrese un número entero positivo:")

    // Entrada de datos
    val input = readLine()
    val numero = input?.toIntOrNull()

    if (numero != null) {
        println("El número actual es: $numero")

        // Validación de dato ingresado
        val mayorPosibleNumero = nextBig(numero)

        if (mayorPosibleNumero != null) {
            println("El número mayor posible es: $mayorPosibleNumero")
        } else {
            println("No existe un número mayor con los mismos dígitos.")
        }
    } else {
        println("Por favor, ingrese un número válido.")
        return main()
    }
}


