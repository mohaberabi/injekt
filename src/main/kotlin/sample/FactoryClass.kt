package sample

class ClassA {
    fun value() = "ClassA"
}

class ClassB {
    fun value() = "ClassB"
}

class ClassC {
    fun value() = "ClassC"

}

class FactoryClass(
    private val classA: ClassA,
    private val classB: ClassB,
    private val classC: ClassC
) {
    init {
        println("FactoryClass init ")
    }

    operator fun invoke() = classA.value() + classB.value() + classC.value()
}

class ComplexSingleTonClass(
    private val classA: ClassA,
    private val classB: ClassB,
    private val classC: ClassC,
    private val factory: FactoryClass,
) {
    operator fun invoke() = factory() + classA.value() + classB.value() + classC.value()

}