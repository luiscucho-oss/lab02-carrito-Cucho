package com.cucho.lab02carritokotlin

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0

    for (p in productos) {
        subtotal += p.precio * p.cantidad
    }

    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    return subtotal + igv
}

fun main() {

    val carrito = mutableListOf<Producto>()

    var opcion: Int

    do {

        println()
        println("===================================")
        println("       CARRITO DE COMPRAS")
        println("===================================")
        println("1. Agregar producto")
        println("2. Ver carrito")
        println("3. Buscar producto")
        println("4. Eliminar producto")
        println("5. Ver totales")
        println("6. Salir")
        print("Seleccione una opcion: ")

        opcion = readln().toIntOrNull() ?: 0

        when (opcion) {

            1 -> {
                agregarProducto(carrito)
            }

            2 -> {
                if (carrito.isEmpty()) {
                    println("El carrito esta vacio")
                } else {
                    mostrarDetalle(carrito)
                }
            }

            3 -> {
                print("Ingrese el nombre del producto a buscar: ")
                val nombre = readln()

                val encontrado = buscarProducto(carrito, nombre)

                if (encontrado != null) {
                    println("Producto encontrado:")
                    println("Nombre: ${encontrado.nombre}")
                    println(String.format("Precio: S/ %.2f", encontrado.precio))
                    println("Cantidad: ${encontrado.cantidad}")
                } else {
                    println("Producto no encontrado")
                }
            }

            4 -> {
                print("Ingrese el nombre del producto a eliminar: ")
                val nombre = readln()

                val eliminado = carrito.removeIf {
                    it.nombre == nombre
                }

                if (eliminado) {
                    println("Producto eliminado correctamente")
                } else {
                    println("Producto no encontrado")
                }
            }

            5 -> {

                if (carrito.isEmpty()) {

                    println("El carrito esta vacio")

                } else {

                    val subtotal = calcularSubtotal(carrito)
                    val igv = calcularIGV(subtotal)
                    val total = calcularTotal(subtotal, igv)
                    val descuento = calcularDescuento(total)
                    val totalFinal = total - descuento

                    println()
                    println(String.format("Subtotal       : S/ %8.2f", subtotal))
                    println(String.format("IGV (18%%)     : S/ %8.2f", igv))
                    println(String.format("Total          : S/ %8.2f", total))
                    println(String.format("Descuento      : S/ %8.2f", descuento))
                    println(String.format("TOTAL FINAL    : S/ %8.2f", totalFinal))
                }
            }

            6 -> {
                println("Programa finalizado")
            }

            else -> {
                println("Opcion no valida")
            }
        }

    } while (opcion != 6)
}

fun mostrarDetalle(productos: List<Producto>) {
    println("--------- DETALLE DEL CARRITO ---------")
    var i = 1

    for (p in productos) {
        val importe = p.precio * p.cantidad

        println(
            String.format(
                "%d. %-20s x%d S/ %8.2f",
                i,
                p.nombre,
                p.cantidad,
                importe
            )
        )

        i++
    }

    println("---------------------------------------")
}

fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
}

fun buscarProducto(productos: List<Producto>, nombre: String): Producto? {
    return productos.find { it.nombre == nombre }
}

fun agregarProducto(carrito: MutableList<Producto>) {

    print("Ingrese el nombre del producto: ")
    val nombre = readln()

    print("Ingrese el precio: ")
    val precio = readln().toDoubleOrNull()

    print("Ingrese la cantidad: ")
    val cantidad = readln().toIntOrNull()

    if (precio != null && cantidad != null && precio > 0 && cantidad > 0) {

        carrito.add(
            Producto(nombre, precio, cantidad)
        )

        println("Producto agregado correctamente")

    } else {

        println("Precio o cantidad incorrectos")
    }
}