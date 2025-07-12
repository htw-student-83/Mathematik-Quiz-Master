package com.example.programtointerfacetest.network.wifidirect

interface WirelessConnectionFacade {
    /**
     * create a new connection above bluetooth
     */
    fun wirelessContactWithBluetooth()

    /**
     * create a new connection above wifi direct
     */
    fun wirelessContactWithWifiDirect()

}