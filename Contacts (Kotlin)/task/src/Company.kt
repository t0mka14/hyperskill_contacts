class Company(var orgName: String, number: String, val adress: String): Contact(number)  {

    private var organizationAddress = adress

    override fun modifyField(name: String, value: String) {
        setModifyTime()
        when(name){
            "address" -> organizationAddress = value
            "name" -> orgName = value
            else -> super.modifyField(name, value)
        }
    }

    override fun getName(): String {
        return orgName
    }

    override fun print(): String {
        return "Organization name: $orgName\nAddress: $organizationAddress\nNumber: ${super.print()}"
    }

    override fun getFieldsNames(): String {
        return "name, address, number"
    }

    override fun getField(name: String): String {
        return when(name){
            "name" -> orgName
            "address" -> adress
            "number" -> super.getNumber()
            else -> "No such field"
        }
    }

    override fun searchFields(inp: String): Boolean {
        return "$orgName$adress${super.getNumber()}".contains(inp, true)
    }
}