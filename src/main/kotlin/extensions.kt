import java.io.File

fun String.loadFile() =
    Class<File>::getClassLoader.javaClass.getResourceAsStream(this)
        .bufferedReader().useLines { it.toList() }

fun <E> List<E>.circular() =
    CircularList(this)

class CircularList<T>(c: Collection<T>) : ArrayList<T>(c) {
    override fun get(index: Int) = super.get(index % size)
}
