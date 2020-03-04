package app.muneesh.githubtrendings.datalayer.localdatasource.tables

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.muneesh.githubtrendings.core.Constants

/**
 * Created by Muneesh on 2020-03-02.
 */
@Entity(tableName = Constants.DEV_TABLE)
data class DeveloperData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val avatar: String?,
    val url: String?,
    val username: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(url)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeveloperData> {
        override fun createFromParcel(parcel: Parcel): DeveloperData {
            return DeveloperData(parcel)
        }

        override fun newArray(size: Int): Array<DeveloperData?> {
            return arrayOfNulls(size)
        }
    }

}
