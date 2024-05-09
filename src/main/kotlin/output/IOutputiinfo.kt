package output

import entity.UserEntity

interface IOutputiinfo {
    fun showMenssage(mensaje:String, lineBreak:Boolean)

    fun show(userList: List<UserEntity>,mensaje: String)
}