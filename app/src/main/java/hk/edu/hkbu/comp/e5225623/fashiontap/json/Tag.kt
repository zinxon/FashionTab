package hk.edu.hkbu.comp.e5225623.fashiontap.json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Tag(
    val id: Int?,
    val photo_id: Int?,
    val x: Int?,
    val y: Int?,
    val name: String?,
    val url: String?
): Parcelable