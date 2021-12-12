import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import model.ExerciseModel
import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import org.junit.Test
import java.util.*

class RepoWorkoutSqlTest {

    init {


    }

    fun get(): WorkoutModel {
        val stubExerciseModel1 = ExerciseModel(
            nameExercise = "Прыжки",
            retry = 10,
            numberRepetitions = 3,
            ownWeight = true,
            weight = 0
        )

        val stubExerciseModel2 = ExerciseModel(
            nameExercise = "Отжимания",
            retry = 3,
            numberRepetitions = 5,
            ownWeight = false,
            weight = 100
        )

        return WorkoutModel(
            id = WorkoutIdModel(id = UUID.randomUUID()),
            ownerId = OwnerIdModel(UUID.randomUUID()),
            name = "Простая тренировка",
            description = "Это просто стабовые данные",
            excersices = mutableListOf(stubExerciseModel1, stubExerciseModel2)
        )
    }

    val repoWorkoutSql = RepoWorkoutSql(initObjects = listOf(get()))

    @Test
    fun testSave() {
        println("Good")
    }


}