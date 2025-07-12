package com.example.programtointerfacetest

import com.example.programtointerfacetest.network.protocolengine.ConnectionType
import com.example.programtointerfacetest.network.protocolengine.GamePointsSerializer
import com.example.programtointerfacetest.network.protocolengine.IGamePointsSerializer
import com.example.programtointerfacetest.network.protocolengine.ProtocolEngineForWireless
import com.example.programtointerfacetest.network.protocolengine.StreamProviderImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ProtocolEngineTest {

    /**
     * Data transfer from engineA to engineB and in the other direction
     * engineA sends 10 points to engineB => engineB reads 10 points successfully
     * engineB sends 30 points to engineA => engineA reads 30 points successfully
     */
    @Test
    fun goodTest() {
        val pointsPlayer1 = 10
        val pointsPlayer2 = 30

        //TODO First initialize engineA
        val outputStreamEngineA = ByteArrayOutputStream()
        val engineA = ProtocolEngineForWireless()
        val serializerEngineA:IGamePointsSerializer = GamePointsSerializer()

        engineA.initialize(
            streams = StreamProviderImpl(ByteArrayInputStream(ByteArray(0)), outputStreamEngineA),
            serializer = serializerEngineA
        )

        //TODO engineA send message to an other engineB
        engineA.giveGamePointsToOutputStream(pointsPlayer1, ConnectionType.BLUETOOTH)

        //TODO the byte data reload from the outputStream
        val receivedDataFromEngineA = outputStreamEngineA.toByteArray()

        //TODO Second engine, eningeB initialize
        val inputStreamEngineB = ByteArrayInputStream(receivedDataFromEngineA)
        val outputStreamEngineB = ByteArrayOutputStream()
        val engineB = ProtocolEngineForWireless()
        val serializerEngineB:IGamePointsSerializer = GamePointsSerializer()

        engineB.initialize(
            streams = StreamProviderImpl(inputStreamEngineB, outputStreamEngineB),
            serializer = serializerEngineB
        )

        //TODO engineB reads message from engineB
        val receivedAtEngineB = engineB.readGamePointsFromInputStream()

        //TODO Check is the value the same or not
        assertEquals(pointsPlayer1, receivedAtEngineB)

        //TODO engineB send message to engineA
        engineB.giveGamePointsToOutputStream(pointsPlayer2, ConnectionType.BLUETOOTH)

        //TODO the byte data reload from the outputStream
        val dataSendFromEngineB = outputStreamEngineB.toByteArray()

        //TODO EngineA received the data from engineB
        val inputStreamToEngineA = ByteArrayInputStream(dataSendFromEngineB)

        engineA.initialize(
            streams = StreamProviderImpl(inputStreamToEngineA, ByteArrayOutputStream()),
            serializer = serializerEngineA
        )
        //TODO EngineA reads message from engingeB
        val receivedPointsAtEngineA = engineA.readGamePointsFromInputStream()

        //TODO Check is the value the same or not
        assertEquals(pointsPlayer2, receivedPointsAtEngineA)
    }

}