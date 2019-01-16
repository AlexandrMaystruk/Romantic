package maystruks08.gmail.com.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsTable(
    @field:ColumnInfo(name = "date")
    var date: String?, @field:ColumnInfo(name = "header")
    var header: String?, @field:ColumnInfo(name = "body")
    var body: String?
) {


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
