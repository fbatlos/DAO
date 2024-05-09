package dao

import entity.UserEntity
import output.IOutputiinfo
import java.util.*

interface IUserDAO {
    fun create(user: UserEntity, consola: IOutputiinfo):UserEntity?
    fun getAll(): List<UserEntity>?
    fun getById(id: UUID, consola: IOutputiinfo): UserEntity?
    fun update(user: UserEntity, consola: IOutputiinfo): UserEntity?
    fun delete(id: UUID, consola: IOutputiinfo): Boolean
}