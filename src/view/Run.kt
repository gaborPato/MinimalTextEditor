package view

object Run {
    private var steJframe: STEJframe? = null

    @JvmStatic
    fun main(args: Array<String>) {
        steJframe = STEJframe()
    }

    fun setSteJframeTitle(filePath: String?) {
        var filePath = filePath
        if (filePath == null)
            filePath = "new file"
        steJframe!!.title = filePath
    }
}
