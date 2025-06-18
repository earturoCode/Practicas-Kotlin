import  kotlin.random.Random

fun main() {
    println("Eliga la forma que quiera jughar;")
    println("1. Manera Aleatoria")
    println("2. Manera Manual")
    val opcion = readLine()!!.toInt()

    when (opcion) {
        1 -> jugarAleatorio()
        2 -> jugarManual ()
        else -> {
            println("Opcion invalido, vuelva a intentarlo")
            return main()
        }

    }

}

fun jugarManual() {
    println("Vas a jugar de manera manual")
    println("Ingrese 5 numeros del 1 al 6 (Con espacios)")

    val manual = readLine()!!
    val dados = manual.split(" ").mapNotNull { it.toIntOrNull() }

    if ( dados.size != 5 || dados.any { it !in 1..6 }) {
        println("El numero debe contenere exactamente 5 numeros, entre 1 y 6")
        return jugarManual()
    }
    mostrarResultado(dados)
}

fun jugarAleatorio() {
    println("Vas a jugar de manera aleatoria")
    val dados = List(5) { Random.nextInt(1, 7) }
    //println("Los dados obtenidos son: ${dados.joinToString(" ")}")

    mostrarResultado(dados)
}

fun mostrarResultado(dados: List<Int>) {
    println("Dados: ${dados.joinToString(" ")}")
    println("Dados ordenados : ${dados.sorted().joinToString(" ")}")

    val jugadas = identificarJugada(dados)

    println("La jugada es: ")
    if (jugadas.isEmpty()) {
        println("No forma ninguna combinacion")
    }else {
        jugadas.forEach { println(it) }
    }

}

fun identificarJugada(dados: List<Int>): List<String> {
    val jugadas = mutableListOf<String>()

    val conteo = mutableMapOf<Int, Int>()
    for (dado in dados) {
        conteo[dado] = (conteo[dado] ?: 0) + 1
    }
    val dadosOrdenados = dados.sorted()

    // GENERALA
    if (conteo.size == 1){
        jugadas.add("Generala - Todos los numeros son del mismo valor")
        return jugadas
    }

    //Poker
    val valorPoker = conteo.entries.find {it.value == 4 }?.key
    if (valorPoker != null) {
        jugadas.add("Poker")
    }

    //FULL
    val valores = conteo.values.sorted()
    if (valores == listOf(2,3)){
        val trio = conteo.entries.find {it.value == 3}?.key
        val par = conteo.entries.find {it.value == 2}?.key
        jugadas.add("FULL")
    }

    //Escalera
    val escaleras = listOf(
        listOf(1,2,3,4,5),
        listOf(2,3,4,5,6),
        listOf(1,3,4,5,6), // Escalera especial
    )

    if (escaleras.any {it == dadosOrdenados}) {
        jugadas.add("Escaleras")
    }
    return jugadas
}