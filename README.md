![RM Roster icon](docs/images/icon.png)

# RM Roster — Arkano-Challenge

Repositorio del challenge de Arkano.
App Android que explora personajes de Rick & Morty con funcionalidad Pull to refresh.

## 🏛️ Arquitectura

El proyecto sigue **Clean Architecture**, separando en capas cuando aplica (no todos los módulos
necesitan las tres):

```
domain  → Modelos, casos de uso y contratos (interfaces).
data    → Implementación de esos contratos: repositorios, mappers y fuentes de datos (Room, Retrofit).
ui      → Archivos que representan la interfaz de usuario de la app: pantallas con Compose, ViewModels, Activities, etc.
```

Es **offline-first**: Room es la única fuente de verdad (single source of truth) para la UI —
`GetCharactersUseCase` expone un `Flow` que observa directamente el DAO. `DownloadCharactersUseCase`
decide cuándo pedir datos a la API: solo si se fuerza un refresh (pull-to-refresh) o si todavía no
hay personajes en caché. Al refrescar, el repositorio limpia la tabla y guarda los personajes
descargados.

La capa **ui** usa **Jetpack Compose con MVVM**: el `ViewModel` combina el flujo de datos con el
estado de carga/error en un único `StateFlow`, y las pantallas son composables *stateless* que solo
reciben el estado y lambdas para las interacciones (`onRefresh`, `onRetryClick`) — así quedan
desacopladas del ViewModel, más fáciles de previsualizar y testear.

Detalle de cada módulo:

```
app
├── domain  → Modelos, casos de uso y contratos.
├── data    → Implementación de esos contratos: repositorios y mappers.
├── ui      → Vistas Compose, ViewModels y Activities.
└── di      → Módulos de Hilt para inyectar dependencias.

core
├── data/local   → Base de datos Room: entidades y DAOs.
├── data/remote  → Cliente Retrofit: API y DTOs.
└── di           → Módulos de Hilt para red y base de datos.

common
├── ui/theme       → Colores, tipografía y dimensiones (design system).
├── ui/components  → Componentes Compose reutilizables.
├── ui/extensions  → Extensiones de Compose/Kotlin compartidas.
└── ui/utils       → Utilidades compartidas.
```

## 🔧 Decisiones técnicas

| Decisión | Por qué |
|---|---|
| `ImmutableList` en vez de `List` para el estado de UI | Es una lista estable: Compose sabe que no va a cambiar, así que evita recomposiciones innecesarias en la lista de personajes. |
| Room como única fuente de verdad | La UI solo observa Room vía `Flow`; la red únicamente actualiza la base de datos en segundo plano. Esto evita estados inconsistentes y da soporte offline-first sin lógica extra. |
| Descargar solo 3 páginas para poblar la base de datos | En pruebas, la API empezaba a fallar tras varios requests seguidos (parece un límite anti-spam). Limitar la descarga a 3 páginas evita saturarla. |
| Pull-to-refresh manual como mecanismo de TTL | Todo offline-first necesita definir cuándo los datos expiran. Aquí ese mecanismo es manual: el usuario decide cuándo refrescar. |
| Vistas y funciones reutilizables en `common` | Se centralizan pensando en reutilizarlas más adelante, por ejemplo si se agrega una pantalla de detalle. |

## ⏳ Qué quedó fuera por falta de tiempo

| Feature | Detalle |
|---|---|
| Vista de detalle al presionar un personaje de la lista | Pantalla con la información completa del personaje seleccionado. |
| Buscador en el listado de personajes | Usando FTS4/FTS5 para optimizar las búsquedas, con el tokenizer `unicode61` y su opción `remove_diacritics` para encontrar personajes sin importar mayúsculas o acentos. |

## 🚀 Qué mejoraría con más tiempo

| Mejora | Detalle |
|---|---|
| Feedback de error más específico en la UI | Hoy solo hay un estado genérico de error con reintento; se podría diferenciar entre tipos de error (sin conexión, error del servidor, etc.) y comunicarlo mejor al usuario. |
| Unit testing en todas las capas, incluyendo UI | Actualmente el proyecto solo tiene los tests de ejemplo generados por Android Studio. |
| Paginación real con `RemoteMediator` de Paging3 | La API de Rick & Morty ya pagina sus resultados; en vez de descargar un rango fijo de páginas, se podría cargar bajo demanda conforme el usuario hace scroll. |

## 🤖 Uso de IA: en qué partes y cómo

- **GitHub Copilot** (sobre todo el autocompletado) durante todo el desarrollo, a través del plugin
  de Android Studio.
- **Claude Code** y **Gemini** como apoyo teórico, para consultar conceptos y validar decisiones en
  base a datos.
- **Claude Code** para escribir algunos fragmentos de código (sobre todo la UI y las capas de la
  arquitectura); la arquitectura ya la tenía clara, la IA solo se usó para optimizar tiempos de
  desarrollo.
- Este archivo README.md lo escribí con IA, revisando punto por punto y modificando lo que creí
  oportuno.

> [!IMPORTANT]
> Toda la arquitectura, toma de decisiones y optimizaciones las definí en base a mi experiencia,
> siguiendo buenas prácticas y con el objetivo de entregar un código limpio, escalable y sobre todo
> fácil de leer por cualquier desarrollador.