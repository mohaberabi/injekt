import kotlin.apply
import kotlin.collections.getOrPut
import kotlin.collections.set
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class Injekt {

    private val creators = mutableMapOf<KType, () -> Any?>()
    private val instances = mutableMapOf<KType, Any?>()

    inline fun <reified T> factory(
        noinline provide: Injekt.() -> T
    ) = factory(type = typeOf<T>(), provide = provide)

    fun factory(
        type: KType,
        provide: Injekt.() -> Any?
    ) {
        creators[type] = { provide() }

    }

    inline fun <reified T> singleton(
        noinline provide: Injekt.() -> T
    ) = singleton(type = typeOf<T>(), provide = provide)

    fun singleton(
        type: KType,
        provide: Injekt.() -> Any?
    ) {
        creators[type] = { instances.getOrPut(type) { provide.invoke(this) } }
    }

    inline fun <reified T> get(): T {
        val key = typeOf<T>()
        return get(type = key) as T
    }

    fun get(
        type: KType,
    ): Any = instances[type] ?: creators[type]?.invoke() ?: error("could not provide instance for $type")

    fun exists(
        type: KType
    ): Boolean = creators[type] != null
}

fun injekt(block: Injekt.() -> Unit) = Injekt().apply(block)