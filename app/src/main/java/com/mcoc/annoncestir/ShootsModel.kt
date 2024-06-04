package com.mcoc.annoncestir

class ShootsModel {
    public var count:Int = 0

    var shootCount = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

    fun AddShoot(position:Int) {
        shootCount[position]++
    }

    fun Ini() {
        shootCount = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    }

}