package InnerModel

data class PaginatedModel(
    var size: Int = Int.MIN_VALUE,
    var lastId:  WorkoutIdModel = WorkoutIdModel.NONE,
    var position: PositionModel = PositionModel.NONE,
) {
    enum class PositionModel {
        NONE,
        FIRST,
        MIDDLE,
        LAST;
    }
}
