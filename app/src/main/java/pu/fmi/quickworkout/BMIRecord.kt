package pu.fmi.quickworkout

class BMIRecord {

    var id: Int = 0
    var weight: Float? = null
    var height: Float? = null
    var bmiStatus: String? = null
    var bmi: Float? = null
    var date: String? = null



    constructor(id: Int, weight: Float, height: Float, bmiStatus: String, bmi: Float, date: String) {
        this.id = id
        this.weight = weight
        this.height = height
        this.bmiStatus = bmiStatus
        this.bmi = bmi
        this.date = date
    }

    constructor(weight: Float, height: Float, bmiStatus: String, bmi: Float, date: String) {
        this.weight = weight
        this.height = height
        this.bmiStatus = bmiStatus
        this.bmi = bmi
        this.date = date
    }


}