package com.ipca.formulaworld.model

class User() {
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var phone: String
    lateinit var vat: String
    lateinit var email: String
    lateinit var password: String
    lateinit var uid: String

    constructor(firstName: String, lastName: String, phone: String, vat: String, email: String, password: String) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
        this.vat = vat
        this.email = email
        this.password = password
    }
}