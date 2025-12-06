import sample.ClassA
import sample.ClassB
import sample.ClassC
import sample.ComplexSingleTonClass
import sample.FactoryClass
import sample.SingletonClass

val injektModule = injekt {
    singleton { ClassA() }
    singleton { ClassB() }
    singleton { ClassC() }
    factory { FactoryClass(get(), get(), get()) }
    singleton { ComplexSingleTonClass(get(), get(), get(), get()) }
    singleton { SingletonClass() }
}


fun testInjekt() {
    val classA = injektModule.get<ClassA>()
    val classB = injektModule.get<ClassB>()
    val classC = injektModule.get<ClassC>()
    val factory = injektModule.get<FactoryClass>()
    val complexSingleTon = injektModule.get<ComplexSingleTonClass>()
    val singleTone = injektModule.get<SingletonClass>()
    println(classA.value())
    println(classB.value())
    println(classC.value())
    println(factory.invoke())
    println(complexSingleTon.invoke())
    println(singleTone.value)
    val factory2 = injektModule.get<FactoryClass>()
}