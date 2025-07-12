package com.example.programtointerfacetest.network.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import androidx.annotation.RequiresPermission
import com.example.programtointerfacetest.network.protocolengine.StreamProvider
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import android.content.Context
import java.io.IOException
import kotlin.contracts.contract

class BluetoothServer(private val context: Context): StreamProvider {
    private lateinit var serverSocket: BluetoothServerSocket
    private lateinit var socket: BluetoothSocket

    override fun getInputStream(): InputStream {
        return socket.inputStream
    }

    override fun getOutputStream(): OutputStream {
        return socket.outputStream
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun startServer(uuid: UUID, serviceName: String) {

        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val adapter = bluetoothManager.adapter?: throw IOException("Bluetooth not available")

        serverSocket = adapter.listenUsingRfcommWithServiceRecord(serviceName, uuid)

        // Dies blockiert den Thread! Am besten in einem Background-Thread aufrufen.
        socket = serverSocket.accept()

        // Nach erfolgreicher Verbindung ServerSocket schließen
        serverSocket.close()
    }

    fun close() {
        socket.close()
    }
}