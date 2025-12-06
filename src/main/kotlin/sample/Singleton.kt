package sample
import kotlin.random.Random


class SingletonClass {

    var value: Int = 0

    init {
        generateRandom()
    }

    private fun generateRandom() {
        value = Random.nextInt()
    }

}