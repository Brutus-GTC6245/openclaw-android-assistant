package ai.openclaw.assistant

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class MainActivity : AppCompatActivity() {
    
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var messagesRecycler: RecyclerView
    private lateinit var connectionStatus: TextView
    
    private lateinit var wsClient: WebSocketClient
    private lateinit var messageDao: MessageDao
    private lateinit var messageAdapter: MessageAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initDatabase()
        initViews()
        setupWebSocket()
        loadMessages()
    }
    
    private fun initDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, &quot;openclaw-db&quot;
        ).build()
        messageDao = db.messageDao()
    }
    
    private fun initViews() {
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        messagesRecycler = findViewById(R.id.messagesRecycler)
        connectionStatus = findViewById(R.id.connectionStatus)
        
        messagesRecycler.layoutManager = LinearLayoutManager(this)
        messageAdapter = MessageAdapter(emptyList())
        messagesRecycler.adapter = messageAdapter
        
        sendButton.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                sendMessage(message)
                messageInput.text.clear()
            }
        }
    }
    
    private fun setupWebSocket() {
        val uri = URI(&quot;ws://echo.websocket.org&quot;) // Replace with OpenClaw WS endpoint
        wsClient = object : WebSocketClient(uri) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                runOnUiThread {
                    connectionStatus.text = &quot;Connected&quot;
                    Toast.makeText(this@MainActivity, &quot;Connected to OpenClaw&quot;, Toast.LENGTH_SHORT).show()
                }
            }
            
            override fun onMessage(message: String?) {
                message?.let {
                    runOnUiThread {
                        saveMessage(&quot;server&quot;, it)
                        updateMessages()
                    }
                }
            }
            
            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                runOnUiThread {
                    connectionStatus.text = &quot;Disconnected&quot;
                }
            }
            
            override fun onError(ex: Exception?) {
                runOnUiThread {
                    connectionStatus.text = &quot;Error&quot;
                    Toast.makeText(this@MainActivity, &quot;WS Error: ${ex?.message}&quot;, Toast.LENGTH_SHORT).show()
                }
            }
        }
        wsClient.connect()
    }
    
    private fun sendMessage(message: String) {
        saveMessage(&quot;user&quot;, message)
        updateMessages()
        wsClient.send(message)
    }
    
    private fun saveMessage(sender: String, content: String) {
        lifecycleScope.launch {
            messageDao.insert(Message(0, sender, content, System.currentTimeMillis()))
        }
    }
    
    private fun loadMessages() {
        lifecycleScope.launch {
            val messages = withContext(Dispatchers.IO) {
                messageDao.getAllMessages()
            }
            messageAdapter.updateMessages(messages)
        }
    }
    
    private fun updateMessages() {
        lifecycleScope.launch {
            val messages = withContext(Dispatchers.IO) {
                messageDao.getAllMessages()
            }
            runOnUiThread {
                messageAdapter.updateMessages(messages)
                messagesRecycler.scrollToPosition(messages.size - 1)
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        wsClient.close()
    }
}