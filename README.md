# Lab 02 - Carrito de Compras en Kotlin

## Nombre

**Luis Alfredo Cucho Bendezu**

## Descripción

Este proyecto implementa la lógica de un carrito de compras utilizando Kotlin. El programa permite registrar productos con nombre, precio y cantidad, calcular el subtotal, el IGV, el total a pagar, identificar el producto más caro y aplicar un descuento dependiendo del monto total de la compra.

## Funciones implementadas

* `calcularSubtotal()`: calcula la suma del precio por cantidad de todos los productos.
* `calcularIGV()`: calcula el 18% del subtotal.
* `calcularTotal()`: suma el subtotal y el IGV.
* `mostrarDetalle()`: muestra los productos del carrito con sus cantidades e importes.
* `calcularDescuento()`: aplica un descuento dependiendo del total de la compra.

También se utilizó `maxByOrNull` para identificar el producto con mayor precio.

## Resultado final

<img width="1912" height="1020" alt="image" src="https://github.com/user-attachments/assets/9fe6989a-dc89-4393-9914-b4d6b414a587" />


## ¿Por qué nombre y precio son `val` pero cantidad es `var`?

`nombre` y `precio` son `val` porque son valores que no deberían cambiar después de crear un producto. En cambio, `cantidad` es `var` porque puede modificarse dependiendo de la cantidad de unidades que se agreguen o retiren del carrito.

Si se intentara modificar el precio después de crear el producto, Kotlin mostraría un error porque `precio` fue declarado como `val` y no puede ser reasignado.
