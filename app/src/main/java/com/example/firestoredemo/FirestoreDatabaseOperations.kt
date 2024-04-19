package com.example.firestoredemo

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

/**
 * Klasa FirestoreDatabaseOperations implementuje interfejs FirestoreInterface
 * i zawiera metody do dodawania, pobierania, aktualizowania i usuwania danych studenta
 * w bazie danych Firestore.
 *
 * @property db - Referencja do obiektu FirebaseFirestore, służąca do interakcji z bazą danych Firestore.
 */

class FirestoreDatabaseOperations(private val db: FirebaseFirestore) : FirestoreInterface {


    /**
     *  funkcja do dodawania nowego studenta do bazy danych Firestore.
     * Wykorzystuje mechanizm korutyn do wykonywania operacji asynchronicznych.
     * suspend - oznacza, ze funkcja moze byc zawieszana w korutynie
     * @param studentId Identyfikator nowego studenta.
     * @param student Obiekt klasy Student, który ma zostać dodany do bazy danych.
     */

    override suspend fun addStudent(studentId: String, student: Student) {
        try {
            db.collection("students").document(studentId).set(student).await()
        } catch (e: Exception) {
            // Obsługa błędów, np. logowanie, zwracanie wartości lub propagacja wyjątku
        }
    }

    /**
     *      funkcja do pobierania danych studenta z bazy danych Firestore.
     *      * Wykorzystuje mechanizm korutyn do wykonywania operacji asynchronicznych.
     *      *
     *      * @param studentId Identyfikator studenta, którego dane mają zostać pobrane.
     *      * @return Obiekt klasy Student odpowiadający danym studenta z bazy danych,
     *      * lub null, jeśli student o podanym identyfikatorze nie istnieje.
     *      */

  override suspend fun getStudent(studentId: String): Student? {
        val snapshot = FirebaseFirestore.getInstance().collection("students")
            .whereEqualTo(FieldPath.documentId(), studentId)
            .get()
            .await()

        return snapshot.documents.firstOrNull()?.toObject(Student::class.java)
    }

    /**
     * funkcja do aktualizowania danych studenta w bazie danych Firestore.
     * Wykorzystuje mechanizm korutyn do wykonywania operacji asynchronicznych.
     *
     * @param studentId Identyfikator studenta, którego dane mają zostać zaktualizowane.
     * @param updatedStudent Obiekt klasy Student z zaktualizowanymi danymi.
     */

    override suspend fun updateStudent(studentId: String, updatedStudent: Student) {
        try {
            db.collection("students").document(studentId).set(updatedStudent).await()
        } catch (e: Exception) {
            // Obsługa błędów
        }
    }
    /**
     * funkcja do usuwania danych studenta z bazy danych Firestore.
     * Wykorzystuje mechanizm korutyn do wykonywania operacji asynchronicznych.
     *
     * @param studentId Identyfikator studenta, którego dane mają zostać usunięte.
     */

    override suspend fun deleteStudent(studentId: String) {
        try {
            db.collection("students").document(studentId).delete().await()
        } catch (e: Exception) {
            // Obsługa błędów
        }
    }
}