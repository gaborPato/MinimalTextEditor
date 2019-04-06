package controller.exceptions

import java.io.IOException

class FileReadError : Exception() {
    override fun toString(): String {
        return "FileReadError "
    }
}
