// Clase que representa una carta
data class Carta(val valor: String, val palo: String) {

    fun mostrar(): String {
        return "$valor$palo"
    }

    fun valoresCartas(): Int {
        return when (valor) {
            "A" -> 14
            "K" -> 13
            "Q" -> 12
            "J" -> 11
            "T" -> 10
            else -> valor.toIntOrNull() ?: 0
        }
    }
}

// Jugador tiene un nombre y cartas en la mano
data class Jugador(
    val nombre: String,
    var cartas: MutableList<Carta> = mutableListOf(),
    var tipoJugada: String? = null
) {

    // Muestra las cartas del jugador
    fun mostrarCartas() {
        print("$nombre tiene: ")
        cartas.forEach { carta ->
            print("${carta.mostrar()} ")
        }
        println()
    }
}

// Clase para gestionar el mazo de cartas
class MazoDeCartas {
    var cartas: MutableList<Carta> = mutableListOf()

    // Constructor que crea todas las cartas
    init {
        val valores = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K")
        val palos = listOf("S", "H", "D", "C")

        // Crear cada combinación de valor y palo
        for (palo in palos) {
            for (valor in valores) {
                val nuevaCarta = Carta(valor, palo)
                cartas.add(nuevaCarta)
            }
        }
    }

    // Mezcla las cartas con shuffle()
    fun mezclar() {
        cartas.shuffle()
    }

    // Reparte una carta del mazo
    fun sacarCarta(): Carta? {
        return if (cartas.isEmpty()) {
            null
        } else {
            cartas.removeAt(0)
        }
    }

    // Entrega 5 cartas para una mano
    fun darMano(): MutableList<Carta> {
        val mano = mutableListOf<Carta>()

        // Intenta dar 5 cartas
        repeat(5) {
            sacarCarta()?.let { carta ->
                mano.add(carta)
            }
        }

        return mano
    }
}

// Función para detectar si hay una escalera
fun esEscalera(cartas: List<Carta>): Boolean {
    // Obtener los valores y ordenarlos
    val valores = cartas.map { it.valoresCartas() }.toMutableList()
    valores.sort()

    // Eliminar duplicados para manejar posibles errores
    val valoresUnicos = valores.toSet().sorted()

    // Caso especial: A-2-3-4-5
    if (valoresUnicos == listOf(2, 3, 4, 5, 14)) {
        return true
    }

    // Debe haber 5 valores distintos
    if (valoresUnicos.size != 5) {
        return false
    }

    // Comprobar si cada valor es uno más que el anterior
    for (i in 0 until valoresUnicos.size - 1) {
        if (valoresUnicos[i + 1] != valoresUnicos[i] + 1) {
            return false
        }
    }

    return true
}

// Función simple para detectar si hay color (mismo palo)
fun esColor(cartas: List<Carta>): Boolean {
    val primerPalo = cartas[0].palo

    // Si alguna carta tiene palo diferente, no es color
    return cartas.all { it.palo == primerPalo }
}

// Función para contar repeticiones de cada valor
fun contarRepeticiones(cartas: List<Carta>): List<Int> {
    val conteo = mutableMapOf<Int, Int>()

    // Contar cuántas veces aparece cada valor
    for (carta in cartas) {
        val valor = carta.valoresCartas()
        conteo[valor] = conteo.getOrDefault(valor, 0) + 1
    }

    // Ordenar las repeticiones de mayor a menor
    return conteo.values.sortedDescending()
}

// Función para encontrar el tipo de jugada
fun analizarJugada(cartas: List<Carta>): String {
    val hayEscalera = esEscalera(cartas)
    val hayColor = esColor(cartas)
    val repeticiones = contarRepeticiones(cartas)

    // Verificar cada jugada posible en orden de importancia
    return when {
        hayEscalera && hayColor -> "Escalera de Color"
        repeticiones == listOf(4, 1) -> "Póker"
        repeticiones == listOf(3, 2) -> "Full House"
        hayColor -> "Color"
        hayEscalera -> "Escalera"
        repeticiones.first() == 3 -> "Trío"
        repeticiones == listOf(2, 2, 1) -> "Doble Par"
        repeticiones.first() == 2 -> "Par"
        else -> "Carta Alta"
    }
}

