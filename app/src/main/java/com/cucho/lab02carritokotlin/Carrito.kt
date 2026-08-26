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
    println(" CARRITO DE COMPRAS - TIENDA TECSUP ")

    val nombreCliente = "Luis Cucho"
    val carrito = mutableListOf<Producto>()

    println("Cliente: $nombreCliente")
    println()

    carrito.add(Producto("Laptop HP", 2500.0, 1))
    carrito.add(Producto("Mouse Logitech", 45.5, 2))
    carrito.add(Producto("MousPad Corsair", 80.0, 1))
    carrito.add(Producto("Teclado Mecanico Antrix", 180.0, 1))

    for (producto in carrito) {
        println("Producto agregado: ${producto.nombre}")
    }

    val subtotal = calcularSubtotal(carrito)
    val igv = calcularIGV(subtotal)
    val total = calcularTotal(subtotal, igv)
    val descuento = calcularDescuento(total)
    val totalConDescuento = total - descuento
    val productoBuscado = buscarProducto(carrito, "Mouse Logitech")

    println()

    mostrarDetalle(carrito)

    println("Cantidad de productos: ${carrito.size}")
    println()

    println()
    println(String.format("Subtotal : S/ %.2f", subtotal))
    println(String.format("IGV (18%%): S/ %.2f", igv))
    println(String.format("TOTAL    : S/ %.2f", total))

    val masCaro = carrito.maxByOrNull { it.precio }

    if (masCaro != null) {
        println()
        println(
            "Producto mas caro: ${masCaro.nombre} " +
                    String.format("(S/ %.2f)", masCaro.precio)
        )
    }

    println(String.format("Descuento      : S/ %8.2f", descuento))
    println(String.format("TOTAL FINAL    : S/ %8.2f", totalConDescuento))

    println()
    println("--------- BUSCAR PRODUCTO ---------")

    if (productoBuscado != null) {

        println(
            "Producto encontrado: ${productoBuscado.nombre}"
        )

    } else {

        println(
            "Producto no encontrado"
        )
    }

    println()
    println("--------- ELIMINAR PRODUCTO ---------")

    carrito.removeIf {
        it.nombre == "Mouse Logitech"
    }

    println(
        "Se elimino: Mouse Logitech"
    )

    println()
    println(
        "----- CARRITO DESPUES DE ELIMINAR -----"
    )

    mostrarDetalle(carrito)
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
