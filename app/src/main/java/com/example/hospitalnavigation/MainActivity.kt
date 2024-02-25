package com.example.hospitalnavigation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val searchView: SearchView = findViewById(R.id.searchView)

        val data = arrayOf("Banyo", "Mutfak", "Tuvalet", "Yatak Odası")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) { // Eğer metin boşsa, ListView'deki verileri gösterme
                        listView.adapter = null
                    } else {
                        val filteredData = data.filter { item ->
                            item.toLowerCase().contains(newText.toLowerCase())
                        }
                        val filteredAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            filteredData
                        )
                        listView.adapter = filteredAdapter
                    }
                }
                return true
            }
        })

        // ListView öğelerine tıklanma işlemi
        listView.setOnItemClickListener { parent, view, position, id ->
            // Tıklanan öğenin metnini al
            val selectedItem = parent.getItemAtPosition(position) as String
            // Seçilen birimin adını Toast mesajı olarak göster
            Toast.makeText(this, "Seçilen birim: $selectedItem", Toast.LENGTH_SHORT).show()

            searchView.clearFocus()
        }
    }
}
