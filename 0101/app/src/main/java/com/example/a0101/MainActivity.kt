package com.example.a0101

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import androidx.room.Room
import com.example.a0101.Database.DAO.StudentDAO
import com.example.a0101.Database.DB.AppDatabase
import com.example.a0101.Database.Model.Student_Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var launcherAdd: ActivityResultLauncher<Intent>
    private lateinit var db: AppDatabase
    private lateinit var studentDao: StudentDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "student_database")
            .fallbackToDestructiveMigration() // Nếu có thay đổi schema, Room sẽ tự động tạo lại DB
            .build()

        studentDao = db.studentDao()

        val students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )


        val studentList = findViewById<ListView>(R.id.listView)
        val studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        studentList.adapter = studentAdapter

        launcherAdd = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            { it: ActivityResult ->
                if (it.resultCode == RESULT_OK) {
                    val hoten = it.data?.getStringExtra("hoten")
                    val mssv = it.data?.getStringExtra("mssv")
                    if (!hoten.isNullOrEmpty() && !mssv.isNullOrEmpty()) {
                        val newStudent = Student_Model(name = hoten, studentId = mssv)
                        GlobalScope.launch(Dispatchers.IO) {
                            studentDao.insert(newStudent)
                        }
                    } else {
                        Toast.makeText(this, "Không nhận được dữ liệu sinh viên", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        registerForContextMenu(studentList)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.main_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                launcherAdd.launch(intent)
            }
            R.id.action_remove -> {
                Toast.makeText(this, "Remove  $pos", Toast.LENGTH_LONG).show()}
            R.id.action_edit -> {
                Toast.makeText(this, "Edit  $pos", Toast.LENGTH_LONG).show()}
        }
        return super.onContextItemSelected(item)
    }
}