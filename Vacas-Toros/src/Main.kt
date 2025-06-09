fun numeroSecreto():String {
    val digitos = (0..9).toMutableList()
    digitos.shuffle()

    if (digitos[0] == 0) {
        val i = digitos.indexOfFirst { it == 0 }
        digitos[0] = digitos[i].also { digitos[i] = 0 }
    }

    return digitos.take(4).joinToString ( "" )
}

fun validarEntrada(entrada: String ): Boolean {
    return entrada.length == 4 && entrada.all { it.isDigit() } && entrada.toSet().size == 4 && !entrada.startsWith("0")
}

fun calcularVyT (secreto: String, intento: String): Pair<Int, Int> {
    var toros = 0
    var vacas = 0

    for (i in 0..3) {
        if (intento[i] == secreto[i]) {
            toros ++
        }else if (intento[i] in secreto) {
            vacas ++
        }

    }
    return Pair(vacas, toros)
}

fun main() {
    val secreto = numeroSecreto()
    println("Bienvenidos al juego de vacas y toros")
    println("El numero de vacas y toros es: $secreto")

    while (true) {
        println("Ingrese 4 numeros aleatorios unicos")
        val entrada = readLine() ?:continue

        if (!validarEntrada(entrada)) {
            println("Entrada invalida, Asegurate de que sea 4 digitos unicos y no tiene que comenzar con 0")
            continue
        }
        val (vacas, toros) = calcularVyT(secreto, entrada)
        println("$vacas vacas, $toros toros ")
        if (toros == 4) {
            println("Felicidades! El numero secreto es: $secreto")
            break
        }

    }
}