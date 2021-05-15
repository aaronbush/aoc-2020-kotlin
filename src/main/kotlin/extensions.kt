import java.io.File

fun String.loadFile() =
    Class<File>::getClassLoader.javaClass.getResourceAsStream(this)
        .bufferedReader().useLines { it.toList() }