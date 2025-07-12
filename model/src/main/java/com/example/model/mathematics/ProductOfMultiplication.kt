package com.example.model.mathematics

internal class ProductOfMultiplication (
    private var product: Int
): IProductOfMultiplication {

    override fun getProduct(): Int {
        return this.product
    }

    override fun setProduct(newProdct: Int) {
        this.product = newProdct
    }

}