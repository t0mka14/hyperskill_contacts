
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


abstract class Contact(number: String) {
    val formatter = DateTimeFormatter.ofPattern("u-M-d'T'k:m")
    private val timeCreated = ZonedDateTime.now().format(formatter)
    private var timeEdited = timeCreated
    private var sNumber = ""
        set(value) {
            if (checkNum(value)) {
                println("The record added")
                field = value
            }
            else {
                println("Wrong number format!")
                println("The record added")
                field = ""
            }
        }
        get() {
            return if (field == "") "[no data]"
            else field
        }


    init {
        sNumber = number
    }
    fun setModifyTime() {timeEdited = ZonedDateTime.now().format(formatter)}


    abstract fun searchFields(inp: String): Boolean
    abstract fun getName(): String
    abstract fun getFieldsNames(): String
    abstract fun getField(name: String): String

    fun getNumber(): String{
        return sNumber
    }

    open fun modifyField(name: String, value: String){
        when(name){
            "number" -> sNumber = value
//            "name" -> sName = value
//            "surname" -> sSurname = value
            else -> {
                println("No such field")
            }
        }
    }

    open fun print(): String{
        return "$sNumber \nTime created: $timeCreated \nTime last edit: $timeEdited" //$sName $sSurname, $sNumber"
    }

    fun hasNumber(): Boolean{
        return if (sNumber != "") true
        else false
    }

    private fun checkNum(inp: String): Boolean{
        val groupSymbol = "[0-9a-zA-Z]"
        val firstGroup = "$groupSymbol+"
        val group = "$groupSymbol{2,}"
        val regex = Regex("\\+?($firstGroup([ -]\\($group\\))?|\\($firstGroup\\))([ -]$group)*")
        return regex.matches(inp)
    }

}
