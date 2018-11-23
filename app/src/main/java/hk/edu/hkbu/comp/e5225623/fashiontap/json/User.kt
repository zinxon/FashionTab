package hk.edu.hkbu.comp.e5225623.fashiontap.json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class User(
    val avatar: String,
    val name: String,
    val url: String,
    val user_id: Int,
    val username: String
): Parcelable