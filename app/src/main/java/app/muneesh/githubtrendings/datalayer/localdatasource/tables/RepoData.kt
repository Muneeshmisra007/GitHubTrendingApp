package app.muneesh.githubtrendings.datalayer.localdatasource.tables

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.muneesh.githubtrendings.core.Constants

/**
 * Created by Muneesh on 2020-03-02.
 */
@Entity(tableName = Constants.REPO_TABLE)
data class RepoData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author : String?,
    val name : String?,
    val avatar : String?,
    val url : String?,
    val description : String?,
    val language : String?,
    val languageColor : String?,
    val stars: Int,
    val forks: Int,
    val currentPeriodStars: Int
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(author)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(language)
        parcel.writeString(languageColor)
        parcel.writeInt(stars)
        parcel.writeInt(forks)
        parcel.writeInt(currentPeriodStars)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoData> {
        override fun createFromParcel(parcel: Parcel): RepoData {
            return RepoData(parcel)
        }

        override fun newArray(size: Int): Array<RepoData?> {
            return arrayOfNulls(size)
        }
    }
}