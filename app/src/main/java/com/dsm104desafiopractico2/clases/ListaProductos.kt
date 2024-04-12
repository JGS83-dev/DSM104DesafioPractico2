package com.dsm104desafiopractico2.clases;

public class ListaProductos {
    var nombre:String? =null
    var id:String? =null
    var imagen:String? = null
    var precio:Double? = 0.0
    var tipo:String? = null

    constructor(){}

    constructor(nombre:String?,precio:Double?){
        this.nombre = nombre
        this.precio = precio
    }

    constructor(id:String?,nombre:String?,precio:Double?){
        this.id = id
        this.nombre = nombre
        this.precio = precio
    }

    constructor(id:String?,nombre:String?,precio:Double?,tipo:String?){
        this.id = id
        this.nombre = nombre
        this.precio = precio
        this.tipo = tipo
    }

}
