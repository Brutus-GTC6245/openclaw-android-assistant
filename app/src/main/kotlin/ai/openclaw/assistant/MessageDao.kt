package ai.openclaw.assistant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query(&quot;SELECT * FROM messages ORDER BY timestamp ASC&quot;)
    fun getAllMessages(): Flow&lt;List&lt;Message&gt;&gt;
    
    @Insert
    suspend fun insert(message: Message)
    
    @Query(&quot;SELECT * FROM messages ORDER BY timestamp ASC&quot;)
    suspend fun getAllMessagesSync(): List&lt;Message&gt;
}