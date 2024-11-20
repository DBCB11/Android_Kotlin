    package com.example.studentmanapp

    import android.os.Bundle
    import android.util.Log
    import android.view.LayoutInflater
    import android.widget.Button
    import android.widget.EditText
    import android.widget.ImageView
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AlertDialog
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView

    class MainActivity : AppCompatActivity() {
        private lateinit var studentAdapter: StudentAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

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
            studentAdapter = StudentAdapter(students) { position ->
                val student = students[position]

                val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_add_new_student_dialog, null)

                val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
                val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)
                editHoten.setText(student.studentName)
                editMssv.setText(student.studentId)


                AlertDialog.Builder(this)
                    .setTitle("Cap nhat thong tin sinh vien")
                    .setView(dialogView)
                    .setPositiveButton("OK") { _, _ ->
                        // Lấy thông tin mới từ EditText
                        val newHoten = editHoten.text.toString()
                        val newMssv = editMssv.text.toString()

                        // Cập nhật danh sách sinh viên
                        students[position] = StudentModel(newHoten, newMssv)

                        // Cập nhật RecyclerView
                        studentAdapter.notifyItemChanged(position)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }

            findViewById<RecyclerView>(R.id.recycler_view_students).run {
                adapter = studentAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            findViewById<Button>(R.id.btn_add_new).setOnClickListener{
                val dialogView = LayoutInflater.from(this)
                .inflate(R.layout.layout_add_new_student_dialog, null)

              val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
              val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)

              AlertDialog.Builder(this)
                .setTitle("Nhap thong tin sinh vien")
                .setView(dialogView)
                .setPositiveButton("OK", { _, _ ->
                  val hoten = editHoten.text.toString()
                  val mssv = editMssv.text.toString()
                  Log.v("TAG", "$hoten - $mssv")
                    students.add(StudentModel(hoten, mssv))
                    studentAdapter.notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", null)
                .show()
            }


            val studentItemView = LayoutInflater.from(this).inflate(R.layout.layout_student_item, null)
            val imageEdit = studentItemView.findViewById<ImageView>(R.id.image_edit)

            imageEdit.setOnClickListener{

                val dialogView = LayoutInflater.from(this)
                    .inflate(R.layout.layout_add_new_student_dialog, null)

                val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
                val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)

                AlertDialog.Builder(this)
                    .setTitle("Cap nhap thong tin sinh vien")
                    .setView(dialogView)
                    .setPositiveButton("OK", { _, _ ->
                        val hoten = editHoten.text.toString()
                        val mssv = editMssv.text.toString()
                        Log.v("TAG", "$hoten - $mssv")
                        students.add(StudentModel(hoten, mssv))
                        studentAdapter.notifyDataSetChanged()
                    })
                    .setNegativeButton("Cancel", null)
                    .show()
            }

        }
    }