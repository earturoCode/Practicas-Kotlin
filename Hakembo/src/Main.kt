
fun main() {
    println("Mba'eteko mi kopi, Jahuga Hakembo!!")
    println("Ha-Ke-Mbo...")
    println("Elegí una opción: Piedra, Papel o Tijera")
    val mano = readLine()!!

    if (mano !in arrayOf("Piedra", "Papel", "Tijera")) {
        println("Opción no válida.")
        return
    }
    posibilidades(mano)
}

fun posibilidades(jugador: String) {
    val mano = arrayOf("Piedra", "Papel", "Tijera")
    val claude = mano.random()
    println("Claude: $claude")

    when (jugador){
        "Piedra" -> {
            when (claude) {
                "Piedra" -> println("Empate!")
                "Papel" -> println("Perdiste!")
                "Tijera" -> println("Ganaste!")
            }
        }
        "Papel" -> {
            when (claude) {
                "Piedra" -> println("Ganaste!")
                "Papel" -> println("Empate!")
                "Tijera" -> println("Perdiste!")
            }
        }
        "Tijera" -> {
            when (claude) {
                "Piedra" -> println("Perdiste!")
                "Papel" -> println("Ganaste!")
                "Tijera" -> println("Emapate!")
            }
        }
    }
}