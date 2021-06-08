package pu.fmi.quickworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BMIRecordAdapter : RecyclerView.Adapter<BMIRecordAdapter.BMIRecordViewHolder>() {

    private var BMIRecordList: ArrayList<BMIRecord> = ArrayList()
    private var onClickDeleteItem: ((BMIRecord) -> Unit)? = null


    fun addItems(items: ArrayList<BMIRecord>) {
        this.BMIRecordList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeteleItem(callback:(BMIRecord)-> Unit){
        this.onClickDeleteItem     = callback

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMIRecordViewHolder =
        BMIRecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.record, parent, false)
        )

    override fun onBindViewHolder(holder: BMIRecordViewHolder, position: Int) {
        val bmiRecord = BMIRecordList[position]
        holder.bindView(bmiRecord)
        holder.btnDelete.setOnClickListener{
            onClickDeleteItem?.invoke(bmiRecord)

        }
    }

    override fun getItemCount(): Int {
        return BMIRecordList.size
    }


    class BMIRecordViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var weight = view.findViewById<TextView>(R.id.tvWeight)
        private var height = view.findViewById<TextView>(R.id.tvHeight)
        private var bmiStatus = view.findViewById<TextView>(R.id.tvBMIStatus)
        private var bmi = view.findViewById<TextView>(R.id.tvBMI)
        private var date = view.findViewById<TextView>(R.id.tvDate)
        var btnDelete = view.findViewById<ImageView>(R.id.ivDelete)

        fun bindView(bmiRecord: BMIRecord) {
            id.text = bmiRecord.id.toString()
            weight.text = "weight: " + bmiRecord.weight.toString()
            height.text = "height: " + bmiRecord.height.toString()
            bmiStatus.text = "Bmi status: " + bmiRecord.bmiStatus.toString()
            bmi.text = "Bmi: " + bmiRecord.bmi.toString()
            date.text = bmiRecord.date.toString()

        }
    }
}

