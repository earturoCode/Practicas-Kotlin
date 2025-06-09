fun numeroSecreto():String {
    val digitos = (0..9).toMutableList()
    digitos.shuffle()  // Mezcla los dígitos aleatoriamente

    // Si el primer dígito es 0, lo intercambia con el primero que no sea 0 (para evitar números que comiencen con 0)
    if (digitos[0] == 0) {
        val i = digitos.indexOfFirst { it == 0 }    // ← esto debería buscar un número distinto de 0
        digitos[0] = digitos[i].also { digitos[i] = 0 }
    }
    return digitos.take(4).joinToString ( "" )  // Toma los primeros 4 dígitos y los convierte en un string
}

// Valida que la entrada del usuario tenga 4 dígitos únicos, todos sean números y no empiece con 0
fun validarEntrada(entrada: String ): Boolean {
    return entrada.length == 4 &&   // Debe tener exactamente 4 caracteres
            entrada.all { it.isDigit() } && // Todos deben ser dígitos
            entrada.toSet().size == 4 &&    // Todos deben ser únicos (sin repetir)
            !entrada.startsWith("0")    // No debe comenzar con 0
}

// Calcula cuántos toros y vacas hay entre el intento y el número secreto
fun calcularVyT (secreto: String, intento: String): Pair<Int, Int> {
    var toros = 0   // Coincide el número y la posición
    var vacas = 0   // Coincide el número pero no la posición

    for (i in 0..3) {
        if (intento[i] == secreto[i]) {
            toros ++    // Mismo número y misma posición
        }else if (intento[i] in secreto) {
            vacas ++    // Mismo número pero en diferente posición
        }

    }
    return Pair(vacas, toros)   // Devuelve un par (vacas, toros)
}

fun main() {
    val secreto = numeroSecreto()
    println("Bienvenidos al juego de vacas y toros")
    println("El numero de vacas y toros es: $secreto")

    while (true) {
        println("Ingrese 4 numeros aleatorios unicos")
        val entrada = readLine() ?:continue     // Si no se ingresa nada, vuelve a pedir

        if (!validarEntrada(entrada)) {
            println("Entrada invalida, Asegurate de que sea 4 digitos unicos y no tiene que comenzar con 0")
            continue    // Si la entrada no es válida, vuelve a pedirla
        }
        val (vacas, toros) = calcularVyT(secreto, entrada)
        println("$vacas vacas, $toros toros ")
        if (toros == 4) {
            println("Felicidades! El numero secreto es: $secreto")
            break
        }

    }
}