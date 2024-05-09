package service

import dao.IUserDAO
import entity.UserEntity
import output.Consola
import java.util.*


class UserServiceImpl(private val userDao: IUserDAO, private val consola: Consola) : IUserService {
    override fun create(user: UserEntity): UserEntity? {
        return userDao.create(user, consola)
    }

    override fun getById(id: UUID): UserEntity? {
        return userDao.getById(id,consola)
    }

    override fun update(user: UserEntity): UserEntity? {
        return userDao.update(user,consola)
    }

    override fun delete(id: UUID) {
        userDao.delete(id,consola)
    }

    override fun getAll(): List<UserEntity>? {
        return userDao.getAll()
    }
}