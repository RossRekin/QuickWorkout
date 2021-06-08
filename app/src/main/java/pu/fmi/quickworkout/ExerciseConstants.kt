package pu.fmi.quickworkout

class ExerciseConstants {

    //note* companion = static
    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1, "Jumping Jacks", R.drawable.jumping_jacks, false, false)
            val highKnees = ExerciseModel(2, "High Knees", R.drawable.high_knees, false, false)
            val pushUps = ExerciseModel(3, "Push Ups", R.drawable.pushups, false, false)
            val crunches = ExerciseModel(4, "Crunches", R.drawable.crunches, false, false)
            val plank = ExerciseModel(5, "Plank", R.drawable.plank, false, false)

            exerciseList.add(jumpingJacks)
            exerciseList.add(highKnees)
            exerciseList.add(pushUps)
            exerciseList.add(crunches)
            exerciseList.add(plank)

            return exerciseList
        }
    }
}