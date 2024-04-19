package com.example.firestoredemo

import com.google.firebase.firestore.PropertyName

/**
 * Klasa Student reprezentuje dane studenta.
 *
 * @property name Imię studenta.
 * @property surname Nazwisko studenta.
 * @property age Wiek studenta.
 * @property studentId Numer identyfikacyjny studenta.
 *
 * @constructor Tworzy obiekt klasy Student z podanymi wartościami lub inicjuje
 * puste łańcuchy znaków (String) dla name, surname, studentId oraz zero dla age.
 * Konstruktor ten jest wymagany do prawidłowej deserializacji obiektu przez Firestore.
 * Standardowa implementacja klasy Student wyglądałaby tak:

 * //    val name: String,
 * //    val surname: String,
 * //    val age: Int,
 * //    val studentId: String // Numer ID studenta
 *
 */
data class Student(
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("surname") @set:PropertyName("surname") var surname: String = "",
    @get:PropertyName("age") @set:PropertyName("age") var age: Int = 0,
    @get:PropertyName("studentId") @set:PropertyName("studentId") var studentId: String = ""
)

