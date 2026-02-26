package ai.openclaw.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(private var messages: List&lt;Message&gt;) : RecyclerView.Adapter&lt;MessageAdapter.MessageViewHolder&gt;() {
    
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderText: TextView = itemView.findViewById(R.id.senderText)
        val contentText: TextView = itemView.findViewById(R.id.contentText)
        val timeText: TextView = itemView.findViewById(R.id.timeText)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.senderText.text = if (message.sender == &quot;user&quot;) &quot;You&quot; else &quot;OpenClaw&quot;
        holder.contentText.text = message.content
        
        val sdf = SimpleDateFormat(&quot;HH:mm:ss&quot;, Locale.getDefault())
        holder.timeText.text = sdf.format(Date(message.timestamp))
    }
    
    override fun getItemCount() = messages.size
    
    fun updateMessages(newMessages: List&lt;Message&gt;) {
        messages = newMessages
        notifyDataSetChanged()
    }
}