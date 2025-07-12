package com.example.model.mathematics

internal interface IProductOfMultiplication {

    /**
     * get the product of the currently displayed exercise.
     *
     * @return current product
     */
    fun getProduct(): Int

    /**
     * update the product of multiplicaton always after a generating of exercise
     *
     * @param newProdct
     */
    fun setProduct(newProdct: Int)
}