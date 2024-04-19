package com.example.firestoredemo
/**
 * Interfejs FirestoreInterface definiuje operacje do interakcji z bazą danych Firestore.
 * Wszystkie operacje są oznaczone jako 'suspend', co oznacza, że mogą być wywoływane
 * tylko w kontekście korutyny.
 */
interface FirestoreInterface {

    /**
     * Suspend function do dodawania nowego rekordu (studenta) do bazy danych Firestore.
     *
     * @param studentId Identyfikator nowego studenta.
     * @param student Obiekt klasy Student, który ma zostać dodany do bazy danych.
     */
    suspend fun addStudent(studentId: String, student: Student)

    /**
     * Suspend function do pobierania danych studenta z bazy danych na podstawie jego ID.
     *
     * @param studentId Identyfikator studenta, którego dane mają zostać pobrane.
     * @return Obiekt klasy Student odpowiadający danym studenta z bazy danych,
     * lub null, jeśli student o podanym identyfikatorze nie istnieje.
     */
    suspend fun getStudent(studentId: String): Student?

    /**
     * Suspend function do aktualizacji istniejącego rekordu (studenta) w bazie danych Firestore.
     *
     * @param studentId Identyfikator studenta, którego dane mają zostać zaktualizowane.
     * @param updatedStudent Obiekt klasy Student z zaktualizowanymi danymi.
     */
    suspend fun updateStudent(studentId: String, updatedStudent: Student)

    /**
     * Suspend function do usuwania istniejącego rekordu (studenta) z bazy danych na podstawie jego ID.
     *
     * @param studentId Identyfikator studenta, którego dane mają zostać usunięte.
     */
    suspend fun deleteStudent(studentId: String)
}
