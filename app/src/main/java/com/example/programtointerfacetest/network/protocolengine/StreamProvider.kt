package com.example.programtointerfacetest.network.protocolengine

import java.io.InputStream
import java.io.OutputStream

interface StreamProvider {

    fun getInputStream(): InputStream

    fun getOutputStream(): OutputStream
}