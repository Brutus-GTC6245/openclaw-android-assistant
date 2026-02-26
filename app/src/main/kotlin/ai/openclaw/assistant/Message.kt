package ai.openclaw.assistant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = &quot;messages&quot;)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sender: String, // &quot;user&quot; or &quot;server&quot;
    val content: String,
    val timestamp: Long
)