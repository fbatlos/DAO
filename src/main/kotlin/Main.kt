import dao.UserDAOH2
import db_connection.DataSourceFactory
import entity.UserEntity
import output.Consola
import service.UserServiceImpl

fun main() {
    val consola = Consola()
    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val userDao = UserDAOH2(dataSource)

    // Creamos la instancia de UserService
    val userService = UserServiceImpl(userDao,consola)

    // Creamos un nuevo usuario
    val newUser = UserEntity(name = "John Doe", email = "johndoe@example.com")
    var createdUser = userService?.create(newUser)
    consola.showMenssage("Created user: $createdUser",true)

    // Obtenemos un usuario por su ID
    val foundUser = createdUser?.let { userService.getById(it.id) }
    consola.showMenssage("Found user: $foundUser",true)

    // Actualizamos el usuario
    val updatedUser = foundUser?.copy(name = "Jane Doe")
    val savedUser = updatedUser?.let { userService.update(it) }
    consola.showMenssage("Updated user: $savedUser",true)

    val otherUser = UserEntity(name = "Eduardo Fernandez", email = "eferoli@gmail.com")
    createdUser = userService.create( otherUser)
    consola.showMenssage("Created user: $createdUser",true)


    // Obtenemos todos los usuarios
    var allUsers = userService.getAll()
    consola.showMenssage("All users: $allUsers",true)

    // Eliminamos el usuario
    savedUser?.let { userService.delete(it.id) }
    consola.showMenssage("User deleted",true)

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    consola.showMenssage("All users: $allUsers",true)

    // Eliminamos el usuario
    userService.delete(otherUser.id)
    consola.showMenssage("User deleted",true)
}