package hk.edu.hkbu.comp.e5225623.fashiontap.json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class PhotoResponse(
    val photo_stream: PhotoStream,
    val status: Boolean
): Parcelable