// Se evalúa cada jugada posible
fun valorJugada(jugada: String): Int {
    return when (jugada) {
        "Escalera de Color" -> 8
        "Póker" -> 7
        "Full House" -> 6
        "Color" -> 5
        "Escalera" -> 4
        "Trío" -> 3
        "Doble Par" -> 2
        "Par" -> 1
        else -> 0 // Carta Alta
    }
}

// Función para el desempate
fun compararCartasAltas(cartas1: List<Carta>, cartas2: List<Carta>): Int {
    val valores1 = cartas1.map { it.valoresCartas() }.sortedDescending()
    val valores2 = cartas2.map { it.valoresCartas() }.sortedDescending()

    // Comparar carta por carta
    for (i in 0 until minOf(valores1.size, valores2.size)) {
        when {
            valores1[i] > valores2[i] -> return 1
            valores2[i] > valores1[i] -> return 2
        }
    }

    return 0 // Empate verdadero
}

// Función para mostrar el valor como texto
fun mostrarValor(valor: Int): String {
    return when (valor) {
        14 -> "As"
        13 -> "Rey"
        12 -> "Reina"
        11 -> "Jota"
        10 -> "10"
        else -> "$valor"
    }
}

// Función para determinar quién gana
fun quienGana(jugador1: Jugador, jugador2: Jugador): String {
    // Asegurarse de que hay jugadas detectadas
    val jugada1 = jugador1.tipoJugada ?: return "Error: falta analizar las jugadas"
    val jugada2 = jugador2.tipoJugada ?: return "Error: falta analizar las jugadas"

    val valor1 = valorJugada(jugada1)
    val valor2 = valorJugada(jugada2)

    // Comparar los valores de las jugadas
    return when {
        valor1 > valor2 -> "${jugador1.nombre} gana con $jugada1"
        valor2 > valor1 -> "${jugador2.nombre} gana con $jugada2"
        else -> {
            // Si tienen la misma jugada, comparar todas las cartas en orden
            when (val resultado = compararCartasAltas(jugador1.cartas, jugador2.cartas)) {
                1 -> {
                    val valoresOrdenados = jugador1.cartas.map { it.valoresCartas() }.sortedDescending()
                    val cartasTexto = valoresOrdenados.joinToString(", ") { mostrarValor(it) }
                    "${jugador1.nombre} gana con $jugada1 (cartas altas: $cartasTexto)"
                }
                2 -> {
                    val valoresOrdenados = jugador2.cartas.map { it.valoresCartas() }.sortedDescending()
                    val cartasTexto = valoresOrdenados.joinToString(", ") { mostrarValor(it) }
                    "${jugador2.nombre} gana con $jugada2 (cartas altas: $cartasTexto)"
                }
                else -> "¡Empate perfecto! Ambos tienen exactamente las mismas cartas."
            }
        }
    }
}

fun jugarPoker() {
    // Crear y preparar el mazo
    val mazo = MazoDeCartas()
    println("Mazo creado con ${mazo.cartas.size} cartas")

    // Mezclar las cartas
    mazo.mezclar()
    println("Mezclando mazo...")

    // Crear jugadores
    val jugador1 = Jugador("ChatGpt")
    val jugador2 = Jugador("Claude")

    // Repartir cartas
    println("\nRepartiendo cartas...")
    jugador1.cartas = mazo.darMano()
    jugador2.cartas = mazo.darMano()

    // Mostrar las cartas de cada jugador
    jugador1.mostrarCartas()
    jugador2.mostrarCartas()

    // Analizar las jugadas
    println("\nAnalizando jugadas...")
    jugador1.tipoJugada = analizarJugada(jugador1.cartas)
    jugador2.tipoJugada = analizarJugada(jugador2.cartas)

    println("${jugador1.nombre} tiene: ${jugador1.tipoJugada}")
    println("${jugador2.nombre} tiene: ${jugador2.tipoJugada}")

    // Determinar el ganador
    println("\n_________RESULTADO_________")
    println(quienGana(jugador1, jugador2))
    println("___________________________")
}

// Función principal
fun main() {
    jugarPoker()
}