package com.example.programtointerfacetest.network.protocolengine

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

class GamePointsSerializer: IGamePointsSerializer {

    override fun serializeGamePoints(outputStream: OutputStream, points: Int, type: ConnectionType) {
        val dos = DataOutputStream(outputStream)
        dos.writeInt(points)
    }

    override fun deserializeGamePoints(inputStream: InputStream): Int {
        val dis = DataInputStream(inputStream)
        return dis.readInt()
    }

}