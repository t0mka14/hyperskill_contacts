package contacts

import Company
import Contact
import Person

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    scanner.useDelimiter("\n");
    val contacts = mutableListOf<Contact>()

    fun listContacts() {
        contacts.forEachIndexed { index, contact ->
            println("${index + 1}. ${contact.print()}")
        }
    }
    fun editContact(inp: Int): Boolean {
        if (inp > contacts.size) {println("No such contact"); return false}
        else {
            while (true) {
                println("[record] Enter action (edit, delete, menu):")
                val action = readln()
                when (action) {
                    "edit" -> {
                        println("Select a field ${contacts[inp - 1].getFieldsNames()}")
                        val selectedField = readln()
                        println("Enter $selectedField:")
                        val enteredValue = readln()
                        contacts[inp - 1].modifyField(selectedField, enteredValue)
                        println("Record updated")
                    }
                    "delete" -> {
                        contacts.removeAt(inp-1)
                        return true
                    }
                    "menu" -> return true
                    else -> println("Action not supported")
                }

            }
        }
    }

    while (true) {
        println("[menu] Enter action (add, list, count, search, exit):")
        var action = scanner.next()
        when (action) {
            "search" -> {
                println("Enter search query:")
                val query = readln()
                var i = 1
                contacts.forEach {
                    if(it.searchFields(query)) {println("$i. ${it.getName()}"); i++}
                }
            }

            "add" -> {
                println("Do you want to add person or organization?")
                action = scanner.next()
                if (action == "person"){
                    println("Enter the name of the person:")
                    val name = scanner.next()
                    println("Enter the surname of the person:")
                    val surname = scanner.next()
                    println("Enter the birth date:")
                    val birth = readln()
                    println("Enter the gender (M, F):")
                    val gender = readln()
                    println("Enter the number:")
                    val number = readln()
                    contacts.add(Person(name, surname, number, birth, gender))
                }
                else if (action == "organization"){
                    println("Enter the name of the organization:")
                    val name = scanner.next()
                    println("Enter the adress:")
                    val adress = scanner.next()
                    println("Enter the number:")
                    val number = readln()
                    contacts.add(Company(name, number, adress))
                }

            }



            "list" -> {
                listContacts()
                println()
                list@ while (true){
                    println("[list] Enter action ([number], back):")
                val inp = readln()
                if (Regex("\\d+").matches(inp)) {
                    if(editContact(inp.toInt())) break
                } else if (inp == "back") {
                    break
                } else println("Action not supported")
            }
        }

            "info" -> {
                contacts.forEachIndexed { index, contact ->
                    println("${index + 1}. ${contact.getName()}")
                }
                println("Select a record:")
                val index = scanner.nextInt()-1
                if (index > contacts.lastIndex) {
                    println("Invalid index")
                }
                else println(contacts[index].print())

            }
/*
            "remove" -> {
                if (contacts.isEmpty()) println("No records to remove!")
                else {
                    listContacts()
                    println("Select a record:")
                    val selected = scanner.nextInt()
                    if (selected > contacts.size) println("No such contact")
                    else contacts.removeAt(selected-1)
                }
            }
*/
           /* "edit" -> {
                if (contacts.isEmpty()) println("No records to edit!")
                else {
                    listContacts()
                    println("Select a record:")
                    val selectedContactN = scanner.nextInt()
                    if (selectedContactN > contacts.size) println("No such contact")
                    else if (contacts[selectedContactN-1].isPerson){
                        println("Select a field (name, surname, number)")
                        val selectedField = scanner.next()
                        println("Enter $selectedField:")
                        val enteredValue = scanner.next()
                        contacts[selectedContactN-1].modifyField(selectedField,enteredValue)
                        println("Record updated")
                    }
                    else{
                        println("Select a field (adress, number)")
                        val selectedField = scanner.next()
                        println("Enter $selectedField:")
                        val enteredValue = scanner.next()
                        contacts[selectedContactN-1].modifyField(selectedField,enteredValue)
                        println("Record updated")
                    }


                }
            }*/
            "count" -> {
                println("The Phone Book has ${contacts.size} records")
            }
            "exit" -> break

            else -> {
                println("Action not supported")
                continue
            }

        }
        println()
    }




}
