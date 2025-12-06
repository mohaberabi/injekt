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