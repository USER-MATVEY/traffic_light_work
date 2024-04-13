package com.DobriyanovMP.traffic_light_work

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ActivityState(
    var currentLight: Int,
    var direction: Boolean
): Parcelable