package pu.fmi.quickworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import pu.fmi.quickworkout.databinding.ActivityHistoryBinding


class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: BMIRecordAdapter? = null
    private val dbHelper = DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "History"
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        initRecyclerView()
        getRecords()

        adapter?.setOnClickDeteleItem {
            deleteRecord(it.id)
        }


    }

    private fun initRecyclerView() {
        val recyclerView = binding.rvItemsList
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BMIRecordAdapter()
        recyclerView.adapter = adapter
    }


    private fun getRecords() {
        val recordsList = dbHelper.getAllRecords()
        adapter?.addItems(recordsList)
    }

    private fun deleteRecord(id: Int) {

        dbHelper.deleteRecordById(id)
        getRecords()


    }

}