


class Person(name: String, surname: String, number: String, birthDate: String, gender: String): Contact(number) {
    private var sName = name
        set(value) {
            field = value
        }
        get() {
            return field
        }
    private var sSurname = surname
        set(value) {
            field = value
        }
        get() {
            return field
        }

    private var sBirthDate = ""
        set(value) {
            if (value.isEmpty()) {println("Bad birth date!"); field = "[no data]"}
            else field = value
        }
    private var sGender = ""
        set(value) {
            if (value.isEmpty()) {println("Bad gender!"); field = "[no data]"}
            else field = value
        }
    init {
       sBirthDate = birthDate
       sGender = gender
    }
    override fun modifyField(name: String, value: String) {
        setModifyTime()
        when(name){
            "name" -> sName = value
            "surname" -> sSurname = value
            "birth" -> sBirthDate = value
            "gender" -> sGender = value
            else -> super.modifyField(name, value)
        }
    }

    override fun getFieldsNames(): String {
        return "name, surname, birth, gender, number"
    }

    override fun getField(name: String): String {
        return when(name){
            "name" -> sName
            "surname" -> sSurname
            "birth" -> sBirthDate
            "gender" -> sGender
            "number" -> super.getNumber()
            else -> "No such field"
        }
    }

    override fun getName(): String {
        return "$sName $sSurname"
    }
    override fun print(): String {
        return "Name: $sName \nSurname: $sSurname \nBirth date: $sBirthDate \nGender: $sGender \nNumber: ${super.print()}"
    }
    override fun searchFields(inp: String): Boolean {
        return "$sName$sSurname$sBirthDate$sGender${super.getNumber()}".contains(inp, true)
    }
}