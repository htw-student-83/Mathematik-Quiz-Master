package com.example.programtointerfacetest.network.protocolengine

import java.io.InputStream
import java.io.OutputStream

interface IGamePointsSerializer {

    fun serializeGamePoints(outputStream: OutputStream, points: Int, type: ConnectionType)

    fun deserializeGamePoints(inputStream: InputStream): Int

}