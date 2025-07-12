package com.example.programtointerfacetest.network.protocolengine

import java.io.InputStream
import java.io.OutputStream

class ProtocolEngineForWireless: IProtocolEngine {

    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream
    private lateinit var serializer: IGamePointsSerializer

    fun initialize(streams: StreamProvider, serializer: IGamePointsSerializer){
        this.inputStream = streams.getInputStream()
        this.outputStream = streams.getOutputStream()
        this.serializer = serializer
    }

    override fun giveGamePointsToOutputStream(points: Int, type: ConnectionType) {
        this.serializer.serializeGamePoints(this.outputStream, points, type)
    }

    override fun readGamePointsFromInputStream(): Int {
        return this.serializer.deserializeGamePoints(this.inputStream)
    }

}