package controller.exceptions

import java.io.IOException

class FileWriteError : Exception() {

    override fun toString(): String {
        return "File WriteError"
    }
}