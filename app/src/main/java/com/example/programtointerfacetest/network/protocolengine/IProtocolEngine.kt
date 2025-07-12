package com.example.programtointerfacetest.network.protocolengine

interface IProtocolEngine {

    /**
     * read points from an input stream
     *
     * @return points
     */
    fun readGamePointsFromInputStream(): Int


    /**
     * send points by output stream
     *
     * @param points
     * @param type bluetooth or wifidirect
     */
    fun giveGamePointsToOutputStream(points: Int, type: ConnectionType)

}