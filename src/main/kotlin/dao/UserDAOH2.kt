package dao

import entity.UserEntity
import output.IOutputiinfo
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource


class UserDAOH2(private val dataSource: DataSource) : IUserDAO {

    override fun create(user: UserEntity , consola:IOutputiinfo): UserEntity? {
        val sql = "INSERT INTO tuser (id, name, email) VALUES (?, ?, ?)"
        return  try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.id.toString())
                    stmt.setString(2, user.name)
                    stmt.setString(3, user.email)
                    val rs = stmt.executeUpdate()
                    if (rs ==1){
                        user
                    }else{
                        consola.showMenssage("Algo no fue bien!! (${rs})",true)
                        null
                    }
                }
            }
        }catch (e:Exception){
            consola.showMenssage("Algo no fue bien!! (${e.message})",true)
            null
        }
    }

    override fun getById(id: UUID , consola: IOutputiinfo): UserEntity? {
        val sql = "SELECT * FROM tuser WHERE id = ?"
        return try {

            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        UserEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            email = rs.getString("email")
                        )
                    } else {
                        null
                    }
                }
            }
        }catch (e: SQLException){
            consola.showMenssage("error de query",true)
            null
        }
    }

    override fun getAll(): List<UserEntity>? {
        val sql = "SELECT * FROM tuser"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<UserEntity>()
                    while (rs.next()) {
                        users.add(
                            UserEntity(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name"),
                                email = rs.getString("email")
                            )
                        )
                    }
                    users
                }
            }
        }catch (e:SQLException){
            null
        }
    }

    override fun update(user: UserEntity, consola: IOutputiinfo): UserEntity? {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return try {

            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.name)
                    stmt.setString(2, user.email)
                    stmt.setString(3, user.id.toString())
                    stmt.executeUpdate()
                    user
                }
            }

        }catch (e:SQLException){
            consola.showMenssage("No ha salido bien.",true)
            null
        }
    }

    override fun delete(id: UUID, consola: IOutputiinfo):Boolean {
        val sql = "DELETE FROM tuser WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    stmt.executeUpdate()
                }
            }
            true
        }catch (e:SQLException){
            consola.showMenssage("No se pudo borrar .",true)
            false
        }
    }
}