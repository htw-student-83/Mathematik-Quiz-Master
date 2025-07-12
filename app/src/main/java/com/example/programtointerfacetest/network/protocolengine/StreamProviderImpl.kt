package com.example.programtointerfacetest.network.protocolengine

import java.io.InputStream
import java.io.OutputStream

class StreamProviderImpl(
    private var inputStream: InputStream,
    private var outputStream: OutputStream
): StreamProvider {
    override fun getInputStream(): InputStream {
        return this.inputStream
    }

    override fun getOutputStream(): OutputStream {
        return this.outputStream
    }
}