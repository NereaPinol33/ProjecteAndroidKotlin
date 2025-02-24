# TaskApp - Llista de Tasques i Més

## Descripció
TaskApp és una aplicació mòbil desenvolupada amb Android Studio utilitzant Kotlin, dissenyada per ajudar-te a organitzar les teves tasques diàries com una llista de coses per fer ("To-Do List"). És senzilla, intuïtiva i amb un estil únic gràcies als seus tons rosats i l’icònic cuaderno rosa que apareix a la pantalla inicial i de càrrega. Aquesta és la primera versió (1.0, de febrer de 2025), però en el futur evolucionarà per incloure una pestanya per portar un registre de lectures, inspirada en aplicacions com Goodreads, mantenint l’estil rosat i convertint-se en una eina versàtil per a tasques i lectures.

## Característiques Actuals
- **Gestió de tasques**: Crea, visualitza, edita i esborra tasques amb títol i descripció.
- **Navegació intuïtiva**: Pantalla inicial amb un cuaderno rosa, llista de tasques, pantalla per afegir/editar tasques, i un diàleg "About" amb informació de l’aplicació.
- **Pantalla de càrrega i inicial**: Mostra un cuaderno rosa mentre es carreguen les dades o a l’inici, abans d’accedir a la llista de tasques.
- **Estil rosat**: Tots els elements visuals (fons, botons, textos) utilitzen una paleta de tons rosats (com `pink_50`, `pink_300`, `pink_500`) per un disseny acollidor i consistent.
- **Navegació amb fletxa d’enrere**: Permet tornar a la llista de tasques des de la pantalla d’edició.

## Plans Futurs
En versions avançades, l’aplicació es transformarà en una biblioteca de llibres, amb una nova pestanya per registrar lectures, similar a Goodreads. Aquesta pestanya permetrà afegir llibres, marcar el progrés de lectura, afegir notes i valoracions, i organitzar-los per categories, mantenint els colors rosats com a marca distintiva.

## Tecnologies Utilitzades
- **Llenguatge**: Kotlin
- **Biblioteques**:
  - Room per gestionar la base de dades SQLite localment.
  - Jetpack Compose o Fragments/RecyclerView per a la interfície d’usuari (segons la configuració).
  - ViewModel i LiveData/Flow per a l’arquitectura MVVM.
  - Navigation Compose (o FragmentManager) per a la navegació entre pantalles.
- **Estils**: Material Design amb una paleta personalitzada de tons rosats.

## Requisits per Executar el Projecte
- **Android Studio**: Versió Iguana (2024.1.x) o superior.
- **JDK**: Java 17 (configurat a Android Studio i al sistema).
- **Gradle**: Versió 8.7 o superior (verifica a `gradle-wrapper.properties`).
- **Dispositiu o Emulador**: Android API 26 (Android 8.0) o superior.

## Com Configurar i Executar
1. **Clona el repositori**:
   ```bash
   git clone git@github.com:<teu-usuari>/TaskApp.git
   cd TaskApp
