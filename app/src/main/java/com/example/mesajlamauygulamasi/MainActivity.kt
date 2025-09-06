package com.example.mesajuygulamasi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class MainActivity : AppCompatActivity() {
    // UI öğeleri
    private lateinit var tvMessages: TextView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    // Ağ değişkenleri
    private var socket: Socket? = null
    private var writer: BufferedWriter? = null
    private var reader: BufferedReader? = null

    // Sunucu IP ve Port
    private val serverIp = "000.000.0.000"  // Bilgisayarın hotspot üzerinden aldığı IP
    //ipconfig yapıp kendi ip adresinizi giriniz.
    private val serverPort = 8081

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI bağlantıları
        tvMessages = findViewById(R.id.tvMessages)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        // Sunucuya bağlan
        connectToServer()

        // Gönder butonuna basıldığında
        btnSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun connectToServer() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                socket = Socket(serverIp, serverPort)
                writer = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream()))
                reader = BufferedReader(InputStreamReader(socket!!.getInputStream()))

                startMessageListener()

                withContext(Dispatchers.Main) {
                    tvMessages.append("Sunucuya bağlanıldı!\n")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    tvMessages.append("Bağlantı hatası: ${e.message}\n")
                }
            }
        }
    }

    private fun startMessageListener() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                while (true) {
                    val message = reader?.readLine() ?: break
                    withContext(Dispatchers.Main) {
                        tvMessages.append("Sunucu: $message\n")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    tvMessages.append("Bağlantı koptu: ${e.message}\n")
                }
            } finally {
                disconnect()
            }
        }
    }

    private fun sendMessage() {
        val message = etMessage.text.toString()
        if (message.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    writer?.write(message + "\n") // Önemli: \n
                    writer?.flush()              // Mesajın gönderilmesini sağlar
                    withContext(Dispatchers.Main) {
                        etMessage.text.clear()
                        tvMessages.append("Sen: $message\n")
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        tvMessages.append("Mesaj gönderilemedi: ${e.message}\n")
                    }
                }
            }
        }
    }

    private fun disconnect() {
        try {
            reader?.close()
            writer?.close()
            socket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnect()
    }
}


