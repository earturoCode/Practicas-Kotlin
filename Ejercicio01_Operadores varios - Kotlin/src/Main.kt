fun main() {
    print("Ingrese un numero: ")
    var numero1 = readln()!!.toInt()
    print("Ingrese otro numero: ")
    var numero2 = readLine()!!.toInt()
    print("Ingrese su nombre:")
    var nombre = readLine()!!
    print("Ingrese un precio de un producto: ")
    var precio = readln()!!.toDouble()

    operaciones(numero1, numero2)
    jerarquia(numero1, numero2)
    welcome(nombre)
    divisible(numero1)
    calcularIva(precio)
    divisibles2a3()
    bucle()
    login(contrasena = "Hola123")
    daywekk()
}

fun operaciones(numero1: Int, numero2: Int) {
    println("La suma de ambos numeros es: ${numero1 + numero2}")
    println("La resta de ambos numeros es: ${numero1 - numero2}")
    println("La multiplicacion de ambos numeros es: ${numero1 * numero2}")
    println("La division de ambos numeros es: ${numero1 / numero2}")
    println("La modulo de ambos numeros es: ${numero1 % numero2}")
}

fun jerarquia (numero1: Int, numero2: Int) {
    if (numero1 > numero2) {
        println("El numero $numero1 es mayor que $numero2")
    }else if (numero2 > numero1) {
        println("El numero $numero2 es mayor que $numero1")
    }else if (numero1 == numero2) {
        println("El numero $numero1 es igual que $numero2")

    }
}
fun welcome(nombre: String) {
    println("Bienvenido : $nombre")
}

fun divisible (numero1: Int) {
    if (numero1 % 2 == 0) {
        println("El numero $numero1 es divisor de 2")
    }else{
        println("El numero $numero1 no es divisor de 2")
    }
}
fun calcularIva(precio:Double) {
    val iva: Double = 0.1
    val precioTotal = (precio * iva) + precio
    println("El precio final con iva es de : $precioTotal")
}
fun divisibles2a3 (){
    for (i in 1..100) {
        if (i % 2 == 0) {
            println("El numero $i es divisor de 2")
        }else if (i % 3 == 0) {
            println("El numero $i es divisor de 3")

        }
    }
}

fun bucle (){
    do{
        println("Ingrese un numero a validar")
        val adivinarnro = readln()!!.toDouble()
        if (adivinarnro >= 0) {
            println("El numero $adivinarnro es mayor a 0")
        }else {
            println("Vuelva a ingresar un numero mayor o igual a 0")
        }
    }while (adivinarnro < 0)
}

fun login (contrasena: String) {
    val contrasena = "Hola123"
    var intentos = 3

    while (intentos > 0) {
        print("Ingresa la costrasena: ")
        val pass = readln()

        if (pass == contrasena) {
            println("Correcto! Adivinaste la contrasena: $contrasena ")
            println("LO lograste con $intentos intentos")
            break
        }else{
            intentos--
            if (intentos == 0) {
                println("Fallaste jaja!!")
            }else{
                println("Incorrecto! Contrasena invalida!, te quedan $intentos")
            }
        }

    }
}

fun daywekk () {
    println("Ingrese un dia a la semana: ")
    val week = readln()!!
    when (week) {
        "Lunes","Martes","Miercoles","Jueves","Viernes" -> println("Es un dia laboral")
        "Sabado","Domingo" -> println("No es un dia laboral")
    }
}



