package example.core3.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostDto(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
) : Parcelable