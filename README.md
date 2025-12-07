# Injekt

A tiny lightweight Dependency Injection container written in Kotlin.  
Made for learning how DI works internally — simple, clean and easy to extend.

---

### Features
- `singleton { }` → one shared instance
- `factory { }` → new instance on every request
- `get<T>()` → type-safe retrieval

---

### Usage

```kotlin
val app = injekt {
    singleton { ApiService() }
    factory { UserRepository(get()) }
}

val api: ApiService = app.get()
val repo: UserRepository = app.get()
```



```kotlin
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

    fun reset (){
        creators.clear()
        instances.clear()
    }
}

fun injekt(block: Injekt.() -> Unit) = Injekt().apply(block)
```