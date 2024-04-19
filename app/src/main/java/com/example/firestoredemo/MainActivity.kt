package com.example.firestoredemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * MainActivity to główna aktywność aplikacji. Zarządza interfejsem użytkownika
 * oraz obsługuje logikę związaną z interakcją z bazą danych Firestore.
 */
class MainActivity : AppCompatActivity() {

    // Referencja do obiektu FirebaseFirestore do interakcji z bazą danych Firestore
    val db = Firebase.firestore

    // Obiekt do obsługi operacji na bazie danych Firestore
    private val dbOperations = FirestoreDatabaseOperations(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicjalizacja wszystkich widżetów interfejsu użytkownika
        val buttonDownload = findViewById<Button>(R.id.buttonDownload)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextSurname = findViewById<EditText>(R.id.editTextSurname)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val editTextStudentId = findViewById<EditText>(R.id.editTextStudentId)
        val studentDetailsTV = findViewById<TextView>(R.id.studentDetailsTV)

        // Ustawienie nasłuchiwaczy kliknięć dla przycisków

        buttonDownload.setOnClickListener {
            // Pobieranie identyfikatora studenta z pola tekstowego
            val studentId = editTextStudentId.text.toString()

            // Uruchomienie korutyny w wątku głównym
            GlobalScope.launch(Dispatchers.Main) {
                // Pobranie danych studenta z bazy danych Firestore
                val student = dbOperations.getStudent(studentId)
                if (student != null) {
                    println("pobrano Studenta")
                    // Aktualizacja interfejsu użytkownika
                    updateUI(student, studentDetailsTV)
                } else {
                    println("nie pobrano Studenta")
                }
            }
        }

        buttonAdd.setOnClickListener {
            // Pobranie danych studenta z pól tekstowych
            val studentId = editTextStudentId.text.toString()
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            if (studentId.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && age != null) {
                // Utworzenie obiektu Student
                val student = Student(name, surname, age, studentId)
                // Uruchomienie korutyny w wątku głównym
                GlobalScope.launch(Dispatchers.Main) {
                    // Dodanie studenta do bazy danych Firestore
                    dbOperations.addStudent(studentId, student)
                }
            } else {
                // Obsługa błędów, np. puste pola
            }
        }

        buttonUpdate.setOnClickListener {
            // Pobranie danych studenta z pól tekstowych
            val studentId = editTextStudentId.text.toString()
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            if (studentId.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && age != null) {
                // Utworzenie zaktualizowanego obiektu Student
                val updatedStudent = Student(name, surname, age, studentId)
                // Uruchomienie korutyny w wątku głównym
                GlobalScope.launch(Dispatchers.Main) {
                    // Aktualizacja danych studenta w bazie danych Firestore
                    dbOperations.updateStudent(studentId, updatedStudent)
                }
            } else {
                // Obsługa błędów, np. puste pola
            }
        }

        buttonDelete.setOnClickListener {
            // Pobranie identyfikatora studenta z pola tekstowego
            val studentId = editTextStudentId.text.toString()
            // Uruchomienie korutyny w wątku głównym
            GlobalScope.launch(Dispatchers.Main) {
                // Usunięcie studenta z bazy danych Firestore
                dbOperations.deleteStudent(studentId)
            }
        }
    }

    /**
     * Metoda aktualizuje interfejs użytkownika, wyświetlając dane studenta.
     *
     * @param student Obiekt klasy Student, którego dane mają zostać wyświetlone.
     * @param textView TextView, w którym mają zostać wyświetlone dane studenta.
     */
    private fun updateUI(student: Student, textView: TextView) {
        textView.text = ("Student details: \n" +
                "name: ${student.name} \n" +
                "surname: ${student.surname} \n" +
                "age: ${student.age}")
    }
}
