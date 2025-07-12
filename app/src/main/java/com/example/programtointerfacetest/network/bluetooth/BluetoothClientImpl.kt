package com.example.programtointerfacetest.network.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import androidx.annotation.RequiresPermission
import com.example.programtointerfacetest.network.protocolengine.StreamProvider
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class BluetoothClientImpl(
    private val deviceName: String,
    private val context: Context)
    : StreamProvider {

    private lateinit var socket: BluetoothSocket

    override fun getInputStream(): InputStream {
        return socket.inputStream
    }

    override fun getOutputStream(): OutputStream {
        return socket.outputStream
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun connect() {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val adapter = bluetoothManager.adapter?: throw IOException("Bluetooth not available")
        val device = adapter.bondedDevices.find { it.name == deviceName }
            ?: throw IOException("Device not found")

        val uuid = device.uuids.first().uuid
        socket = device.createRfcommSocketToServiceRecord(uuid)
        socket.connect()
    }

    fun close() {
        socket.close()
    }
}


