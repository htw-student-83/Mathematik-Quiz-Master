package com.example.programtointerfacetest.network.wifidirect

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkInfo
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.annotation.RequiresPermission

class WifiDirectManager(context: Context) : WifiService{

    private val manager: WifiP2pManager = context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    private val channel: WifiP2pManager.Channel = manager.initialize(context, context.mainLooper, null)
    private lateinit var receiver: BroadcastReceiver

    private val intentFilter = IntentFilter().apply {
        addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }

    fun registerReceiver(activity: Activity) {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
                        val networkInfo = intent.getParcelableExtra<NetworkInfo>(WifiP2pManager.EXTRA_NETWORK_INFO)
                        if (networkInfo?.isConnected == true) {
                            manager.requestConnectionInfo(channel) { info ->
                                if (info.groupFormed) {
                                    if (info.isGroupOwner) {
                                        //startServer() // Listen for connections
                                    } else {
                                        //connectToOwner(info.groupOwnerAddress) // Connect as client
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        activity.registerReceiver(receiver, intentFilter)
    }

    fun unregisterReceiver(activity: Activity) {
        activity.unregisterReceiver(receiver)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.NEARBY_WIFI_DEVICES])
    fun discoverPeers() {
        manager.discoverPeers(channel, object : WifiP2pManager.ActionListener {
            override fun onSuccess() {
                Log.d("WiFiDirect", "Peer discovery started.")
            }

            override fun onFailure(reason: Int) {
                Log.e("WiFiDirect", "Peer discovery failed: $reason")
            }
        })
    }

    override fun wifiDirectConnecting() {
        TODO("Not yet implemented")
    }
}