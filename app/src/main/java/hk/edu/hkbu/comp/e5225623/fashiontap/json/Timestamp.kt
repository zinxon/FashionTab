package hk.edu.hkbu.comp.e5225623.fashiontap.json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Timestamp(
    val formatted: String,
    val raw: Long
): Parcelable