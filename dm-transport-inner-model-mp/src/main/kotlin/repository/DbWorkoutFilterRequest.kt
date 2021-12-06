package repository

import model.OwnerIdModel

data class DbWorkoutFilterRequest(
	val searchStr: String = "",
	val ownerId: OwnerIdModel = OwnerIdModel.NONE,
//    val dealSide: DealSideModel = DealSideModel.NONE,
)